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

    public void mainPage() {
        if (!onMainPage()) {
            click(By.linkText("home"));
        }
    }

    public void groupsPage() {
        if( !onGroupsPage()) {
            click(By.linkText("groups"));    
        }        
    }

    public void addContactPage() {
        if( !onAddContactPage()) {
            click(By.linkText("add new"));
        }
    }

    private boolean onAddContactPage() {
        return driver.findElements(By.name("new_group")).size() != 0;
    }

    private boolean onGroupsPage() {
        if(driver.getCurrentUrl().contains("/groups.php")
                && driver.findElements(By.name("new")).size() > 0) return true;
        return false;
    }

    private boolean onMainPage() {
        return driver.findElements(By.id("maintable")).size() > 0;
    }

}
