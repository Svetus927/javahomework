package tests;

import model.UserData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserCreationTests extends TestBase{


    @DataProvider
    public Iterator<Object[]> validUsers () throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
        String line = reader.readLine();
        while (line!=null) {
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

    @Test(dataProvider= "validUsers")
    public void testAddNewUser(UserData user) {

        app.gotoUserPage();
        app.userHelper().create(user);
        app.gotoUserPage();
    }

}
