package application;
//  String selector = String.format("input[value=%s]", id);

import model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;

/**
 * Created by uasso on 10/07/2017.
 */
public class UserHelper extends HelperBase {


    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void create(UserData user) {
        initUserCreation();
        fillUserForm(user);
        submitUserCreation();
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

    public boolean isThereAUser() {
        return isElementPresent(By.cssSelector("input[name='selected[]"));
    }


    public HashSet<UserData> all() { // получить неупорядоченное множество контактов
// метод аналогичный методу GetList но результат  не List  а множество -  ДЛЯ КОНТАКТОВ
        HashSet users = new HashSet<UserData>();

        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
           WebElement checkbox =  element.findElement(By.cssSelector("input[type='checkbox']"));
           int id = Integer.parseInt(checkbox.getAttribute("value"));
            String name =element.findElements(By.cssSelector("td")).get(1).getText();
            UserData user = new UserData().withId(id).withFirstname(name).withLastname(null).withHomephone(null).
                    withMobilephone(null).withWorkphone(null).withEmail(null);
            users.add(user);
        }
        return users;
    }

    public void delete(UserData userToDelete) {

        clickPencilForEdit(userToDelete.getId());
        submitUserDelete();
        //userCache = null;
    }

    public void modify(int id, UserData updatedUser) {
        clickPencilForEdit(id);
        fillUserForm(updatedUser);
        submitUserUpdate();
    }

    private void clickPencilForEdit(int id) {
        wd.findElements(By.cssSelector("a[href*='id="+id+"']")).get(1).click();
        System.out.println("ЮЗЕР открыт на едит " + id );
    }

    private void submitUserUpdate() {
        By updateLocator = By.cssSelector("input[value='Update']");
        /* WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(updateLocator)); */
        click(updateLocator);
    }

    private void submitUserDelete() {
        By deleteLocator = By.cssSelector("input[value='Delete']");
        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(deleteLocator));
        click(deleteLocator);
    }
}
