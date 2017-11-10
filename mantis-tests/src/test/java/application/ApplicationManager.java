package application;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSessionContext;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by Svetlana Verkholantceva on 10/07/2017.
 */
public class ApplicationManager {

    private String browser;
    private final Properties properties;

    private WebDriver wd; // сделан private для того чтобы вме классы могли получить к нему доступ только через метод getDriver()
                        // при инициализации app не означивается чтоб не тормозить работу ( дальше  исп ленивая иниц-ия (getDriver

    private FtpHelper ftp;
    private MailHelper mailHelper;
    private  NavigationHelper navigationHelper;
    private  ManageUserHelper manageUserHelper;


 //   private DbHelper dbHelper;  пока что не  создан  - пока коментим


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target","local"); // 1-ый параметр название св-ва,
        // которое может быть задано в ком строке, и может юыть не определено системой, как в данном случае и вернуло бы без деф
        // значения null;  2-ой пар-р - значение по умолчанию, если  при вызове пар-р не указан

        String propertiesFileName = String.format("src/test/resources/%s.properties", target);
        System.out.println("filename " + propertiesFileName);

        properties.load( new FileReader(new File(propertiesFileName)));

// ** Инициализируем соединение с БД: **       dbHelper = new DbHelper();

    }


    public void stop() {
        if (wd!=null) {
            wd.quit();
        }

    }





    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this); //  в качестве параметра передается вызвавший ApplicationManager
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registrationHelper() {
        return new RegistrationHelper(this);
    }

    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ  вебдрайвера** //
    public WebDriver getDriver() {
        if (wd == null) {
            // **  Выбираем броузер в котором будем проводить тестирование на основании параметра browser
             // пар р browser считывается/задается при инициализации app.init
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }

        }

    //    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return wd;
    }

    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ  передатчика файлов** //
    public FtpHelper ftp(){
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ почтового помощника** //
    public MailHelper MailHelper(){
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ  навигатора** //
    public NavigationHelper navigationHelper(){
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this,this.getProperty("web.BaseUrl"));
        }
        return navigationHelper;
    }

    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ  помощника по управдлению пользователями** //
    public ManageUserHelper manageUserHelper() {
        if (manageUserHelper== null) {
            manageUserHelper = new ManageUserHelper(this);
        }
        return manageUserHelper;
    }
}



