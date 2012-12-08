package com.example.fw;

import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openMainPage() {
        driver.get(manager.baseUrl + "addressbookv4.1.4/");
    }

    public void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    public void gotoGroupsPage() {
        click(By.linkText("groups"));
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void returnToMainPage() {
        click(By.linkText("home page"));
    }
}
