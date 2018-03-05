package application;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/10/2017. текст взят из видео 8.3
 * класс создан для прямого взаимодействия с сервером по протоколу HTTP без участия браузера
 * а точнее  для быстрых проверок без участия интерфейса, например  в случае что возможно залогиниться на сервер под
 * юзером созданным автоматически
 */
public class HttpSession {

    private CloseableHttpClient  httpClient;
    private ApplicationManager app;

    // * конструктор * //
    public  HttpSession (ApplicationManager app)  {
        this.app =  app;
        // для того чтобы  редирект выполнялся в созданном клиенте автоматически (код 302) устанавливаем стратегию в LAX
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

   public boolean login (String username, String password) throws IOException {
      // ** пример  поста с параметрами, они к посту прикручиваются методом setEntity, а уж потом у инициализированного
       // заранее http клиента выполняется execute этого поста
       HttpPost post = new HttpPost(app.getProperty("web.BaseUrl")+"/login.php");
       List<NameValuePair> params = new ArrayList<>();
       params.add(new BasicNameValuePair ("username", username));
       params.add(new BasicNameValuePair ("password", password));
       params.add(new BasicNameValuePair ("secure_session", "on"));
       params.add(new BasicNameValuePair ("return", "index.php"));
       post.setEntity(new UrlEncodedFormEntity(params));
       CloseableHttpResponse response = httpClient.execute(post);


       String body = getTextFromResponse(response);
       System.out.println(body);
       return body.contains(String.format("username=%s", username));

   }

    private String getTextFromResponse(CloseableHttpResponse response) throws IOException {
       try {
           return EntityUtils.toString(response.getEntity());
       } finally {
            response.close();
       }

    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.BaseUrl")+"my_view_page.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFromResponse(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));


    }

}
