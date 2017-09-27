package tests;

import com.thoughtworks.xstream.XStream;
import model.GroupData;

import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class GroupCreationTests extends TestBase {

    @DataProvider// пример считывания данных из CSV файла
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider // пример считывания данных из XML файла
    public Iterator<Object[]> validGroupsXML() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line; // xml = xml+line;
            line = reader.readLine();
        }
        XStream xstream  = new XStream(); // спец объект для работы с xml файлами
        xstream.processAnnotations(GroupData.class);
        List<GroupData>  groups = (List<GroupData>)xstream.fromXML(xml); // приведение типов ( кастинг одного типа данных к другому)
        return groups.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroups")
    public void tesGroupCreation(GroupData group) {
        //  GroupData group = new GroupData().withName("FriendsCheck").withFooter("friends");

        app.gotoGroupPage();
        Groups before = app.groupHelper().allGroups();


        app.groupHelper().create(group);

        app.gotoGroupPage();
        Groups after = app.groupHelper().allGroups();

        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);

        // устанавливаем новой группе нужный ид ор и  сравниваем сами списки:
        group.withId(after.stream().mapToInt(g -> g.id()).max().getAsInt());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withAdded(group)));

        // before.add(group);  Assert.assertEquals(before, after, "Lists of group after new addition is not equal to ethalon!");

    }
}





   /* public int getMaxGroupId(List<GroupData> after) {
        int max = 0;
        for (GroupData g : after) {
            if (g.id() > max) {
                max = g.id();
            }

        }
        return  max;
    }*/

