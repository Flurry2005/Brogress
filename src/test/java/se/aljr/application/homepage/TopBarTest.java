package se.aljr.application.homepage;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import se.aljr.application.ApplicationWindow;

import javax.swing.*;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TopBarTest {

    //This checks if all buttons in TopBar works, so checks minimize and fullscreen + exit (Not working atm)

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

    //Test for rezising
//    @Test
//    public void testWindowResize() {
//        window.target().setSize(1400, 900);
//        window.target().revalidate();
//        window.target().repaint();
//
//        Dimension newSize = window.target().getSize();
//        assertTrue(newSize.width == 1400 && newSize.height == 900, "Window should be resized correctly");
//    }

//    //Test for exitbutton
//    @Test
//    public void testExitButtonClosesWindow() {
//        window.button("exitButton").click();
//        assert !window.target().isDisplayable();
//    }

    //Test for fullscreen
    @Test
    @Order(5)
    public void testFullscreenToggle() {
        window.button("fullscreenButton").click();
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.MAXIMIZED_BOTH, frame.getExtendedState());
        window.button("fullscreenButton").click();
        assertEquals(JFrame.NORMAL, frame.getExtendedState());
    }

    //Test for minimize client
    @Test
    @Order(6)
    public void testMinimizeButton() {
        window.button("minimizeButton").click();
        // Verify the window state is minimized
        JFrame frame = (JFrame) window.target();
        assertEquals(JFrame.ICONIFIED, frame.getExtendedState());
    }
}