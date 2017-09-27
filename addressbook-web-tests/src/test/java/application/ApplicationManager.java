package application;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Svetlana Verkholantceva on 10/07/2017.
 */
public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;
    private  UserHelper userHelper ;
    private  NavigationHelper navigationHelper;
    private  GroupHelper groupHelper;
    private String browser;
    private DbHelper dbHelper;



    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() throws IOException {
        String target = System.getProperty("target");//"local2"); // 1-ый параметр название св-ва,
        // которое может быть задано в ком строке, и может юыть не определено системой, как в данном случае и вернуло бы без деф
        // значения null;  2-ой пар-р - значение по умолчанию, если  при вызове пар-р не указан
        String propertiesFileName = String.format("src/test/resources/%s.properties", target);
        System.out.println("filename " + propertiesFileName);

        properties.load( new FileReader(new File(propertiesFileName)));
// ** Инициализируем соединение с БД: **
        dbHelper = new DbHelper();

        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }
        else  if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd, properties.getProperty("web.BaseUrl"));
        userHelper = new UserHelper(wd);


    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper groupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public UserHelper userHelper() {
        return userHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public void gotoUserPage() {
        navigationHelper.gotoUserPage();
    }

    public void gotoGroupPage() {
        navigationHelper.gotoGroupPage();
    }


}
