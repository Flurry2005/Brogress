package se.aljr.application;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AppThemeColorsTest {

    @Test
    void lightMode() {
        AppThemeColors.lightMode();
        assertEquals(new Color(255, 255, 255), AppThemeColors.PRIMARY, "PRIMARY color should be white in light mode");
        assertEquals(new Color(200, 200, 200), AppThemeColors.SECONDARY, "SECONDARY color should be light gray in light mode");
        assertEquals(new Color(195, 195, 195), AppThemeColors.panelColor, "panelColor should match light mode value");
        assertEquals(new Color(220, 220, 220), AppThemeColors.textFieldColor, "textFieldColor should match light mode value");
        assertEquals(new Color(255, 255, 255), AppThemeColors.buttonBG, "buttonBG should be white in light mode");
        assertEquals(new Color(240, 240, 240), AppThemeColors.buttonBGHovered, "buttonBGHovered should match light mode value");
        assertEquals(new Color(210, 210, 210), AppThemeColors.buttonBGSelected, "buttonBGSelected should match light mode value");
        assertEquals(Color.BLACK, AppThemeColors.foregroundColor, "foregroundColor should be black in light mode");
    }

    @Test
    void darkMode() {
        AppThemeColors.darkMode();

        assertEquals(new Color(51, 51, 51), AppThemeColors.PRIMARY, "PRIMARY color should be dark gray in dark mode");
        assertEquals(new Color(31, 31, 31), AppThemeColors.SECONDARY, "SECONDARY color should be darker gray in dark mode");
        assertEquals(new Color(21, 21, 21), AppThemeColors.panelColor, "panelColor should match dark mode value");
        assertEquals(new Color(22, 22, 22), AppThemeColors.textFieldColor, "textFieldColor should match dark mode value");
        assertEquals(new Color(51, 51, 51, 255), AppThemeColors.buttonBG, "buttonBG should match dark mode value");
        assertEquals(new Color(40, 40, 40), AppThemeColors.buttonBGHovered, "buttonBGHovered should match dark mode value");
        assertEquals(new Color(30, 30, 30), AppThemeColors.buttonBGSelected, "buttonBGSelected should match dark mode value");
        assertEquals(Color.WHITE, AppThemeColors.foregroundColor, "foregroundColor should be white in dark mode");
    }
}