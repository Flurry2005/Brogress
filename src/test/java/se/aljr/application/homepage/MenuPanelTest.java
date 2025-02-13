package se.aljr.application.homepage;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import se.aljr.application.ApplicationWindow;

import javax.swing.*;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MenuPanelTest {

    //Testing menu buttons so they work

    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        // Enforce EDT thread safety checks
        FailOnThreadViolationRepaintManager.install();

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

    @Test
    @Order(1)
    public void testExercises() {
        window.button("ExercisesButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.NORMAL, frame.getExtendedState());
    }

    @Test
    @Order(2)
    public void testPrograms() {
        window.button("ProgramButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.NORMAL, frame.getExtendedState());
    }

    @Test
    @Order(3)
    public void testSettings() {
        window.button("SettingsButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.NORMAL, frame.getExtendedState());
    }

    @Test
    @Order(4)
    public void testHome() {
        window.button("HomeButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.NORMAL, frame.getExtendedState());
    }
}