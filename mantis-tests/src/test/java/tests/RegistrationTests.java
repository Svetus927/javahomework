package tests;

import application.HttpSession;
import model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression; // используется биб ка verbalregex для поиска линки подтверждения3

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by uasso on 16/10/2017. по уроку 8.4
 * регистрация нового пользователя, с предварительным запуском постового сервера. Подмена конфиг файла происходит в Testbase
 */
public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.MailHelper().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        String user = "userN"+  System.currentTimeMillis();;
        String realname = "Automated testuser";
        String email = user+"@localhost.localdomain";
        String password = user+"_pwd";

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
        System.out.println("login is "+ login);
        Assert.assertTrue(session.isLoggedInAs(username), "строка поиска не найдена");
    }

   /*  вынесено в  класс MailHelper для  использования в др тестах
   private String findConfirmationLinkByEmail(List<MailMessage> messages, String email) {
        // ** Находим сообщение адресованное нужному имэйлу
       MailMessage mailMessage=  messages.stream().filter((m) -> m.to.equals(email)).findAny().get();

       // для поиска линки подтв-ия в письме используется биб ка verbalregex. Она упрощает построение регулярных выражений
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

        return regex.getText(mailMessage.text);// regex.getText возвращает найденный кусок регулярного выражения в тексте писбма
    }  */


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.MailHelper().stop();
    }
}
