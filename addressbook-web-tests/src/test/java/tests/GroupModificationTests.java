package tests;

import org.testng.annotations.Test;
import model.GroupData;

/**
 * Created by Svetlana Verkholantceva on 13/07/2017.
 */
public class GroupModificationTests  extends TestBase{
    @Test
    public void testGroupModification() {
        app.gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Friends", null, "friends"));
            app.gotoGroupPage();
        }
        app.getGroupHelper().initGroupModification();

        app.getGroupHelper().fillGroupData(new GroupData("FriendsUpdated", null, "friendsUpd"));
        app.getGroupHelper().submitGroupModification();

        app.gotoGroupPage();
    }

}
