import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class UserCreationTests {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    private void gotoUserPage() {
        wd.get("http://localhost/addressbookv4.1.4/");
    }

    @Test
    public void AddNewUser() {

        gotoUserPage();
        initUserCreation();

        fillUserForm(new UserData("Vasya", "Ivanka", "25 oktabrja", "3251645", "kuku@gmIL,COM"));
        submitUserCreation();
        gotoUserPage();
    }

    private void fillUserForm(UserData userdata) {


        wd.findElement(By.name("firstname")).sendKeys(userdata.firstname);
        wd.findElement(By.name("lastname")).sendKeys(userdata.lastname);
        wd.findElement(By.name("address")).sendKeys(userdata.address);
        wd.findElement(By.name("home")).sendKeys(userdata.homephone);
        wd.findElement(By.name("email")).sendKeys(userdata.email);

    }

    private void submitUserCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void initUserCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
