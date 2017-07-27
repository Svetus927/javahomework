package tests;

import model.GroupData;

import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {


    @Test
    public void tesGroupCreation() {
        GroupData group = new GroupData().withName("FriendsCheck").withFooter("friends");

        app.gotoGroupPage();
        Groups before = app.groupHelper().allGroups();


        app.groupHelper().create(group);

        app.gotoGroupPage();
        Groups after = app.groupHelper().allGroups();

        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size() + 1);

        // устанавливаем новой группе нужный ид ор и  сравниваем сами списки:
        group.withId(after.stream().mapToInt(g -> g.getGroupId()).max().getAsInt());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withAdded(group)));

       // before.add(group);  Assert.assertEquals(before, after, "Lists of group after new addition is not equal to ethalon!");

    }
}





   /* public int getMaxGroupId(List<GroupData> after) {
        int max = 0;
        for (GroupData g : after) {
            if (g.getGroupId() > max) {
                max = g.getGroupId();
            }

        }
        return  max;
    }*/

