package tests;

import application.HttpSession;

import dataproviders.MantisUserdataProvider;
import model.MantisUserData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression; // исп-ся биб ка verbalregex для поиска текста-линки подтверждения

import javax.mail.MessagingException;
import java.io.IOException;



/**
 * Created on 16/10/2017. по уроку 8.4
 * регистрация нового пользователя, с предварительным запуском почтового сервера, встроенного непосредственно в тесты,
 * для этого исп бибка subethamail
 * Подмена конфиг файла происходит в Testbase для отключения капчи при регистрации нового пользователя
 * Проверка успешного логина под вновь созданным польз-лем происходит без GUI с помощбю HTTP сессии
 */
public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.MailHelper().start();
    }

    @Test  (dataProviderClass = MantisUserdataProvider.class, dataProvider ="validUsersCSV")
    public void testRegistration(MantisUserData mu) throws IOException, MessagingException {
        String user = mu.username; // "userNN"+  System.currentTimeMillis();;
        String realname = mu.realname; //"Automated testuser";
        String email = mu.email; // user+"@localhost.localdomain";
        String password = mu.password; // user+"_pwd";

        app.registrationHelper().startRegistration(user, email);

        // После того как начался старт регистрации нового пользователя, ему на почту должно прийти подтверждение,
        //сод ссылку, по кот нужно перейти и подтвердить рег-ию
        String link = app.MailHelper().findConfirmationLinkByEmail(email);

        app.registrationHelper().finishRegistration(link, realname, password);


        userLoginCheckWithoutUI(user, password);


    }

    private void userLoginCheckWithoutUI(String username, String password) throws IOException {
        HttpSession session = app.newSession();

        boolean login = session.login(username, password);
        System.out.println("function of login is performed = "+ login);
        Assert.assertTrue(session.isLoggedInAs(username), "строка поиска не найдена");
    }


    @AfterMethod(alwaysRun = true) //чтобы останавливался в случае если тесты не отработали успешно и тп
    public void stopMailServer() {
        app.MailHelper().stop();
    }
}
