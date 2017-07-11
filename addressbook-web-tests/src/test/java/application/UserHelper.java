package application;

import model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by uasso on 10/07/2017.
 */
public class UserHelper extends HelperBase {
    private FirefoxDriver wd;

    public UserHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void fillUserForm(UserData userdata) {
        type(By.name("firstname"), userdata.getFirstname());
        type(By.name("lastname"), userdata.getLastname());
        type(By.name("address"), userdata.getAddress());
        type(By.name("home"), userdata.getHomephone());
        type(By.name("email"), userdata.getEmail());

    }

    public void submitUserCreation() {
        click(By.name("submit"));
    }

    public void initUserCreation() {
        click (By.linkText("add new"));
    }
}
