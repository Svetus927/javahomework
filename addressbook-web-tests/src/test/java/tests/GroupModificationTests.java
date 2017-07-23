package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import model.GroupData;

import java.util.HashSet;
import java.util.List;

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

        List<GroupData> before = app.getGroupHelper().getGroupList();

        System.out.println("before = " + before.size());
        int selectedNum  = before.size() - 1;
        app.getGroupHelper().selectGroup(selectedNum);
        app.getGroupHelper().initSelectedGroupModification();
        GroupData groupUpdated = new GroupData(before.get(selectedNum).getGroupId(),"First Group", null, "friendsUpd");
        app.getGroupHelper().fillGroupData(groupUpdated);
        app.getGroupHelper().submitGroupModification();

        app.gotoGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("after = " + after.size());

        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size() );

        // сравниваем сами списки, предварительно удалив старый элемент и добавив новый и преобразовав их в множества HashSet:
        before.remove(selectedNum);
        before.add(groupUpdated);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }

}
