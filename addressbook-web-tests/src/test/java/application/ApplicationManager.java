package application;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.util.concurrent.TimeUnit;

/**
 * Created by Svetlana Verkholantceva on 10/07/2017.
 */
public class ApplicationManager {
    WebDriver wd;
    private  UserHelper userHelper ;
    private  NavigationHelper navigationHelper;
    private  GroupHelper groupHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {

        if (browser == BrowserType.FIREFOX) {
            wd = new FirefoxDriver();
        }
        else  if (browser == BrowserType.IE) {
            wd = new InternetExplorerDriver();
        }
        else if (browser == BrowserType.CHROME) {
            wd = new ChromeDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
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

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public void gotoUserPage() {
        navigationHelper.gotoUserPage();
    }

    public void gotoGroupPage() {
        navigationHelper.gotoGroupPage();
    }


}
