package com.example.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class ApplicationManager {
    
    private WebDriver driver;
    public String baseUrl;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private HibernateHelper hibernateHelper;
    private Properties properties;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
        baseUrl = properties.getProperty("baseUrl");
    }

    public void stop() {
        driver.quit();
    }

    public NavigationHelper navigateTo(){
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }
    public GroupHelper getGroupHelper() {
        if (groupHelper == null) {
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        if (contactHelper == null) {
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            if(properties.getProperty("browser").equals("firefox")){
                driver = new FirefoxDriver();
            } else if (properties.getProperty("browser").equals("ie")){
                driver = new InternetExplorerDriver();
            } else if (properties.getProperty("browser").equals("chrome")){
                driver = new ChromeDriver();
            } else {
                throw new Error("Unsupported browser");
            }
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("timeout")), TimeUnit.SECONDS);
            driver.get(baseUrl);
        }
        return driver;
    }

    public HibernateHelper getHibernateHelper() {
        if (hibernateHelper == null) {
            hibernateHelper = new HibernateHelper(this);
        }
        return hibernateHelper;
    }
}
