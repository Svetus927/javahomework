package tests;

import model.GroupData;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

    
    @Test
    public void tesGroupCreation() {
        app.gotoGroupPage();

        app.getGroupHelper().createGroup(new GroupData("Friends", null, "friends"));

        app.gotoGroupPage();
    }

}
