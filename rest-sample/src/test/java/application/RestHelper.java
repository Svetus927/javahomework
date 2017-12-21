package application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import model.Issue;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by uasso on 11/12/2017.
 * * тут есть пример работы с парсером JSON!
 * для работы с удаленным API по технологии REST необходимо подкл бибки
 * для работы c http клиентом и форматом json - com.google.code.gson, org.apache.httpcomponents:httpclient
 * А также подключим бибку fluent-hc для работы в стиле fluent c http клиентом
 * документация на https://hc.apache.org/
 */
public class RestHelper {
    ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }


    public boolean isIssueOpen(int issueId) throws IOException {
        Executor executor = getExecutor();

        String json = executor.execute(Request.Get(app.getProperty("web.BaseUrl")+ String.format("/issues/%s.json",issueId )))
                .returnContent().asString();
        // парсим полученный json:
        JsonElement parsed = new JsonParser().parse(json);
        // выделяем элемент с ключем status
        JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);

        int state  =((JsonObject) issues).get("state").getAsInt();
        return (state==0)||(state==1); //  0 = open, 1 = in processing

    }

    public int createIssue(Issue newIssue) throws IOException {
        Executor executor = getExecutor();
        String json = executor.execute(Request.Post(app.getProperty("web.BaseUrl")+"/issues.json").
                bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        // парсим полученный json:
        JsonElement parsed = new JsonParser().parse(json);
        // выделяем элемент с ключем issue_id
        int id  = parsed.getAsJsonObject().get("issue_id").getAsInt();
        return id;
    }

    public int createIssueWithRestAssured(Issue newIssue) throws IOException {
       /* Executor executor = getExecutor();
        String json = executor.execute(Request.Post(app.getProperty("web.BaseUrl")+"/issues.json").
                bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();  -  версия без restassured*/
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post(app.getProperty("web.BaseUrl")+"/issues.json").asString();
        // парсим полученный json:
        JsonElement parsed = new JsonParser().parse(json);
        // выделяем элемент с ключем issue_id
        int id  = parsed.getAsJsonObject().get("issue_id").getAsInt();
        return id;
    }

    public Set<Issue> getIssues() throws IOException {
        Executor executor = getExecutor();

        String json = executor.execute(Request.Get(app.getProperty("web.BaseUrl")+"/issues.json?limit=500"))
                .returnContent().asString();


        // парсим полученный json:
        JsonElement parsed = new JsonParser().parse(json);
        // выделяем элементы с ключем issues
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType()); //  преобразуем в set<Issue>

    }

    public Set<Issue> getIssueswithREstAssured() throws IOException {
        // без executor который в бибке http клиента
        String json =RestAssured.get(app.getProperty("web.BaseUrl")+"/issues.json").asString();


        // парсим полученный json:
        JsonElement parsed = new JsonParser().parse(json);
        // выделяем элементы с ключем issues
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType()); //  преобразуем в set<Issue>

    }
    private Executor getExecutor() {
        // Executor из бибки http fluent
        return Executor.newInstance()
                .auth(app.getProperty("web.ApiKei")   , "");

    }

    public void initRestAssured() {
        RestAssured.authentication = RestAssured.basic(app.getProperty("web.ApiKei")   , "");
    }
}
