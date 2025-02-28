package se.aljr.application;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MonitorsizeTest {

    @Test
    void getWidth() {
        int expectedWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        int actualWidth = Monitorsize.getWidth();

        assertEquals(expectedWidth, actualWidth, "The screen width should match the system's screen width");
    }

    @Test
    void getHeight() {
        int expectedHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        int actualHeight = Monitorsize.getHeight();

        assertEquals(expectedHeight, actualHeight, "The screen height should match the system's screen height");
    }
}