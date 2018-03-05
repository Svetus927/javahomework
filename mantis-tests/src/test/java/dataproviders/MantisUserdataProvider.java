package dataproviders;

/**
 * Created  on 01/03/2018.
 */

import com.thoughtworks.xstream.XStream; // нужен для обработки xml формата
import model.MantisUserData;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class MantisUserdataProvider {

    @DataProvider // считывание данных из XML файла
    public Iterator<Object[]> validUsersXML() throws IOException {
        //  List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/mantisusers.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line; // xml = xml+line;
            line = reader.readLine();
        }
        XStream xstream  = new XStream(); // спец объект для работы с xml файлами
       // xstream.processAnnotations(MantisUserData.class); // чтобы исп такой вариант нужно замапить с классом аннотацией
                            //@XStreamAlias(<имя тэга) и тогда все будет называться или опускаться как указано в классе
      //  xstream.alias("uuserr", MantisUserData.class); // или можно прописывать явно здесь, тогда мапить в самом классе не нужно
        List<MantisUserData> groups = (List<MantisUserData>)xstream.fromXML(xml); // приведение типов ( кастинг одного типа данных к другому)
        return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider// считывание данных из CSV файла
    public Iterator<Object[]> validUsersCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/mantisusers.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new MantisUserData().withUsername(split[0])
                                                      .withRealname(split[1])
                                                        .withEmail(split[2])
                                                        .withPassword(split[0]+"pwd")}); //? проверить есть ли пароль в  генераторе
            line = reader.readLine();
        }
        return list.iterator();
    }
}






