package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Svetlana Verkholantceva on 19/07/2017.
 */
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.gotoGroupPage();
        if (! app.groupHelper().isThereAGroup()) {
            app.groupHelper().create(new GroupData().withName("FriendsAndrei").withFooter("friends"));
            app.gotoGroupPage();
        }
    }

    @Test
    public void testGroupDeletion() {

        HashSet<GroupData> before = app.groupHelper().all();
        System.out.println("before = " + before.size());

        GroupData groupToDelete = before.iterator().next(); // выбирается случайный эл т множества и затем у него next
        app.groupHelper().delete(groupToDelete);
        app.gotoGroupPage();


        HashSet<GroupData> after = app.groupHelper().all();
        System.out.println("after = " + after.size());

        // Сравниваем размеры :
        Assert.assertEquals(after.size(), before.size() - 1);

        if ( after.size()> 0 ) {
            before.remove(groupToDelete);
            Assert.assertEquals(after, before, "List of groups after deletion is not equal to ethalon!");

        }


    }


}
