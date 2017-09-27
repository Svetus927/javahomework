package application;

import org.openqa.selenium.WebDriver;


/**
 * Created by uasso on 10/07/2017.
 */
public class NavigationHelper extends HelperBase {

    private final String webBaseUrl;

    public NavigationHelper(WebDriver wd, String baseUrl) {
       super(wd);
       webBaseUrl = baseUrl;
    }

    public void gotoUserPage() {

        wd.get(webBaseUrl);
   //     wd.get("http://localhost/addressbookv4.1.4/");
    }

    public void gotoGroupPage() {

      //  wd.get("http://localhost/addressbookv4.1.4/group.php/");
        wd.get(webBaseUrl +"group.php/");
    }
}
