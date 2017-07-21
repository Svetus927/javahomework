package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Svetlana Verkholantceva on 19/07/2017.
 */
public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("FriendsAndrei", null, "friends"));
            app.gotoGroupPage();
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();

        System.out.println("before = " + before.size());
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initSelectedGroupDeletion();
        app.gotoGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("after = " + after.size());
        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size() - 1);

        if ( after.size()> 0 ) {
            before.remove(after.size() - 1);
            System.out.println("before.equals(after) " + before.equals(after));
            Assert.assertEquals(after, before, "Lists of groups before and after deletion are not equal!");
            /*  for (int i = 0; i < after.size(); i++) {
                Assert.assertEquals(before.get(i), after.get(i));
            } */
        }


    }
}
