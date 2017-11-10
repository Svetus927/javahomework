package application;

import model.MantisUserData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created  on 09/11/2017.
 * для операций по управлению пользователем
 */
public class ManageUserHelper extends HelperBase {

    // конструктор
    public ManageUserHelper(ApplicationManager app) {
        super(app);
    }

    public MantisUserData findUserToManage (String partUserName) {
        WebElement tr = wd.findElement(By.xpath("//tr[.//td[contains(.,'user15099780')]]"));
        if (tr!=null) {
            MantisUserData user = new MantisUserData();
            user.setUsername(tr.findElements(By.cssSelector("td")).get(0).getText());
            user.setRealname(tr.findElements(By.cssSelector("td")).get(1).getText());
            user.setEmail(tr.findElements(By.cssSelector("td")).get(2).getText());

            tr.findElement(By.cssSelector("a[href*='manage_user_edit'")).click();

            System.out.println("hurray we opened page  to edit user");
            //  Assert.assertFalse("email для юзера не найден! ", email.isEmpty());
            return user;
        } else
            return null;


    }

    public void startResetPassword () {
        click(new By.ByCssSelector("input[value='Reset Password']"));

    }

    public void finishResetPassword(String link, String realname, String newPassword) {
       wd.get(link);
       type(By.name("realname"), realname);
       type(By.name("password"), newPassword);
       type(By.name("password_confirm"), newPassword);
       click(By.cssSelector("button[type='submit']"));


    }
}
