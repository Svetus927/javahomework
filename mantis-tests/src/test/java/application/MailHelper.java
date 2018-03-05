package application;

import model.MailMessage;
import org.subethamail.wiser.Wiser; // подключена для иниц-ии почты сервера всроенного в тесты
import org.subethamail.wiser.WiserMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 17/10/2017.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;  // это и есть почтовый сервер, вначале его инициализируем, потом стопим.
    // чтобы настроить систему мантис на наш собст почтовый сервер нужно в конфиг файле config_inc, на кот
    // подменяем в самом начале для отключения капчи добавить настройки сервера
   // $g_phpMailer_method = PHPMAILER_METHOD_SMTP;
   // $g_smtp_port = '25';
    //$g_smtp_host = 'localhost';


    public MailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout ) {

            if ( wiser.getMessages().size() >= count ) {
                System.out.println("Ура, список не пуст, писем " + wiser.getMessages().size());
                return  makeModelMailList(wiser);  // преобразуем в список формата " кому-тело письма"
            }
            try
                {Thread.sleep(1000);} // 1000 millis = 10 sec
            catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
        }
        throw new Error ("No Mail come during a period of 100 sec! :(");
    }

    private List<MailMessage> makeModelMailList(Wiser wiser) throws MessagingException {
        List<MailMessage> mailMessages = new ArrayList<MailMessage>();
        List<WiserMessage> wiserMessages = wiser.getMessages();
        for (WiserMessage m: wiserMessages) {
            MailMessage mailmessage = makeModelMail(m); // разбиваем сообщение на парный объект кому-тело письма
            mailMessages.add(mailmessage);

        }
        return mailMessages;

    }

    public  static MailMessage makeModelMail(WiserMessage m) throws MessagingException {
        // ** разбивает сообщение на парный объект кому-тело письма  **//
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent()); // создаем новый объект
                                    // типа MailMessage  с параметрами recipient, content (остальное не надо)
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String findConfirmationLinkByEmail( String email) throws IOException, MessagingException {

        // Должно прийти 2 письма (одно этому юзеру, а второе админу) и ждем мы их 10000 милисекунд:
        List<MailMessage> messages =  app.MailHelper().waitForMail(1,100000);

        // ** Находим сообщение адресованное нужному имэйлу:
        MailMessage mailMessage=  messages.stream().filter((m) -> m.to.equals(email)).findAny().get();

        // для поиска линки подтв-ия в письме используется биб ка verbalregex. Она упрощает построение регулярных выражений
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

        return regex.getText(mailMessage.text);// regex.getText возвращает найденный кусок регулярного выражения в тексте письма
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}
