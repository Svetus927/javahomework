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


    public void initGroupModification() {
        click(By.cssSelector("input[name='selected[]"));
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
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }

    public void selectGroup(int i) {
        wd.findElements(By.cssSelector("input[name='selected[]")).get(i).click();

    }
}
