package application;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by uasso on 11/07/2017.
 */
public class HelperBase {
    public FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd= wd;
    }

    protected void type(By locator, String text) {
        wd.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }
}
