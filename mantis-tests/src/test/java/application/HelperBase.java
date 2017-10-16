package application;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

/**
 * Created by uasso on 11/07/2017.
 */
public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd= wd;
    }

    protected void type(By locator, String text) {
       if (text!=null) {
           WebElement field = wd.findElement(locator);
           field.click(); // нужен для текстовых полей ввода, но "вреден" для файловых полей
           String existingText = field.getAttribute("value"); // получаем текущее значение поля
           if (!text.equals(existingText)) {
               field.clear();
               field.sendKeys(text);
           }
       }
    }

    protected void attach(By locator, File file) {
        WebElement field = wd.findElement(locator);


        if (file !=null) {

            field.sendKeys(file.getAbsolutePath());
        }

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
