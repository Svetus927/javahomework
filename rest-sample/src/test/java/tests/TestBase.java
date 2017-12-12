package tests;

import application.ApplicationManager;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created on 11/12/2017.
 */
public class TestBase {



   protected  static final ApplicationManager app = new ApplicationManager(System.getProperty("browser")); // в Edit Configurations указ пар р VM machine:  -ea -dBrowser=firefox
// второе значение задает значение браузера по умолчанию

    @BeforeClass
    public void setUp() throws Exception {
        app.init();

    }

    @AfterClass
    public void tearDown() throws IOException {
        app.stop();
    }

// ** Функция  которая проверяет в начале теста, исправлен ли баг по этому тесту в багтрекере мантис ** //
    public void skipIfNotFixed(int issueId) throws IOException {
        if (issueOpen(issueId)) {
            throw new SkipException("Test ignored because of issue " + issueId);
        }
    }

    /*
    В классе TestBase, от которого наследуются все тесты, необходимо реализовать функцию boolean isIssueOpen(int issueId) ,
    которая должна через Remote API получать из баг-трекера информацию о    баг-репорте с заданным идентификатором,
    и возвращать значение false или true в зависимости от того, помечен он как исправленный или нет.
    */
    private boolean issueOpen (int issueId) throws IOException {

        return (app.restHelper().isIssueOpen(issueId));


    }


}
