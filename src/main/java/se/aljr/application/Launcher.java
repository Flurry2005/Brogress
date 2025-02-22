package se.aljr.application;

import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.loginpage.LoginWindow;

import java.awt.*;
import java.io.IOException;

/**
 * Launches the application
 */
public class Launcher {
    //Keeps track

    public static boolean isLoggedIn = false;

    public static void main(String[] args) throws InterruptedException, IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            UserData.setIsOnline(false);
            FirebaseManager.writeDBonlineStatus();
        }));
        LoginWindow loginWindow = new LoginWindow((int)(Monitorsize.getWidth()/3),(int)(Monitorsize.getWidth()/6));
        loginWindow.setMinimumSize(new Dimension((int)(Monitorsize.getWidth()/3),(int)(Monitorsize.getWidth()/6)));
        while(!isLoggedIn){

            Thread.sleep(1000);

            if(isLoggedIn){
                loginWindow.dispose();
                UserData.setIsOnline(true);
                FirebaseManager.writeDBonlineStatus();
                ApplicationWindow applicationWindow = new ApplicationWindow((int)(Monitorsize.getWidth()/1.5), (int)(Monitorsize.getWidth()/2.66666666667), "Brogress - Gym tracker");
                break;
            }
        }
    }
}