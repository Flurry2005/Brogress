package se.aljr.application.loginpage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FirebaseAuthenticationManager {
    private String resourcePath;
    public FirebaseAuthenticationManager(){
        try {
            resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
            System.out.println(resourcePath);
            FileInputStream serviceAccount = new FileInputStream(resourcePath+"brogress-7499c-firebase-adminsdk-fbsvc-28c8a3e194.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://brogress-7499c.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            System.out.println("An error occurred during Firebase initialization");
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
