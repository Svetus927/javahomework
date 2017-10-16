package application;


import model.GroupData;
import model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by uasso on 10/07/2017.
 */
public class GroupHelper extends HelperBase {
    private Groups groupCache = null; // для ускорения работы


    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void fillGroupData(GroupData groupData) {

        type(By.name("group_name"), groupData.name());
        type(By.name("group_header"), groupData.header());
        type(By.name("group_footer"), groupData.footer());

    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }


    public void edit() {

        click(By.name("edit"));

    }

    public void submitModification() {
        click(By.name("update"));
        groupCache = null;
    }

    public void delete(GroupData groupToDelete) {
        selectGroupbyId(groupToDelete.id());
        deleteSelectedGroup();

    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
        groupCache = null;
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupData(groupData);
        submitGroupCreation();
        groupCache = null;
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.cssSelector("input[name='selected[]"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("input[name='selected[]'")).size();
    }

    public List<GroupData> getList() {
        // просто так List of WebElements в List<GroupData> не запихать, поэтому надо пооследовательно пройти по списку и создавать
        // объекты нужного типа, а потом их уже добавить в нужный список
        List groups = new ArrayList<GroupData>();

        List<WebElement> elements = wd.findElements(By.cssSelector("input[name='selected[]'"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.getAttribute("value"));
            String title = element.getAttribute("title");
            String name = getGroupNameFromTitle(title);
            GroupData group = new GroupData().withId(id).withName(name).withHeader(null).withFooter(null);
            groups.add(group);
        }
        return groups;
    }



    public HashSet<GroupData> all() {
// метод аналогичный методу GetList но результат  не List  а множество
        HashSet groups = new HashSet<GroupData>();

        List<WebElement> elements = wd.findElements(By.cssSelector("input[name='selected[]'"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.getAttribute("value"));
            String name = getGroupNameFromTitle(element.getAttribute("title"));
            GroupData group = new GroupData().withId(id).withName(name).withHeader(null).withFooter(null);
            groups.add(group);
        }
        return groups;
    }

    public Groups allGroups() {
// метод аналогичный all но возвращающий объект типа Groups еоторый мы создали сами для добавления собственных методов (для красоты)
        if (groupCache != null) {
            return new Groups(groupCache);
        }

        groupCache = new Groups();

        List<WebElement> elements = wd.findElements(By.cssSelector("input[name='selected[]'"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.getAttribute("value"));
            String name = getGroupNameFromTitle(element.getAttribute("title"));
            GroupData group = new GroupData().withId(id).withName(name).withHeader(null).withFooter(null);
            groupCache.add(group);
        }
        return new Groups(groupCache);

    }

    private String getGroupNameFromTitle(String title) {
        int len = title.length();
        String nam = title.substring(8, len - 1);
        return nam;

    }


    public void selectGroupbyId(int groupId) {
        wd.findElement(By.cssSelector("input[value='" + groupId + "']")).click();
    }
}
