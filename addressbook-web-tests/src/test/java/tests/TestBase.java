package tests;

import application.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Created by uasso on 07/07/2017.
 */
public class TestBase {


   /// final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
   protected  static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); // в Edit Configurations указ пар р VM machine:  -ea -dBrowser=firefox
// второе значение задает значение браузера по умолчанию
    @BeforeClass
    public void setUp() throws Exception {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }


}
