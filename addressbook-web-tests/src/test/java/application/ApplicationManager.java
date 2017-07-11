package application;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Svetlana Verkholantceva on 10/07/2017.
 */
public class ApplicationManager {
    FirefoxDriver wd;
    private  UserHelper userHelper ;
    private  NavigationHelper navigationHelper;
    private  GroupHelper groupHelper;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        userHelper = new UserHelper(wd);
    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
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
