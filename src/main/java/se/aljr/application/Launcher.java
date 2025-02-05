package se.aljr.application;

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

        LoginWindow loginWindow = new LoginWindow((int)(Monitorsize.getWidth()/3),(int)(Monitorsize.getWidth()/6));
        loginWindow.setMinimumSize(new Dimension((int)(Monitorsize.getWidth()/3),(int)(Monitorsize.getWidth()/6)));
        while(!isLoggedIn){

            Thread.sleep(1000);

            if(!isLoggedIn){
                //UserData.setUserAge(30);
                //UserData.setUserHeight(150);
                //UserData.setUserName("Johan Kronholm");
                //UserData.setUserWeight(150);
                loginWindow.dispose();
                ApplicationWindow applicationWindow = new ApplicationWindow((int)(Monitorsize.getWidth()/1.5), (int)(Monitorsize.getHeight()/1.5), "Brogress - Gym tracker");
                break;
            }
        }
    }
}