package se.aljr.application.loginpage;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FirebaseManager {
    private static String resourcePath;
    private static Firestore db;
    private static FirestoreOptions firestoreOptions;
    static {
        try {
            resourcePath = FirebaseManager.class.getClassLoader().getResource("resource.path")
                    .getPath().replace("resource.path","");

            FileInputStream serviceAccount = new FileInputStream(resourcePath+"brogress-7499c-firebase-adminsdk-fbsvc-f751df8ba3.json");
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
