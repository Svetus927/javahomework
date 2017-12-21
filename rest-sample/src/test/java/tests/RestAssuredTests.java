package tests;

import model.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created on 04/12/2017.
 * тут есть пример работы с парсером JSON!
 * для работы с удаленным API по технологии REST необходимо подкл бибки
 * с бибкой RESTASSURED
 * для работы c http клиентом и форматом json - com.google.code.gson,
 *

 */
public class RestAssuredTests extends TestBase{
// здесь для авторизации будем ип метод before class который один раз авторизует и получает RestAssrured

    @BeforeClass
    public void init() {
        app.restHelper().initRestAssured();
    }

    @Test
    public void testCreateIssue() throws IOException {

        Set<Issue> oldset = app.restHelper().getIssueswithREstAssured();
        Issue newIssue = new Issue().withSubject("new s").withDescription("My description new");
        int issueId =  app.restHelper().createIssueWithRestAssured(newIssue);

        Set<Issue> newset =  app.restHelper().getIssues();
        oldset.add( newIssue.withId(issueId));
        Assert.assertEquals(oldset, newset);

    }


}


