package se.aljr.application;

import se.aljr.application.loginpage.LoginWindow;

import java.awt.*;

/**
 * Launches the application
 */
public class Launcher {
    //Keeps track

    public static boolean isLoggedIn = false;

    public static void main(String[] args) throws InterruptedException {

        LoginWindow loginWindow = new LoginWindow((int)(Monitorsize.getWidth()/3.2),(int)(Monitorsize.getWidth()/6.4));
        loginWindow.setMinimumSize(new Dimension((int)(Monitorsize.getWidth()/3.2),(int)(Monitorsize.getWidth()/6.4)));
        while(!isLoggedIn){

            Thread.sleep(1000);

            if(!isLoggedIn){

                loginWindow.dispose();
                ApplicationWindow applicationWindow = new ApplicationWindow((int)(Monitorsize.getWidth()/2), Monitorsize.getHeight()/2, "Brogress - Gym tracker");
                break;
            }
        }
    }
}