package se.aljr.application;
/*
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import javax.swing.*;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationWindowTest {

    //This checks if the panel works, with everything inside it

    private FrameFixture window;

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Enforce EDT thread safety checks
        FailOnThreadViolationRepaintManager.install();

        // Create the full Application Window
        JFrame frame = execute(() -> {
            ApplicationWindow testFrame = new ApplicationWindow(1200, 800, "Test App");
            testFrame.setVisible(true);
            return testFrame;
        });

        window = new FrameFixture(frame);
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    // Test window is visible and correct size
    @Test
    public void testWindowIsCreated() {
        JFrame frame = (JFrame) window.target();
        assertEquals("Test App", frame.getTitle());
        assertTrue(frame.isShowing(), "Window should be visible");
    }

    // Test menu buttons switch panels correctly
    @Test
    public void testPanelSwitching() {
        window.button("ExercisesButton").click();
        ApplicationWindow.switchWindow("exercises");
        assertEquals(2, ApplicationWindow.pageSelector, "Exercises panel should be active");

        window.button("ProgramButton").click();
        ApplicationWindow.switchWindow("program");
        assertEquals(3, ApplicationWindow.pageSelector, "Program panel should be active");

        window.button("SettingsButton").click();
        ApplicationWindow.switchWindow("settings");
        assertEquals(4, ApplicationWindow.pageSelector, "Settings panel should be active");

        window.button("HomeButton").click();
        ApplicationWindow.switchWindow("home");
        assertEquals(1, ApplicationWindow.pageSelector, "Home panel should be active");
    }

//    // Test window resizing
//    @Test
//    public void testWindowResize() {
//        window.target().setSize(1400, 900);
//        window.target().revalidate();
//        window.target().repaint();
//
//        Dimension newSize = window.target().getSize();
//        assertEquals(1400, newSize.width);
//        assertEquals(900, newSize.height);
//    }

    // Test minimize button
    @Test
    public void testMinimizeButton() {
        window.button("minimizeButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.ICONIFIED, frame.getExtendedState(), "Window should be minimized");
    }

    // Test fullscreen toggle
    @Test
    public void testFullscreenToggle() {
        window.button("fullscreenButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.MAXIMIZED_BOTH, frame.getExtendedState(), "Window should be fullscreen");

        window.button("fullscreenButton").click();
        assertEquals(JFrame.NORMAL, frame.getExtendedState(), "Window should return to normal size");
    }

//    // Test exit button closes window
//    @Test
//    public void testExitButtonClosesWindow() {
//        window.button("exitButton").click();
//        assertTrue(!window.target().isDisplayable(), "Window should be closed after clicking exit button");
//    }
}

 */