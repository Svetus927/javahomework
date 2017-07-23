package application;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uasso on 10/07/2017.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void fillGroupData(GroupData groupData) {

        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());

    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }


    public void initSelectedGroupModification() {

        click(By.name("edit"));

    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void initSelectedGroupDeletion() {
        click(By.name("delete"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupData(groupData);
        submitGroupCreation();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.cssSelector("input[name='selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.cssSelector("input[name='selected[]'")).size();
    }

    public List<GroupData> getGroupList() {
        // просто так List of WebElements в List<GroupData> не запихать, поэтому надо пооследовательно пройти по списку и создавать
        // объекты нужного типа, а потом их уже добавить в нужный список
        List groups = new ArrayList<GroupData>();

        List<WebElement> elements =  wd.findElements(By.cssSelector("input[name='selected[]'"));
        for (WebElement element: elements) {
            int  id = Integer.parseInt(element.getAttribute("value"));
            String title = element.getAttribute("title");
            String name = getGroupNameFromTitle(title);
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }

    private String getGroupNameFromTitle(String title) {
        int len = title.length();
        String nam =title.substring(8,len-1 );
        return nam;

    }

    public void selectGroup(int i) {
        wd.findElements(By.cssSelector("input[name='selected[]")).get(i).click();

    }



}
