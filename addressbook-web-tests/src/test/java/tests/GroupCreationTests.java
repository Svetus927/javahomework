package tests;

import model.GroupData;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

    
    @Test
    public void tesGroupCreation() {
        app.gotoGroupPage();

        app.getGroupHelper().initGroupCreation();

        app.getGroupHelper().fillGroupData(new GroupData("Friends", null, "friends"));
        app.getGroupHelper().submitGroupCreation();

        app.gotoGroupPage();
    }

}
