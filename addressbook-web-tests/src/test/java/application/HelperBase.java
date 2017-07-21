package application;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by uasso on 11/07/2017.
 */
public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd= wd;
    }

    protected void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    protected boolean isElementPresent(By locator) {
        try {
            WebElement e = wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}
