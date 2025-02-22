package se.aljr.application.loginpage;

import com.google.api.client.json.Json;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.chatpanel.ChatPanel;
import se.aljr.application.homepage.HomePanel;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.programplanner.Workout;
import se.aljr.application.programplanner.WorkoutSet;
import se.aljr.application.programplanner.WorkoutsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FirebaseManager {
    private static String resourcePath;
    private static Firestore db;
    private static FirestoreOptions firestoreOptions;
    private static StorageOptions storageOptions;
    private static final ArrayList<Thread> activeThreads = new ArrayList<>();
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

    public static void readDBlistenToClientChats() throws InterruptedException {

        new Thread(()->{
            // Referens till användarens dokument
            DocumentReference docRef = db.collection("chats").document(UserData.getEmail());

            // Lyssna på ändringar i fältet "isOnline"
            docRef.addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    System.err.println("Couldnt listen to chats document of user: "+UserData.getEmail() + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    for(Friend friend : FriendsList.getFriendArrayList()){
                        System.out.println("Updated chat"+FriendsList.getFriendArrayList().size());
                        ArrayList<HashMap<String,String>> newChat = readDBreadMessageHistory(friend.getFriendEmail(), UserData.getEmail());
                        System.out.println(newChat);
                        friend.setChat(newChat);
                        if(friend==ChatPanel.selectedFriend){

                        }

                    }

                }
                ChatPanel.updateChat();
            });
            try {

                Thread.sleep(Long.MAX_VALUE);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();


    }

    public static void writeDBwriteMessageHistory(String friendEmail,String yourEmail, String message){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ArrayList<HashMap<String,String>> myMessageHistory = readDBreadMessageHistory(friendEmail, yourEmail);

        HashMap<String,String> messageTemp = new HashMap<>();
        messageTemp.put(UserData.getEmail(),message);

        myMessageHistory.add(messageTemp);

        JsonElement json = gson.toJsonTree(myMessageHistory);
        FieldPath field = FieldPath.of(friendEmail);

        DocumentReference docRef = db.collection("chats").document(yourEmail);

        ApiFuture<WriteResult> result = docRef.update(field,gson.toJson(json));

        if(!friendEmail.equals(UserData.getEmail())){
            writeDBwriteMessageHistory(UserData.getEmail(),friendEmail,message);
        }

        try {
            System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<HashMap<String, String>> readDBreadMessageHistory(String friendEmail, String yourEmail) {
        try {
            Gson gson = new Gson();
            ArrayList<HashMap<String, String>> friendsMap = new ArrayList<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("chats").document(yourEmail).get();
            DocumentSnapshot document = snapshot.get();

            if (document.exists()) {
                FieldPath field = FieldPath.of(friendEmail);
                Object rawData = document.get(field);

                if (rawData != null && rawData instanceof String && !((String) rawData).isEmpty()) {
                    String jsonString = (String) rawData;

                    Type listType = new TypeToken<ArrayList<HashMap<String, String>>>() {}.getType();
                    friendsMap = gson.fromJson(jsonString, listType);
                    return friendsMap;
                }else{
                    return new ArrayList<HashMap<String,String>>();
                }
            }else{
                throw new NullPointerException();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<HashMap<String,String>>();
        }
    }

    public static void writeDBsendFriendRequest(String email){
        HashMap<String,String> newFriendRequest= readDBgetFriendRequests(email);
        HashMap<String,String> usersFriends = readDBfriends(email,true);
        if(newFriendRequest!=null&&usersFriends!=null){
            if(!newFriendRequest.containsKey(UserData.getEmail())&&!usersFriends.containsKey(UserData.getEmail())&&!email.equals(UserData.getEmail())){
                newFriendRequest.put(UserData.getEmail(), UserData.getUserName());

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(newFriendRequest);

                Map<String, Object> user = new HashMap<>();
                user.put("friendrequests", json);



                // Referens till dokumentet i "users" collection
                DocumentReference docRef = db.collection("users").document(email);

                // Skriv data och vänta på resultat
                ApiFuture<WriteResult> result = docRef.update(user);

                try {
                    System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeDBacceptFriendRequest(String email){
        HashMap<String,String> myFriendRequests= readDBgetFriendRequests(UserData.getEmail());
        HashMap<String,String> usersFriends = readDBfriends(email,true);
        if(myFriendRequests!=null&&usersFriends!=null){
            if(!myFriendRequests.containsKey(UserData.getEmail())&&!usersFriends.containsKey(UserData.getEmail())&&!email.equals(UserData.getEmail())){
                for(Map.Entry<String,String> entry : myFriendRequests.entrySet()){
                    if(entry.getKey().equals(email)){
                        myFriendRequests.remove(entry.getKey());
                        break;
                    }
                }

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(myFriendRequests);

                Map<String, Object> newUserFriendRequests = new HashMap<>();
                newUserFriendRequests.put("friendrequests", json);



                // Referens till dokumentet i "users" collection
                DocumentReference docRef = db.collection("users").document(UserData.getEmail());

                // Skriv data och vänta på resultat
                ApiFuture<WriteResult> result = docRef.update(newUserFriendRequests);

                writeDBfriends(UserData.getEmail());
                writeDBfriends(email);
                HomePanel.updateFriends();


                FieldPath fieldClientUser = FieldPath.of(UserData.getEmail());
                FieldPath fieldFriend = FieldPath.of(email);

                db.collection("chats").document(UserData.getEmail()).update(fieldFriend,"");

                db.collection("chats").document(email).update(fieldClientUser,"");

                try {
                    System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static HashMap<String,String> readDBgetFriendRequests(String email){
        try {
            Gson gson = new Gson();
            HashMap<String, String> friendRequestsMap = new HashMap<>();

            // Hämta dokumentet från Firestore
            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(email).get();
            DocumentSnapshot document = snapshot.get();

            if (document.exists()) {
                // Hämta JSON-strängen från "friends"
                String friendsJson = (String) document.get("friendrequests");
                friendRequestsMap = gson.fromJson(friendsJson, HashMap.class);

            }

            return friendRequestsMap;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String,String>();
        }


    }

    public static void writeDBonlineStatus(){

        Map<String, Object> user = new HashMap<>();
        user.put("isOnline", UserData.isIsOnline()?"true":"false");


        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(UserData.getEmail());

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(user);

        try {
            System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDBlistenToFriendsOnlineStatus() throws InterruptedException {

                for(Thread thread : activeThreads){
                    thread.interrupt();
                }
                activeThreads.clear();

                for(Friend friend : FriendsList.getFriendArrayList()){
                    Thread listnerThread = new Thread(()->{
                    // Referens till användarens dokument
                    DocumentReference docRef = db.collection("users").document(friend.getFriendEmail());

                    // Lyssna på ändringar i fältet "isOnline"
                    docRef.addSnapshotListener((snapshot, e) -> {
                        if (e != null) {
                            System.err.println("Listen failed: " + e);
                            return;
                        }

                        if (snapshot != null && snapshot.exists()) {
                            // Hämta fältet "isOnline" som en String
                            String newIsOnline = snapshot.getString("isOnline");

                            if (newIsOnline != null) {
                                friend.setOnline(newIsOnline.equals("true")?true:false); // Uppdatera variabeln
                                friend.updateOnlineStatus();
                                System.out.println("Updated isOnline "+friend.getFriendEmail()+" :" + newIsOnline);
                            }
                        } else {
                            System.out.println("Document does not exist.");
                        }
                    });

                    // Håll programmet igång
                    System.out.println("Listening for 'isOnline' of user :"+friend.getFriendEmail());
                    try {
                        System.out.println("Thread count listening to online status: "+activeThreads.size());
                        System.out.println("Amount of friends: "+FriendsList.getFriendArrayList().size());
                        Thread.sleep(Long.MAX_VALUE);

                        //Thread.currentThread().interrupt();
                    } catch (InterruptedException e) {
                        System.out.println("Thread stopped listening to friends online status");
                    }

                    });
                    activeThreads.add(listnerThread);
                    listnerThread.start();
                }

    }

    public static void readDBlistenToClientUserFriendsList() throws InterruptedException {

            new Thread(()->{
                // Referens till användarens dokument
                DocumentReference docRef = db.collection("users").document(UserData.getEmail());

                // Lyssna på ändringar i fältet "isOnline"
                docRef.addSnapshotListener((snapshot, e) -> {
                    if (e != null) {
                        System.err.println("Listen failed: " + e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        // Hämta fältet "isOnline" som en String
                        String friends = snapshot.getString("friends");

                        if (friends != null) {
                            HomePanel.updateFriends();
                            ChatPanel.updateRequestsPanel();
                        }
                    } else {
                        System.out.println("Document does not exist.");
                    }
                });

                // Håll programmet igång
                System.out.println("Listening for Firestore changes on 'friends' of user "+UserData.getEmail());
                try {

                    Thread.sleep(Long.MAX_VALUE);

                    //Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }).start();


    }

    public static void writeDBfriends(String email){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<String, String> friends = new HashMap<>();

        if(email.equals(UserData.getEmail())){
            for(Friend friend : FriendsList.getFriendArrayList()){
                friends.put(friend.getFriendEmail(), friend.getFriendName());
            }
        }else{
            friends = readDBfriends(email,true);
            friends.put(UserData.getEmail(),UserData.getUserName());
        }
        String json = gson.toJson(friends);

        Map<String, Object> user = new HashMap<>();
        user.put("friends", json);


        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(email);

        // Skriv data och vänta på resultat
        ApiFuture<WriteResult> result = docRef.update(user);

        try {
            System.out.println("Uppdaterat vid: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static HashMap<String,String> readDBfriends(String email, boolean readOnly){
        try {
            Gson gson = new Gson();
            HashMap<String, String> friendsMap = new HashMap<>();

            // Hämta dokumentet från Firestore
            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(email).get();
            DocumentSnapshot document = snapshot.get();

            if (document.exists()) {
                // Hämta JSON-strängen från "friends"
                String friendsJson = (String) document.get("friends");

                if (friendsJson != null) {
                    // Konvertera JSON-strängen till HashMap
                    friendsMap = gson.fromJson(friendsJson, HashMap.class);

                    // Skriv ut resultatet
                    System.out.println("Friends Map: " + friendsMap);
                } else {
                    System.out.println("Nyckeln 'friends' är null eller existerar inte.");
                }
            } else {
                System.out.println("Dokumentet existerar inte.");
            }

           if(!readOnly){
               for(Map.Entry<String, String> entry : friendsMap.entrySet()){
                   HashMap<String, String> finalUserData = friendsMap;
                   FriendsList.getFriendArrayList().add(new Friend(true){
                       {
                           setFriendEmail(entry.getKey());
                           setFriendName(entry.getValue());
                       }
                   });
               }
           }
            return friendsMap;


        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
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
        user.put("theme","dark");
        user.put("friends","{}");
        user.put("isOnline","");
        user.put("friendrequests","{}");
        user.put("Created_Exercises","");
        user.put("Favorite_Exercises","");


        // Referens till dokumentet i "users" collection
        DocumentReference docRef = db.collection("users").document(email);

        //Create document for chats of the user
        HashMap<String, Object> data = new HashMap<>();
        data.put("createdAt", FieldValue.serverTimestamp());
        db.collection("chats").document(email).set(data);

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
        user.put("theme", UserData.getTheme());


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
            UserData.setTheme(userData.get("theme").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeWorkoutIcons(WorkoutsList workoutsList){
        for(Workout workout : workoutsList){
            for (Component comp1 : workout.getComponents()) {
                if(comp1.getName()!=null){
                    if (comp1.getName().equals("mainExercisePanel")) {
                        JPanel mainExercisePanel = (JPanel) comp1;
                        for (Component comp2 : mainExercisePanel.getComponents()) {
                            if ("addSet".equals(comp2.getName())) {
                                JButton addSet = (JButton) comp2;
                                /**/
                                addSet.setIcon(null);
                            }
                            if ("setPanel".equals(comp2.getName())) {
                                JPanel setPanel = (JPanel) comp2;
                                for (Component compRight : setPanel.getComponents()){
                                    if(compRight.getName()!=null){
                                        if("rightPanel".equals(compRight.getName())){
                                            JPanel rightPanel = (JPanel) compRight;
                                            for(Component compMoveSetUp : rightPanel.getComponents()){
                                                if(compMoveSetUp.getName()!=null){
                                                    if("moveSetUp".equals(compMoveSetUp.getName())){
                                                        JButton moveSetUp = (JButton) compMoveSetUp;
                                                        /**/
                                                        moveSetUp.setIcon(null);
                                                    }
                                                    if("moveSetDown".equals(compMoveSetUp.getName())){
                                                        JButton moveSetDown = (JButton) compMoveSetUp;
                                                        /**/
                                                        moveSetDown.setIcon(null);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                for (Component compLeftPanel : setPanel.getComponents()) {
                                    if ("leftPanel".equals(compLeftPanel.getName())) {
                                        JPanel leftPanel = (JPanel) compLeftPanel;
                                        for (Component compDeleteSet : leftPanel.getComponents()) {
                                            if (compDeleteSet.getName() != null) {
                                                if ("deleteSet".equals(compDeleteSet.getName())) {
                                                    JButton deleteSet = (JButton) compDeleteSet;
                                                    /**/
                                                    deleteSet.setIcon(null);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (comp2.getName() != null) {

                                if (comp2.getName().equals("exerciseNameTitlePanel")) {

                                    JPanel exerciseNameTitlePanel = (JPanel) comp2;

                                    for (Component comp3 : exerciseNameTitlePanel.getComponents()) {

                                        if (comp3.getName().equals("removeExercise")) {

                                            JButton removeExercise = (JButton) comp3;
                                            /**/
                                            removeExercise.setIcon(null);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addWorkoutIcons(WorkoutsList workoutsList){
        for(Workout workout : workoutsList){
            for (Component comp1 : workout.getComponents()) {
                if(comp1.getName()!=null){
                    if (comp1.getName().equals("mainExercisePanel")) {
                        JPanel mainExercisePanel = (JPanel) comp1;
                        for (Component comp2 : mainExercisePanel.getComponents()) {
                            if ("addSet".equals(comp2.getName())) {
                                JButton addSet = (JButton) comp2;
                                /**/
                                addSet.setIcon(ProgramPanel.scaledNewSetIcon);

                            }
                            if ("setPanel".equals(comp2.getName())) {
                                JPanel setPanel = (JPanel) comp2;
                                for (Component compRight : setPanel.getComponents()){
                                    if(compRight.getName()!=null){
                                        if("rightPanel".equals(compRight.getName())){
                                            JPanel rightPanel = (JPanel) compRight;
                                            for(Component compMoveSetUp : rightPanel.getComponents()){
                                                if(compMoveSetUp.getName()!=null){
                                                    if("moveSetUp".equals(compMoveSetUp.getName())){
                                                        JButton moveSetUp = (JButton) compMoveSetUp;
                                                        /**/
                                                        moveSetUp.setIcon(ProgramPanel.scaledMoveSetUpIcon);
                                                    }
                                                    if("moveSetDown".equals(compMoveSetUp.getName())){
                                                        JButton moveSetDown = (JButton) compMoveSetUp;
                                                        /**/
                                                        moveSetDown.setIcon(ProgramPanel.scaledMoveSetDownIcon);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                for (Component compLeftPanel : setPanel.getComponents()) {
                                    if ("leftPanel".equals(compLeftPanel.getName())) {
                                        JPanel leftPanel = (JPanel) compLeftPanel;
                                        for (Component compDeleteSet : leftPanel.getComponents()) {
                                            if (compDeleteSet.getName() != null) {
                                                if ("deleteSet".equals(compDeleteSet.getName())) {
                                                    JButton deleteSet = (JButton) compDeleteSet;
                                                    /**/
                                                    deleteSet.setIcon(ProgramPanel.scaledRemoveSetIcon);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (comp2.getName() != null) {

                                if (comp2.getName().equals("exerciseNameTitlePanel")) {

                                    JPanel exerciseNameTitlePanel = (JPanel) comp2;

                                    for (Component comp3 : exerciseNameTitlePanel.getComponents()) {

                                        if (comp3.getName().equals("removeExercise")) {

                                            JButton removeExercise = (JButton) comp3;
                                            /**/
                                            removeExercise.setIcon(ProgramPanel.scaledRemoveExerciseIcon);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void writeDBCreatedExercises(ArrayList<Exercise> createdExerciseList) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);

        outputStream.writeObject(createdExerciseList);
        outputStream.close();

        String exercise64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        DocumentReference documentReference = db.collection("users").document(UserData.getEmail());
        documentReference.update("Created_Exercises", exercise64);
    }

    public static void writeDBFavoriteExercises(HashSet<Exercise> favoriteList) throws IOException {
        HashSet<Exercise> temp = new HashSet<>();
        temp.addAll(favoriteList);
        for (Exercise exercise : temp) {
            exercise.removeImageIcon();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(temp);
        objectOutputStream.close();

        String exercise64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        DocumentReference documentReference = db.collection("users").document(UserData.getEmail());
        documentReference.update("Favorite_Exercises", exercise64);

    }

    public static void writeDBworkout(WorkoutsList workoutsList) throws IOException {
        removeWorkoutIcons(workoutsList);

        /*---------Creates a storage object connected to the database---------*/
        Storage storage = storageOptions.getService();


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(workoutsList);
        objectOutputStream.close();

        /*---------Writes the byte stream into a string representation---------*/
        String workoutBase64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());


        /*---------Specifies the cloud storage url and the name of the file to save---------*/
        String bucketName = "brogress-7499c.firebasestorage.app"; // Bucket name
        String fileName = UserData.getEmail() + "_workouts.txt"; // workout file name

        // Konvertera String till byte-array
        byte[] bytes = workoutBase64.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        /*---------Creates a blob file in the cloud storage---------*/
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();

        /*---------uploads the file to the database---------*/
        storage.create(blobInfo, inputStream);

        /*---------Specifies the url to the file created---------*/
        String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;

        /*---------Key value pair to save the workouts url---------*/
        Map<String, Object> ref = new HashMap<>();
        ref.put("workouts", publicUrl);

        /*---------Document reference where the url is to be saved---------*/
        DocumentReference docRef = db.collection("users").document(UserData.getEmail());

        /*---------Writes the url to the firestore database---------*/
        ApiFuture<WriteResult> result = docRef.update(ref);

        System.out.println("Text har laddats upp som fil: " + fileName);
        System.out.println("Publik URL: " + "https://storage.googleapis.com/" + bucketName + "/" + fileName);

        addWorkoutIcons(workoutsList);

    }

    public static WorkoutsList readDBworkout(ProgramPanel programPanel){
        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            /*---------Gets a snapshot of the document of the user in the firestore database---------*/
            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(UserData.getEmail()).get();

            /*---------Converts the documentSnapshot to a json element--------*/
            DocumentSnapshot document = snapshot.get();
            JsonElement jsonElement = gson.toJsonTree(document.getData());

            /*---------Converts the json to a HashMap---------*/
            userData = gson.fromJson(jsonElement, HashMap.class);

            int height = programPanel.getHeight();

            System.out.print(programPanel.getHeight());
            /*---------Checks if the user has stored a workout or not---------*/
            if (!userData.get("workouts").toString().isEmpty()) {
                /*---------Creates a storage object connected to the database---------*/
                Storage storage = storageOptions.getService();

                /*---------Gets the workout url stored in the firestore of the user---------*/
                String fileName = userData.get("workouts").toString();

                /*---------Sets up the variables needed for collecting the workout data from the cloud storage---------*/
                URL url = new URL(fileName);
                String path = url.getPath();
                String bucketName = "brogress-7499c.firebasestorage.app";
                String userWorkoutFileName = path.substring(1).split("/")[1];

                byte[] data1 = storage.get(BlobId.of(bucketName,userWorkoutFileName)).getContent();
                String fileContent = new String(data1, StandardCharsets.UTF_8);

                /*---------Decodes the base64 string to an object (WorkoutsList)---------*/
                byte[] data = Base64.getDecoder().decode(fileContent);
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                WorkoutsList workoutsList = (WorkoutsList) objectInputStream.readObject();
                objectInputStream.close();

                /*---------Reattaches all buttons listeners and data needed to load the WorkoutsList back to the program---------*/
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
                addWorkoutIcons(workoutsList);
                return workoutsList;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WorkoutsList();
    }

    public static HashSet<Exercise> readDBfavoriteExercises () throws ExecutionException, InterruptedException, IOException, ClassNotFoundException {
        DocumentReference documentReference = db.collection("users").document(UserData.getEmail());

        DocumentSnapshot documentSnapshot = documentReference.get().get();

        String base64String = documentSnapshot.getString("Favorite_Exercises");

        if(!base64String.isEmpty()&&!base64String.equals("")){
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            ObjectInputStream inStream = new ObjectInputStream(bis);
            inStream.close();

            return (HashSet<Exercise>) inStream.readObject();
        }else{
            return new HashSet<Exercise>();
        }


    }

    public static ArrayList<Exercise> readDBcreatedExercises() throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {

        DocumentReference documentReference = db.collection("users").document(UserData.getEmail());

        DocumentSnapshot documentSnapshot = documentReference.get().get();
        String base64String = documentSnapshot.getString("Created_Exercises");


        if(!base64String.isEmpty()&&!base64String.equals("")){
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            ObjectInputStream inStream = new ObjectInputStream(bis);
            inStream.close();

            return (ArrayList<Exercise>) inStream.readObject();
        }else{
            return new ArrayList<Exercise>();
        }


    }

    public static ImageIcon readDBprofilePicture(String email) {
        try {
            Gson gson = new Gson();
            HashMap<String, Object> userData = new HashMap<>();

            ApiFuture<DocumentSnapshot> snapshot = db.collection("users").document(email).get();

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
