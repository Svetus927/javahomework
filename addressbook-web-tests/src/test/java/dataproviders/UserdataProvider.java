package dataproviders;

import model.UserData;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by uasso on 27/02/2018.  При создании польз ля инфа будет браться отсюда
 */
public class UserdataProvider {

    @DataProvider
    public Iterator<Object[]> validUsersCSV  () throws IOException {
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
}
