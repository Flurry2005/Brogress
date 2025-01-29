package se.aljr.application;

import java.awt.*;

public class Monitorsize {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int dotsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
    static int screenWidth = screenSize.width;
    static int screenHeight = screenSize.height;


    public static int getWidth(){

        return screenWidth;
    }
    public static int getHeight(){
        return screenHeight;
    }
}
