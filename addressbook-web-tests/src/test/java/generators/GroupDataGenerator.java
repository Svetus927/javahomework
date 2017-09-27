package generators;
// в Edit Configuration . Program arguments пишем -f src/test/resources/groups.csv -c 5 -d xml
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *  30/07/2017. Класс генерирующий тестовые данные для групп ( GroupData)
 */
public class GroupDataGenerator {
// здесь испоьзуется библиотека jCommander для анализа параметров из ком строки. Документация на jcommander.org
    @Parameter(names = "-c", description = "group count")
    //        название опции,  описание
    public int count;

    @Parameter(names = "-f", description = "target file")
    public String file;

    @Parameter(names = "-d", description = "data format")
    public String format;

// раз это запускаемый класс то дб функция main
    public static void  main(String[] args) throws IOException {
        // здесь мы используем возможность запуска с параметрами из коммандной строки и укажем там сколько и таргетфайл

        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            ex.usage(); // в случае оишбки выводится  текст о досутпных опциях с помощбю метода usage
            return;
        }
        generator.run();


    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv") ) {
            saveAsCsv(groups, new File(file));
        }
        else  if (format.equals("xml") ) {
            saveAsXml(groups, new File(file));
            }
            else {
            System.out.println("Unrecognized format "+ format);
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        // XStream - биб ка для работы с Файлами xml
        XStream xStream = new XStream();
        // названия тэгов можно настроить 2 способами : или через alias или processannotations
        xStream.alias("group", GroupData.class); // настраиваем  названия тэга для разделений объектов
        // др способ указать как называть тэги это прописать в самом классе GroupData (см начало класса)  и потом здесь
        // указать processAnnotations:
        xStream.processAnnotations(GroupData.class);

        String xml = xStream.toXML(groups);  // делает из объекта представление в виде xml
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {

        System.out.println(new File(".").getAbsolutePath()); // на всякий случай узнаем текущую директорию для этой проги
        Writer writer = new FileWriter(file);
        for (GroupData group: groups ) {
            writer.write(String.format("%s;%s;%s\n", group.name(),group.header(), group.footer()));
        }
        writer.close();

    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i =0; i< count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }  //в String.format вместо % подставляется значение второго параметра)
        return groups;
    }
}
