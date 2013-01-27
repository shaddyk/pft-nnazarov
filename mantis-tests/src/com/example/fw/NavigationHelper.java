package com.example.fw;

import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class NavigationHelper extends WebDriverHelperBase {

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void signUpPage() {
        click(By.xpath("//span[1]/a"));
    }

    public void openUrl(String url) {
        driver.get(url);
    }
}
