package tests;

import model.UserData;
import dataproviders.UserdataProvider;
import org.testng.annotations.Test;


public class UserCreationTests extends TestBase{

// раз указан датапровайдер то тест будет выполняться столько раз сколько данных в датапровайдере
    @Test(dataProviderClass = UserdataProvider.class, dataProvider= "validUsersCSV")
    public void testAddNewUser(UserData user) {

        app.gotoUserPage();
        app.userHelper().create(user);
        app.gotoUserPage();
    }

}
