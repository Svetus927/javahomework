package tests;

import model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by uasso on 12/09/2017.
 */
public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.gotoUserPage();
        if (!app.userHelper().isThereAUser()) {
            UserData user = new UserData().withFirstname("Petya").
                    withLastname("Petrov").
                    withHomephone("123456").
                    withMobilephone("+7123456").
                    withWorkphone("654321");
            app.userHelper().create(user);
            app.gotoUserPage();
        }
    }

    @DataProvider
    public Iterator<Object[]> validUser() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new UserData().withFirstname(split[0]).
                    withLastname(split[1]).
                    withHomephone(split[2]).
                    withMobilephone(split[3]).
                    withWorkphone(split[4])});
            //    withEmail(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }


    @Test(dataProvider = "validUser")
    public void testGroupModification(UserData updatedUser) {

        HashSet<UserData> before = app.userHelper().all();

        UserData userToModify = before.iterator().next(); // выбирается случайный эл т множества и затем у него next
        app.userHelper().modify(userToModify.getId(), updatedUser);

        app.gotoUserPage();
        HashSet<UserData> after = app.userHelper().all();

        // Сравниваем размеры :
        Assert.assertEquals(after.size(), before.size());


        before.remove(userToModify);
        before.add(updatedUser);
        // Assert.assertEquals(after, before, "List of groups after deletion is not equal to ethalon!");


    }
}

