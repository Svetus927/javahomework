package tests;

import application.ApplicationManager;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by uasso on 07/07/2017.
 */
public class TestBase {



   protected  static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); // в Edit Configurations указ пар р VM machine:  -ea -dBrowser=firefox
// второе значение задает значение браузера по умолчанию

    @BeforeClass
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterClass
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back", "config_inc.php"); //  то что запихнули  резерв восстанавливаем обратно
        app.stop();
    }

// ** Функция  которая проверяет в начале теста, исправлен ли баг по этому тесту в багтрекере мантис ** //
    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueNotFixed(issueId)) {
            throw new SkipException("Test ignored because of issue " + issueId);
        }
    }

    /*
    В классе TestBase, от которого наследуются все тесты, необходимо реализовать функцию boolean isIssueOpen(int issueId) ,
    которая должна через Remote API получать из баг-трекера информацию о    баг-репорте с заданным идентификатором,
    и возвращать значение false или true в зависимости от того, помечен он как исправленный или нет.
    */
    public boolean isIssueNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {

        return !(app.soapHelper().isIssueResolvedorClosed(issueId));

    }


}
