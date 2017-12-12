package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import model.Issue;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created on 04/12/2017.
 * тут есть пример работы с парсером JSON!
 * для работы с удаленным API по технологии REST необходимо подкл бибки
 * для работы c http клиентом и форматом json - com.google.code.gson, org.apache.httpcomponents:httpclient
 * А также подключим бибку fluent-hc для работы в стиле fluent c http клиентом
 * документация на https://hc.apache.org/
 */
public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {

        Set<Issue> oldset = app.restHelper().getIssues();
        Issue newIssue = new Issue().withSubject("new s").withDescription("My description new");
        int issueId =  app.restHelper().createIssue(newIssue);

        Set<Issue> newset =  app.restHelper().getIssues();
        oldset.add( newIssue.withId(issueId));
        Assert.assertEquals(oldset, newset);

    }


}


