package se.aljr.application.exercise.Excercise;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JCheckBoxFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.timing.Pause;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.aljr.application.ApplicationWindow;

import javax.swing.*;

import java.util.List;
import java.util.Arrays;

import static org.assertj.swing.edt.GuiActionRunner.execute;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

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

    @Order(1)
    @Test
    public void testSwitchToExercisesPanel() {
        // Simulate clicking the "Exercises" button
        window.button("ExercisesButton").click();

        Pause.pause(500);

    }

    //Test for being able to click on the list
    @Order(2)
    @Test
    public void testSelectBenchPressFromList() {
        window.button("ExercisesButton").click();
        Pause.pause(500);

        // Find the JList and print available items
        JListFixture exerciseList = window.list(); // Assuming it's the only JList
        List<String> listItems = Arrays.asList(exerciseList.contents());

        assertTrue(listItems.stream().anyMatch(item -> item.equalsIgnoreCase("Bench-press")),
                "Bench-press should be in the list");

        exerciseList.selectItem("Bench-press");

        String[] selectedItems = exerciseList.selection();
        assertEquals("Bench-press", selectedItems[0], "Exercise list selection should be Bench-press");
    }

    //Test to see if the information prints out on screen
    @Order(3)
    @Test
    public void testExerciseInfoIsDisplayed() {
        window.button("ExercisesButton").click();
        Pause.pause(500);

        JListFixture exerciseList = window.list();
        exerciseList.selectItem("Bench-press");

        JTextComponentFixture infoTextArea = window.textBox(new GenericTypeMatcher<JTextArea>(JTextArea.class) {
            @Override
            protected boolean isMatching(JTextArea component) {
                return component.isShowing() && component.getText().contains("The bench press is a compound");
            }
        });

        String expectedInfoText = "The bench press is a compound upper-body exercise that primarily targets the chest (pectoralis major) while also engaging the triceps and \n" +
                "anterior deltoids (front shoulders). It is a fundamental pushing exercise used to develop upper-body strength and muscle mass.";

        assertTrue(infoTextArea.text().contains(expectedInfoText),
                "Exercise info should match the expected details for Bench-press");
    }

//    //Test for Form, doesn't work yet because not implemented
//    @Order(4)
//    @Test
//    public void testExerciseFormIsDisplayed() {
//        testSelectBenchPressFromList();
//
//        JTextComponentFixture formTextArea = window.textBox(new GenericTypeMatcher<JTextArea>(JTextArea.class) {
//            @Override
//            protected boolean isMatching(JTextArea component) {
//                return component.isShowing() && component.getText().contains("Good Form and Tips");
//            }
//        });
//
//        String expectedFormText = "Good Form and Tips\n" +
//                "Keep a Moderate Arch in the Lower Back: Maintain natural spine alignment without overextending.\n" +
//                "Tuck Elbows at 45 Degrees: Protects the shoulders while maximizing chest activation.\n" +
//                "Controlled Bar Movement: Lower the bar slowly and press it back up explosively while keeping control.";
//
//        assertTrue(formTextArea.text().contains(expectedFormText),
//                "Exercise form should match the expected details for Bench-press");
//    }

//    //Test for Mistakes, doesn't work yet because not implemented
//    @Order(5)
//    @Test
//    public void testExerciseMistakesIsDisplayed() {
//        testSelectBenchPressFromList();
//
//        JTextComponentFixture mistakesTextArea = window.textBox(new GenericTypeMatcher<JTextArea>(JTextArea.class) {
//            @Override
//            protected boolean isMatching(JTextArea component) {
//                return component.isShowing() && component.getText().contains("Common Mistakes to Avoid");
//            }
//        });
//
//        String expectedMistakesText = "Common Mistakes to Avoid\n" +
//                "Lifting with Flared Elbows: Can strain the shoulders and increase injury risk.\n" +
//                "Arching the Lower Back Excessively: Leads to poor form and potential lower back injury.\n" +
//                "Bouncing the Bar Off the Chest: Reduces control and risks chest injuries.";
//
//        assertTrue(mistakesTextArea.text().contains(expectedMistakesText),
//                "Exercise mistakes should match the expected details for Bench-press");
//    }

    // Checking if Bench-press gets added in favourite
    @Test
    @Order(6)
    public void testExerciseFavouriteIsDisplayed() {
        window.button("ExercisesButton").click();

        JListFixture exerciseList = window.list("menuList");
        exerciseList.selectItem("Bench-press");

        window.button("FavouriteButton").click();

        JCheckBoxFixture checkBox = window.checkBox("FavouriteCheckBox");
        checkBox.click();

        JListFixture menuListFixture = window.list("menuList");

        List<String> displayedItems = Arrays.asList(menuListFixture.contents());

        assertTrue(displayedItems.contains("Bench-press"),
                "Bench-press should be visible in the menuList after favouring!");
    }
}