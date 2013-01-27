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
    private Properties properties;

    private NavigationHelper navigationHelper;
    private HibernateHelper hibernateHelper;
    private AccountHelper accountHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
        baseUrl = properties.getProperty("baseUrl");
    }

    public void stop() {
        driver.quit();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
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

    public AccountHelper getAccountHelper() {
        if (accountHelper == null) {
            accountHelper = new AccountHelper(this);
        }
        return accountHelper;
    }

    public NavigationHelper navigateTo(){
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public MailHelper getMailHelper(){
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper getJamesHelper(){
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
}
