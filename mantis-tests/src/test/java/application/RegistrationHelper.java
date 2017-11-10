package application;


import org.openqa.selenium.By;

/**
 * Created by on 16/10/2017.
 *
 */
public class RegistrationHelper extends HelperBase {



    public RegistrationHelper(ApplicationManager app) {
        super(app);

    }


    public void startRegistration(String username, String email) {
        wd.get(app.getProperty("web.BaseUrl")+"signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(new By.ByCssSelector("input[type='submit'"));
        System.out.println("начало регистрации положено!");


    }

    public void finishRegistration(String link, String realName, String password) {
        wd.get(link);
        type(By.name("realname"), realName );
        type(By.name("password"),password);
        type(By.name("password_confirm"), password );
        click(new By.ByCssSelector("button[type='submit']"));

    }


}
