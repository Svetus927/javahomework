package tests;

import application.DbJdbcHelper;
import application.HttpSession;
import model.MailMessage;
import model.MantisUserData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static tests.TestBase.app;

/**
 * task 18  self doing  on 09/11/2017.
 * Реализовать тест для смены пароля в MantisBT
 Реализовать сценарий смены пароля пользователю баг-трекера MantisBT администратором системы:

 Администратор входит в систему, переходит на страницу управления пользователями, выбирает заданного пользователя и нажимает кнопку Reset Password
 Отправляется письмо на адрес пользователя, тесты должны получить это письмо, извлечь из него ссылку для смены пароля, пройти по этой ссылке и изменить пароль.
 Затем тесты должны проверить, что пользователь может войти в систему с новым паролем.
 Изменить конфигурацию MantisBT можно вручную, не обязательно подменять конфигурационный файл при запуске тестов. Пользователя тоже можно заранее создать вручную.

 Однако получить информацию об идентификаторе и/или логине пользователя тесты должны самостоятельно во время выполнения.
 Можно это сделать, например, загрузив информацию о пользователях из базы данных.

 Почтовый сервер можно запускать непосредственно внутри тестов.

 Шаги 1 и 2 необходимо выполнять через пользовательский интерфейс, а шаг 3 можно выполнить на уровне протокола HTTP.
 */
public class PaswordModificationTests extends TestBase{

    String username ="svakok11";//+System.currentTimeMillis();
    String useremail = "svakok@localhost.localdomain";
    int createdUserId;
            
    @BeforeMethod
    public void loginAsAdminCreateUser() throws IOException, MessagingException {
       DbJdbcHelper db = new DbJdbcHelper();

        app.MailHelper().start();
        createdUserId = db.createUserWithoutUIandGetId(username, useremail );

   //    String link = app.MailHelper().findConfirmationLinkByEmail(useremail);   похоже не просит подтверждение писбма  потому что создание не через UI ,
        // когда просит подтверждение пароля
    //    app.registrationHelper().setupPassword(link,  "kvakva");

        Boolean loggedAsAdmin = app.navigationHelper().loginAsAdmininUI(app.getProperty("web.AdminLogin"), app.getProperty("web.AdminPassword"));
        System.out.println("loggedAsAdmin "+ loggedAsAdmin);
        Assert.assertTrue(loggedAsAdmin, "Залогиниться под админом не удалось!");


    }



     //@BeforeMethod
    public void startMailServer() {
        app.MailHelper().start();
    }


    @Test
    public void testChangePasswordSuccess() throws IOException, MessagingException {
        app.navigationHelper().gotoManageUsersPage();

        MantisUserData user = app.manageUserHelper().findUserToManage(createdUserId);

        String newPassword =  "hahaha1234";

        app.manageUserHelper().startResetPassword();

     // После того как начался старт регистрации нового пользователя, ему на почту должно прийти подтверждение,
     //сод ссылку, по кот нужно перейти и подтвердить рег-ию
     String link = app.MailHelper().findConfirmationLinkByEmail( user.email);
     app.manageUserHelper().finishResetPassword(link, user.realname, newPassword);


     userLoginCheckWithoutUI(user.username, newPassword);


 }

    private void userLoginCheckWithoutUI(String username, String password) throws IOException {
        HttpSession session = app.newSession();

        boolean login = session.login(username, password);

        Assert.assertTrue(session.isLoggedInAs(username), "строка поиска не найдена");
    }

    @AfterMethod
    public void stopMailServer() {
        app.MailHelper().stop();
    }
}
