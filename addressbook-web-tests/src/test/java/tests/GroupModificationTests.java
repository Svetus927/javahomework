package tests;


import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import model.GroupData;

/**
 * Created by Svetlana Verkholantceva on 13/07/2017.
 */
public class GroupModificationTests  extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.gotoGroupPage();
        if (app.db().groups().size()==0)//  if  (! app.groupHelper().isThereAGroup())
        {
            app.groupHelper().create(new GroupData().withName("Friends").withFooter("friends"));
            app.gotoGroupPage();
        }
    }

    @Test
    public void testGroupModification() {

        Groups before = app.db().groups();

        GroupData groupToModify = before.iterator().next(); // выбирается случайный эл т множества и затем у него next
        GroupData newGroupData = new GroupData().withId(groupToModify.id()).withName("Friends1").withFooter("friendsUpd");

        app.groupHelper().selectGroupbyId(groupToModify.id());
        app.groupHelper().edit();
        app.groupHelper().fillGroupData(newGroupData);
        app.groupHelper().submitModification();

        app.gotoGroupPage();
        Groups after = app.db().groups();//groupHelper().all();

        // Сравниваем размеры списков:
        Assert.assertEquals(after.size(), before.size() );

        // сравниваем сами списки, предварительно удалив старый элемент и добавив новый и преобразовав их в множества HashSet:
        before.remove(groupToModify);
        before.add(newGroupData);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
       // Assert.assertEquals(before,after);   простой testng-шный способ без хамкреста для примера

        verifyGroupsUI();

    }



}
