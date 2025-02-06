package se.aljr.application.loginpage;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.Acl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.programplanner.Workout;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class FirebaseManager {
    private static String resourcePath;
    private static Firestore db;
    private static FirestoreOptions firestoreOptions;
    static {
        try {
            resourcePath = FirebaseManager.class.getClassLoader().getResource("resource.path")
                    .getPath().replace("resource.path","");

            FileInputStream serviceAccount = new FileInputStream(resourcePath+"brogress-7499c-firebase-adminsdk-fbsvc-7d0008d7db.json");
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
    public static void readDBUserInfo(String email){

        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(email).get();

            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            userData = gson.fromJson(jsonElement, HashMap.class);

            UserData.setUserName(userData.get("name").toString());
            UserData.setEmail(userData.get("email").toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDBworkout(Workout workout) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(workout);
        objectOutputStream.close();

        String workoutBase64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        Map<String, Object> workoutsMap = new HashMap<>();
        workoutsMap.put("workouts", workoutBase64);

        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(UserData.getEmail());

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(workoutsMap);

        try {
            System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Workout readDBworkout(ProgramPanel programPanel){
        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(UserData.getEmail()).get();

            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            userData = gson.fromJson(jsonElement, HashMap.class);

            int height = programPanel.getHeight();

            System.out.print(programPanel.getHeight());
            if(userData.get("workouts")!=null){
                byte[] data = Base64.getDecoder().decode((String) userData.get("workouts"));
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                Workout workout = (Workout) objectInputStream.readObject();
                objectInputStream.close();
                int exerciseId = 1;
                for(Component comp1 : workout.getComponents()){
                    System.out.println("Added 4 panels : "+(float) (4 * height) /19);

                    if(comp1.getName().equals("mainExercisePanel")){
                        ProgramPanel.totalHeight += (4 * ProgramPanel.setPanelHeight);
                        JPanel mainExercisePanel = (JPanel) comp1;
                        for(Component comp2 : mainExercisePanel.getComponents()){
                            int finalExerciseId = exerciseId;
                            if("addSet".equals(comp2.getName())){
                                JButton addSet = (JButton) comp2;
                                int finalExerciseId1 = exerciseId;
                                addSet.addActionListener(e -> {
                                    ProgramPanel.totalHeight += ProgramPanel.setPanelHeight; //Lägger till höjden settet som läggs till
                                    ProgramPanel.addSet(finalExerciseId1, workout.getWorkoutSet().getExercise(), mainExercisePanel, ProgramPanel.setPanelHeight, workout);
                                    workout.setPreferredSize(new Dimension(workout.getWidth(), (int) ProgramPanel.totalHeight));
                                    workout.revalidate();
                                    workout.repaint();

                                });
                            }

                            if ("setPanel".equals(comp2.getName())) {
                                JPanel setPanel = (JPanel) comp2;
                                System.out.println("Added hieght for set panel: "+ (float) height /19);
                                for(Component compLeftPanel : setPanel.getComponents()){
                                    if("leftPanel".equals(compLeftPanel.getName())){
                                        System.out.println("Found left panel");
                                        JPanel leftPanel = (JPanel) compLeftPanel;

                                        int setCounter = 1;
                                        for(Component compDeleteSet : leftPanel.getComponents()){
                                            if(compDeleteSet.getName()!=null){
                                                if("deleteSet".equals(compDeleteSet.getName())){
                                                    System.out.println("Found deleteSet button");
                                                    JButton deleteSet = (JButton) compDeleteSet;
                                                    int finalExerciseId2 = exerciseId;
                                                    int finalSetCounter = setCounter;
                                                    deleteSet.addActionListener(e -> {
                                                       ProgramPanel.deleteSet(workout.getExercisePanels().get(finalExerciseId2), finalExerciseId2, finalSetCounter, workout, ProgramPanel.setPanelHeight, setPanel, workout);

                                                    });
                                                }
                                           }
                                            setCounter++;
                                        }
                                    }
                                }
                                ProgramPanel.totalHeight += ProgramPanel.setPanelHeight;
                            }

                            if(comp2.getName()!=null){
                                if(comp2.getName().equals("exerciseNameTitlePanel")){
                                    JPanel exerciseNameTitlePanel = (JPanel) comp2;
                                    for(Component comp3 : exerciseNameTitlePanel.getComponents()){
                                        if(comp3.getName().equals("removeExercise")){
                                            JButton removeExercise = (JButton) comp3;


                                            removeExercise.addActionListener(e -> {
                                                ProgramPanel.totalHeight -=  (4 * ProgramPanel.setPanelHeight);
                                                int i = 1;// For settings the numbers of the sets correctly
                                                for (Component comp : mainExercisePanel.getComponents()) {
                                                    if ("setPanel".equals(comp.getName())) {
                                                        ProgramPanel.totalHeight -= ProgramPanel.setPanelHeight;
                                                    }
                                                }
                                                workout.setPreferredSize(new Dimension(workout.getWidth(), (int) ProgramPanel.totalHeight));
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
                workout.setPreferredSize(new Dimension(workout.getWidth(), (int) ProgramPanel.totalHeight));
                workout.setMinimumSize(new Dimension(workout.getWidth(), (int) ProgramPanel.totalHeight));
                workout.setMaximumSize(new Dimension(workout.getWidth(), (int) ProgramPanel.totalHeight));

                workout.repaint();
                workout.revalidate();

                System.out.println("Supposed: "+ProgramPanel.totalHeight+" Actual: "+workout.getHeight());
                return workout;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Workout();
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
    public static void registerUser(String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setEmailVerified(false); // Optional: Mark as not verified initially

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            System.err.println("Error creating new user: " + e.getMessage());
        }
    }
}
