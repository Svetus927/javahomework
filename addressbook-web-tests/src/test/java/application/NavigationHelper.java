package application;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by uasso on 10/07/2017.
 */
public class NavigationHelper extends HelperBase {


    public NavigationHelper(FirefoxDriver wd) {
       super(wd);
    }

    public void gotoUserPage() {
        wd.get("http://localhost/addressbookv4.1.4/");
    }

    public void gotoGroupPage() {
        wd.get("http://localhost/addressbookv4.1.4/group.php/");
    }
}
