package tests;

import application.ApplicationManager;
import model.GroupData;
import model.Groups;

import org.hamcrest.MatcherAssert;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;


import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by uasso on 07/07/2017.
 */
public class TestBase {


   /// final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
   protected  static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); // в Edit Configurations указ пар р VM machine:  -ea -dBrowser=firefox
// второе значение задает значение браузера по умолчанию
    @BeforeClass
    public void setUp() throws Exception {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }


    public void verifyGroupsUI() {
        // функция проверяющая что группы в интерфейсе соответсвуют группам в БД
        Groups dbGroups =app.db().groups();
        Groups uiGroups =app.groupHelper().allGroups();
        MatcherAssert.assertThat(uiGroups, equalTo(dbGroups.stream().
                                    map((g)->new GroupData().withId(g.id()).withName(g.name()))
                                    .collect(Collectors.toSet())
        ));

    }
}
