package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public abstract class WebDriverHelperBase extends HelperBase {
    protected WebDriver driver;

    public WebDriverHelperBase(ApplicationManager manager) {
        super(manager);
        this.manager = manager;
        this.driver = manager.getDriver();
    }

    protected void type(By locator, String text) {
        if(text != null) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void select(By locator, String text) {
        if(text != null) {
            new Select(driver.findElement(locator)).selectByVisibleText(text);
        }
    }
}
