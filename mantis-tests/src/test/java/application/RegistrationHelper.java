package application;

import org.openqa.selenium.WebDriver;

/**
 * Created by uasso on 16/10/2017.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app =app;
        this.wd = app.getDriver();
    }


    public void start(String username, String email) {
        wd.get(app.getProperty("web.BaseUrl")+"signup_page.php");

    }
}
