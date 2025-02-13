package se.aljr.application;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import se.aljr.application.exercise.Excercise.ExerciseTest;
import se.aljr.application.homepage.HomePanelTest;
import se.aljr.application.homepage.MenuPanelTest;
import se.aljr.application.homepage.TopBarTest;

@Suite
@SuiteDisplayName("Custom Ordered Test Suite")
@SelectClasses({
        ApplicationWindowTest.class, // Run first and next onwards
        TopBarTest.class,
        MenuPanelTest.class,
        HomePanelTest.class,
        ExerciseTest.class

})
public class AllTests {
}