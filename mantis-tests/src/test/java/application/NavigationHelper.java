package application;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by uasso on 09/11/2017.
 */
public class NavigationHelper extends HelperBase {

    private final String webBaseUrl;

    public NavigationHelper(ApplicationManager app, String baseUrl) {
       super(app);
       webBaseUrl = baseUrl;
    }

    public boolean loginAsAdmininUI(String username, String password) {

        wd.get(webBaseUrl +"login_page.php");
        type(By.name("username"), username);
        click(new By.ByCssSelector("input[type='submit'"));
        type(By.name("password"), password);
        click(new By.ByCssSelector("input[type='submit'"));
        WebElement loggedAs = wd.findElement(By.xpath("//ul[@class='breadcrumb'][.//a[contains(.,'administrator')]]"));

        if (loggedAs!= null) return true;
        else return false;



    }
    public void gotoManageUsersPage() {

      //  wd.get("http://localhost/mantisbt-2.5.0/manage_user_page.php");
        wd.get(webBaseUrl +"manage_user_page.php");

    }


}
