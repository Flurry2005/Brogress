package se.aljr.application.loginpage;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import se.aljr.application.UserData;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.programplanner.Workout;
import se.aljr.application.programplanner.WorkoutSet;
import se.aljr.application.programplanner.WorkoutsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FirebaseManager {
    private static String resourcePath;
    private static Firestore db;
    private static FirestoreOptions firestoreOptions;
    private static StorageOptions storageOptions;
    static {
        try {
            resourcePath = FirebaseManager.class.getClassLoader().getResource("resource.path")
                    .getPath().replace("resource.path","");

            FileInputStream serviceAccount = new FileInputStream(resourcePath + "serviceKey.json" );
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://test-7528a.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            firestoreOptions = FirestoreOptions.newBuilder()
                    .setProjectId("brogress-7499c")
                    .setCredentials(credentials)
                    .build();

            db = firestoreOptions.getService();

            storageOptions = StorageOptions.newBuilder().setCredentials(credentials).build();


        } catch (Exception e) {
            System.out.println("An error occurred during Firebase initialization");
            e.printStackTrace();
        }
    }

    public static void writeDBnewUser(String name, String email) throws IOException {

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("name", name);
        user.put("age", "");
        user.put("height", "");
        user.put("weight", "");
        user.put("workouts", "");
        user.put("profilepicture","");


        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(email);

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.set(user);

        try {
            System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDBUser(String email) throws IOException {

        Map<String, Object> user = new HashMap<>();
        user.put("name", UserData.getUserName());
        user.put("age", String.valueOf(UserData.getUserAge()));
        user.put("height", String.valueOf(UserData.getUserHeight()));
        user.put("weight", String.valueOf(UserData.getUserWeight()));


        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(email);

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(user);

        try {
            System.out.println("Updated User Info on db: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDBUserInfo(String email) {

        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(email).get();

            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            userData = gson.fromJson(jsonElement, HashMap.class);

            UserData.setUserName(userData.get("name").toString());
            UserData.setEmail(userData.get("email").toString());
            UserData.setUserWeight(userData.get("weight").toString().isEmpty() ? 0:Float.parseFloat(userData.get("weight").toString())); //If no user weight is set, return 0
            UserData.setUserAge(userData.get("age").toString().isEmpty() ?0:Integer.parseInt(userData.get("age").toString())); //If no user age is set, return 0
            UserData.setUserHeight(userData.get("height").toString().isEmpty() ?0:Integer.parseInt(userData.get("height").toString())); //If no user height is set, return 0

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDBworkout(WorkoutsList workoutsList) throws IOException {
        Storage storage = storageOptions.getService();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(workoutsList);
        objectOutputStream.close();

        String workoutBase64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());


        String bucketName = "brogress-7499c.firebasestorage.app"; // Ersätt med ditt bucket-namn
        String fileName = UserData.getEmail() + "_workouts.txt"; // Filnamn med användarnamn

        // Konvertera String till byte-array
        byte[] bytes = workoutBase64.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        // Skapa en Blob (fil) i Firebase Storage
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();

        // Ladda upp innehållet
        storage.create(blobInfo, inputStream);

        String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;

        Map<String, Object> ref = new HashMap<>();
        ref.put("workouts", publicUrl);

        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(UserData.getEmail());

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(ref);

        System.out.println("Text har laddats upp som fil: " + fileName);
        System.out.println("Publik URL: " + "https://storage.googleapis.com/" + bucketName + "/" + fileName);



    }

    public static WorkoutsList readDBworkout(ProgramPanel programPanel){
        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(UserData.getEmail()).get();

            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            userData = gson.fromJson(jsonElement, HashMap.class);

            int height = programPanel.getHeight();

            System.out.print(programPanel.getHeight());
            if (!userData.get("workouts").toString().isEmpty()) {
                Storage storage = storageOptions.getService();

                String fileName = userData.get("workouts").toString();

                URL url = new URL(fileName);
                String path = url.getPath();
                String bucketName = "brogress-7499c.firebasestorage.app";
                String userWorkoutFileName = path.substring(1).split("/")[1];

                byte[] data1 = storage.get(BlobId.of(bucketName,userWorkoutFileName)).getContent();
                String fileContent = new String(data1, StandardCharsets.UTF_8);


                byte[] data = Base64.getDecoder().decode(fileContent);
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                WorkoutsList workoutsList = (WorkoutsList) objectInputStream.readObject();
                objectInputStream.close();
                for(Workout workout : workoutsList){
                    workout.getWorkoutData().setTotalWorkoutHeight(0);
                    int exerciseId = 1;
                    for (Component comp1 : workout.getComponents()) {
                        if(comp1.getName()!=null){
                            System.out.println("Added 4 panels : " + (float) (4 * height) / 19);

                            if (comp1.getName().equals("mainExercisePanel")) {
                                workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight()+(4 * ProgramPanel.setPanelHeight));
                                JPanel mainExercisePanel = (JPanel) comp1;
                                int setCounterMoveUp = 0;
                                for (Component comp2 : mainExercisePanel.getComponents()) {
                                    int finalExerciseId = exerciseId;
                                    if ("addSet".equals(comp2.getName())) {
                                        JButton addSet = (JButton) comp2;
                                        int finalExerciseId1 = exerciseId;
                                        addSet.addActionListener(e -> {
                                            ProgramPanel.addSet(finalExerciseId1, workout.getIdToExercise(finalExerciseId1), mainExercisePanel, ProgramPanel.setPanelHeight, workout);
                                            workout.setPreferredSize(new Dimension(workout.getWidth(), (int) workout.getWorkoutData().getTotalWorkoutHeight()));
                                            workout.revalidate();
                                            workout.repaint();

                                        });
                                    }

                                    if ("setPanel".equals(comp2.getName())) {
                                        JPanel setPanel = (JPanel) comp2;
                                        System.out.println("Added hieght for set panel: " + (float) height / 19);

                                        for (Component compRight : setPanel.getComponents()){

                                            if(compRight.getName()!=null){

                                                if("rightPanel".equals(compRight.getName())){
                                                    System.out.println("Found right panel, set: "+setCounterMoveUp);
                                                    JPanel rightPanel = (JPanel) compRight;
                                                    for(Component compMoveSetUp : rightPanel.getComponents()){
                                                        if(compMoveSetUp.getName()!=null){
                                                            if("moveSetUp".equals(compMoveSetUp.getName())){
                                                                System.out.println("Found moveSetUp, set: "+setCounterMoveUp);
                                                                JButton moveSetUp = (JButton) compMoveSetUp;
                                                                int finalExerciseId2 = exerciseId;
                                                                int finalSetCounter1 = setCounterMoveUp;
                                                                WorkoutSet workoutSet = workout.getWorkoutData().getExerciseSets().get(finalExerciseId2).get(finalSetCounter1);
                                                                moveSetUp.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {


                                                                        ProgramPanel.moveUp(workout.getExercisePanels().get(finalExerciseId2), finalExerciseId2, workoutSet, workout);
                                                                    }
                                                                });
                                                            }
                                                            if("moveSetDown".equals(compMoveSetUp.getName())){
                                                                System.out.println("Found moveSetDown, set: "+setCounterMoveUp);
                                                                JButton moveSetDown = (JButton) compMoveSetUp;
                                                                int finalExerciseId2 = exerciseId;
                                                                int finalSetCounter1 = setCounterMoveUp;
                                                                WorkoutSet workoutSet = workout.getWorkoutData().getExerciseSets().get(finalExerciseId2).get(finalSetCounter1);
                                                                moveSetDown.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        ProgramPanel.moveDown(workout.getExercisePanels().get(finalExerciseId2), finalExerciseId2, workoutSet, workout);
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }
                                                    setCounterMoveUp++;
                                                }
                                            }
                                        }

                                        for (Component compLeftPanel : setPanel.getComponents()) {
                                            if ("leftPanel".equals(compLeftPanel.getName())) {
                                                System.out.println("Found left panel");
                                                JPanel leftPanel = (JPanel) compLeftPanel;

                                                int setCounter = 0;
                                                for (Component compDeleteSet : leftPanel.getComponents()) {
                                                    if (compDeleteSet.getName() != null) {
                                                        if ("deleteSet".equals(compDeleteSet.getName())) {
                                                            System.out.println("Found deleteSet button");
                                                            JButton deleteSet = (JButton) compDeleteSet;
                                                            int finalExerciseId2 = exerciseId;
                                                            int finalSetCounter = setCounter;
                                                            deleteSet.addActionListener(e -> {
                                                                ProgramPanel.deleteSet(workout.getExercisePanels().get(finalExerciseId2), finalExerciseId2, workout.getWorkoutData().getExerciseSets().get(finalExerciseId2).get(finalSetCounter), workout, ProgramPanel.setPanelHeight, setPanel, workout);

                                                            });
                                                        }
                                                    }
                                                    setCounter++;
                                                }
                                            }
                                        }
                                        workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight()+(ProgramPanel.setPanelHeight));
                                    }

                                    if (comp2.getName() != null) {

                                        if (comp2.getName().equals("exerciseNameTitlePanel")) {

                                            JPanel exerciseNameTitlePanel = (JPanel) comp2;

                                            for (Component comp3 : exerciseNameTitlePanel.getComponents()) {

                                                if (comp3.getName().equals("removeExercise")) {

                                                    JButton removeExercise = (JButton) comp3;

                                                    removeExercise.addActionListener(e -> {

                                                        workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight()-(4 * ProgramPanel.setPanelHeight));
                                                        int i = 1;// For settings the numbers of the sets correctly
                                                        for (Component comp : mainExercisePanel.getComponents()) {

                                                            if ("setPanel".equals(comp.getName())) {
                                                                workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight()-(ProgramPanel.setPanelHeight));
                                                            }

                                                        }

                                                        workout.setPreferredSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));

                                                        workout.getWorkoutData().deleteExercise(finalExerciseId);

                                                        mainExercisePanel.removeAll();

                                                        workout.repaint();

                                                        workout.revalidate();
                                                    });

                                                }
                                            }
                                        }
                                    }

                                }

                            }
                            exerciseId++;
                        }
                    }
                    workout.setPreferredSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));
                    workout.setMinimumSize(new Dimension(workout.getWidth(),  workout.getWorkoutData().getTotalWorkoutHeight()));
                    workout.setMaximumSize(new Dimension(workout.getWidth(),  workout.getWorkoutData().getTotalWorkoutHeight()));

                    workout.repaint();
                    workout.revalidate();
                }
                return workoutsList;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WorkoutsList();
    }

    public static ImageIcon readDBprofilePicture() {
        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(UserData.getEmail()).get();

            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            userData = gson.fromJson(jsonElement, HashMap.class);
            if (!userData.get("profilepicture").toString().isEmpty()) {
                byte[] data = Base64.getDecoder().decode((String) userData.get("profilepicture"));
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                ImageIcon profilePicture = (ImageIcon) objectInputStream.readObject();
                objectInputStream.close();
                return profilePicture;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void writeDBprofilePicture(ImageIcon profilePicture) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(profilePicture);
        objectOutputStream.close();

        String workoutBase64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        Map<String, Object> ref = new HashMap<>();
        ref.put("profilepicture", workoutBase64);

        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(UserData.getEmail());

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(ref);

        try {
            System.out.println("Profile picture written to database at: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean authenticateUser(String email, String password) {
        try {
            // Firebase Authentication endpoint
            String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + "AIzaSyADuzz3eZoO-UpAkNS89ksgC4G4eXAdx8w";

            // Create JSON payload
            String payload = String.format(
                    "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                    email, password);

            // Create connection
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            // Send JSON payload
            OutputStream os = connection.getOutputStream();
            os.write(payload.getBytes("UTF-8"));
            os.close();

            // Read response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Authentication successful
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println("Authentication successful: " + response.toString());
                return true;
            } else {
                // Authentication failed
                Scanner scanner = new Scanner(connection.getErrorStream());
                StringBuilder errorResponse = new StringBuilder();
                while (scanner.hasNext()) {
                    errorResponse.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println("Authentication failed: " + errorResponse.toString());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Register a new user with email and password
    public static int registerUser(String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setEmailVerified(false); // Optional: Mark as not verified initially

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            return 0;
        } catch (FirebaseAuthException e) {
            System.err.println("Error creating new user: " + e.getMessage());
            return -1;
        }
    }
}
