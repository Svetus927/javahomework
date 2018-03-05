package dataproviders;

import com.thoughtworks.xstream.XStream;
import model.GroupData;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by uasso on 27/02/2018.
 */
public class GroupdataProvider {

    @DataProvider // считывание данных из XML файла
    public Iterator<Object[]> validGroupsXML() throws IOException {
      //  List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line; // xml = xml+line;
            line = reader.readLine();
        }
        XStream xstream  = new XStream(); // спец объект для работы с xml файлами
        xstream.processAnnotations(GroupData.class); // чтобы исп такой вариант нужно замапить с классом аннотацией
                    //@XStreamAlias(<имя тэга) и тогда все будет называться или опускаться как указано в классе
        xstream.alias("group", GroupData.class); // или можно прописывать явно здесь, тогда мапить в самом классе не нужно
        List<GroupData>  groups = (List<GroupData>)xstream.fromXML(xml); // приведение типов ( кастинг одного типа данных к другому)
        return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider// считывание данных из CSV файла
    public Iterator<Object[]> validGroupsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/newgroups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
