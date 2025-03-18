package se.aljr.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.aljr.application.loginpage.FirebaseManager;

public class LoginPanelTest {
    @Test
    public void testAuthentication(){
        assertTrue(FirebaseManager.authenticateUser("g@gmail.com","testpassword"));
    }
}
