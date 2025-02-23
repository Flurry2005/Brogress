package se.aljr.application;

import se.aljr.application.settings.SettingsPanel;

import java.awt.*;

public class AppThemeColors {

    public static Color PRIMARY;
    public static Color SECONDARY;
    public static Color panelColor;
    public static Color textFieldColor;
    public static Color buttonBG;
    public static Color buttonBGHovered;
    public static Color buttonBGSelected;
    public static Color foregroundColor;


    public static void lightMode(){
        PRIMARY = new Color(255,255,255);
        SECONDARY = new Color(200,200,200);
        panelColor = new Color(195,195,195);
        textFieldColor = new Color(220,220,220);

        /*
        PRIMARY = new Color(255,0,0);
        SECONDARY = new Color(200,0,0);
        panelColor = new Color(195,0,0);
        textFieldColor = new Color(220,0,0);

         */

        buttonBG = new Color(255, 255, 255);
        buttonBGHovered = new Color(240,240,240);
        buttonBGSelected = new Color(210,210,210);



        foregroundColor = Color.BLACK;
        System.out.println("Updated theme colors to LIGHTMODE");
    }

    public static void darkMode(){
        PRIMARY = new Color(51,51,51);
        SECONDARY = new Color(31,31,31);
        panelColor = new Color(21,21,21);
        textFieldColor = new Color(22,22,22);

        buttonBG = new Color(51, 51, 51,255);
        buttonBGHovered = new Color(40,40,40);
        buttonBGSelected = new Color(30,30,30);

        foregroundColor = Color.WHITE;
        System.out.println("Updated theme colors to DARKMODE");
    }

    public static void updateThemeColors(){
        switch (SettingsPanel.currentTheme){
            case "light"-> lightMode();
            case "dark"-> darkMode();
        }
    }



}
