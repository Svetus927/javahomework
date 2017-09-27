package tests;

import model.GroupData;
import model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;

/**
 * Created by uasso on 11/09/2017.
 */
public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.gotoUserPage();
        if  (! app.userHelper().isThereAUser()) {
            UserData user = new UserData().withFirstname("Petya").
                    withLastname("Petrov").
                    withHomephone("123456").
                    withMobilephone("+7123456").
                    withWorkphone("654321");
            app.userHelper().create(user);
            app.gotoUserPage();
        }
    }

    @Test
    public void testGroupModification() {

        HashSet<UserData> before = app.userHelper().all();

        UserData userToDelete = before.iterator().next(); // выбирается случайный эл т множества и затем у него next
        app.userHelper().delete(userToDelete);

        app.gotoUserPage();
        HashSet<UserData> after = app.userHelper().all();

        // Сравниваем размеры :
        Assert.assertEquals(after.size(), before.size() - 1);

        if ( after.size()> 0 ) {
            before.remove(userToDelete);
            Assert.assertEquals(after, before, "List of groups after deletion is not equal to ethalon!");

        }

    }
}
