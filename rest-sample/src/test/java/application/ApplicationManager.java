package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Svetlana Verkholantceva on 10/07/2017.
 */
public class ApplicationManager {

    private String browser;
    private final Properties properties;


    private RestHelper restHelper;




    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target","local"); // 1-ый параметр название св-ва,
        // которое может быть задано в ком строке, и может быть не определено системой, как в данном случае и вернуло бы без деф
        // значения null;  2-ой пар-р - значение по умолчанию, если  при вызове пар-р не указан

        String propertiesFileName = String.format("src/test/resources/%s.properties", target);
        System.out.println("filename " + propertiesFileName);

       properties.load( new FileReader(new File(propertiesFileName)));


    }


    public void stop() {


    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }





    // ** ЛЕНИВАЯ ИНИЦИАЛИЗАЦИЯ  помощника по REST для исп без интерфейса bugify ** //
    public RestHelper restHelper() {
        if (restHelper== null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}



