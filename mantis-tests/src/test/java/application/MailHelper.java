package application;

import model.MailMessage;
import org.subethamail.wiser.Wiser; // подключена
import org.subethamail.wiser.WiserMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by uasso on 17/10/2017.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout ) {

            if ( wiser.getMessages().size() >= count ) {
                System.out.println("Ура, список не пуст");
                return  messageList(wiser);  // преобразуем в список формата " кому-тело письма"

            }
            try
                {Thread.sleep(1000);}
            catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
        }
        throw new Error ("No Mail! :(");
    }

    private List<MailMessage> messageList(Wiser wiser) throws MessagingException {
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
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
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


    public String findConfirmationLinkByEmail(List<MailMessage> messages, String email) {
        // ** Находим сообщение адресованное нужному имэйлу
        MailMessage mailMessage=  messages.stream().filter((m) -> m.to.equals(email)).findAny().get();

        // для поиска линки подтв-ия в письме используется биб ка verbalregex. Она упрощает построение регулярных выражений
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

        return regex.getText(mailMessage.text);// regex.getText возвращает найденный кусок регулярного выражения в тексте писбма
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}
