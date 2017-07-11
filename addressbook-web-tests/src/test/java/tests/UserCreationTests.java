package tests;

import model.UserData;
import org.testng.annotations.Test;


public class UserCreationTests extends TestBase{

    @Test
    public void testAddNewUser() {

        app.gotoUserPage();
        app.getUserHelper().initUserCreation();

        app.getUserHelper().fillUserForm(new UserData("Natasha", "Smith", "25 oktabrja", "3251645", "kuku@gmIL,COM"));
        app.getUserHelper().submitUserCreation();
        app.gotoUserPage();
    }

}
