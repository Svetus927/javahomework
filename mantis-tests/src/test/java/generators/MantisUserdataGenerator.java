package generators;

/**
 * Created by uasso on 01/03/2018.
 */

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import model.MantisUserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



//  СКОПИРОВАНО ИЗ  МОДУЛЯ ADDRESSBOOK WEBTESTS
// в Edit Configuration  Program arguments пишем -f src/test/resources/groups.csv -c 5 -d xml
// ( порядок параметров не важен главное чтоб было перед значением название опции

/**
 *  30/07/2017. Класс генерирующий тестовые данные для групп ( GroupData)
 */
public class MantisUserdataGenerator {
    // здесь используется библиотека jCommander для анализа параметров из ком строки. Документация на jcommander.org
    @Parameter(names = "-c", description = "user count")
    //        название опции,  описание
    public int count;

    @Parameter(names = "-f", description = "target file")
    public String file;

    @Parameter(names = "-d", description = "data format")
    public String format;

    // раз это запускаемый класс то дб функция main
    public static void  main(String[] args) throws IOException {
        // здесь мы используем возможность запуска с параметрами из коммандной строки и укажем там
        // сколько и таргетфайл

        MantisUserdataGenerator generator = new MantisUserdataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            ex.usage(); // в случае оишбки выводится  текст о досутпных опциях с помощью метода usage
            return;
        }


        generator.run(); // запкск собственно генератора
    }


    // **** Собственно генерация   **** //
    private static List<MantisUserData> generateUsers(int count) {
        List<MantisUserData> users = new ArrayList<MantisUserData>();
        for (int i =0; i< count; i++) {
            String usertmp = String.format("testuser%s", i*10);  //в String.format вместо % подстав-ся значение второго параметра)
            users.add(new MantisUserData().withUsername(usertmp)
                             .withRealname(usertmp+ " realname")
                             .withEmail(usertmp+"@localhost.localdomain")
                             .withPassword(usertmp+"_pwd"));
        }
        return users;
    }

    private void run() throws IOException {
        List<MantisUserData> users = generateUsers(count);

        if (format.equals("csv") ) {
            saveAsCsv(users, new File(file));
        }
        else  if (format.equals("xml") ) {
            saveAsXml(users, new File(file));
        }
        else {
            System.out.println("Unrecognized format "+ format);
        }
    }

    private void saveAsXml(List<MantisUserData> users, File file) throws IOException {
        // XStream - биб ка для работы с Файлами xml
        XStream xStream = new XStream();
        // названия тэгов можно настроить 2 способами : или через alias или processannotations
        xStream.alias("mantisUser", MantisUserData.class); // настраиваем  названия тэга для разделений объектов
        // др способ указать как называть тэги это прописать в самом классе GroupData (см начало класса)  и потом здесь
        // указать processAnnotations:
        xStream.processAnnotations(MantisUserData.class);

        String xml = xStream.toXML(users);  // делает из объекта представление в виде xml
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCsv(List<MantisUserData> users, File file) throws IOException {

        // ** на всякий случай узнаем текущую директорию для этой проги  //
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (MantisUserData user: users ) {
            writer.write(String.format("%s;%s;%s\n", user.username,user.realname, user.email));
        }
        writer.close();

    }




}
