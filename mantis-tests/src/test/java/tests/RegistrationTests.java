package tests;

import org.testng.annotations.Test;

/**
 * Created by uasso on 16/10/2017. по уроку 8.4
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user1", "email1");
    }

}
