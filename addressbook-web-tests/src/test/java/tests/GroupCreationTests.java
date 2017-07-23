package tests;

import model.GroupData;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class GroupCreationTests extends TestBase{

    
    @Test
    public void tesGroupCreation() {
        app.gotoGroupPage();

        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("before = " + before.size());
        GroupData group = new GroupData("Friends", null, "friends");
        app.getGroupHelper().createGroup(group);

        app.gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("after = " + after.size());

        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size()+1 );

        // Сравниваем сами списки:
        int newGroupId = getMaxGroupId(after);
        group.setGroupId(newGroupId);
        before.add(group);
        Assert.assertEquals(before, after, "Lists of group after new addition is not equal to ethalon!");

    }
    public int getMaxGroupId(List<GroupData> after) {
        int max = 0;
        for (GroupData g : after) {
            if (g.getGroupId() > max) {
                max = g.getGroupId();
            }

        }
        return  max;
    }
}
