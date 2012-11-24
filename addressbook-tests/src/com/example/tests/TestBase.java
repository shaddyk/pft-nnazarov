package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */

public class TestBase {
    private static WebDriver driver;
    private static String baseUrl;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    protected void returnToGroupsPage() {
        driver.findElement(By.linkText("group page")).click();
    }

    public void checkCreated(String name) {
        assertTrue(driver.findElement(By.xpath("//html")).getText().contains(name));
    }

    protected void submitGroupCreation() {
        driver.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
        driver.findElement(By.name("group_name")).clear();
        driver.findElement(By.name("group_name")).sendKeys(groupData.getName());
        driver.findElement(By.name("group_header")).clear();
        driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        driver.findElement(By.name("group_footer")).clear();
        driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
        driver.findElement(By.name("new")).click();
    }

    protected void gotoGroupsPage() {
        driver.findElement(By.linkText("groups")).click();
    }

    protected void openMainPage() {
        driver.get(baseUrl + "/addressbookv4.1.4/");
    }

    public void gotoContactsPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void fillContact(ContactData contactData) {
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("home")).clear();
        driver.findElement(By.name("home")).sendKeys(contactData.getPhone());
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(contactData.getMphone());
        driver.findElement(By.name("work")).clear();
        driver.findElement(By.name("work")).sendKeys(contactData.getWphone());
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(contactData.getMail());
        driver.findElement(By.name("email2")).clear();
        driver.findElement(By.name("email2")).sendKeys(contactData.getSmail());
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
        driver.findElement(By.name("byear")).clear();
        driver.findElement(By.name("byear")).sendKeys(contactData.getByear());
        driver.findElement(By.name("address2")).clear();
        driver.findElement(By.name("address2")).sendKeys(contactData.getSaddress());
        driver.findElement(By.name("phone2")).clear();
        driver.findElement(By.name("phone2")).sendKeys(contactData.getSphone());
    }

    protected void returnToMainPage() {
        driver.findElement(By.linkText("home page")).click();
    }

    protected void submitContact() {
        driver.findElement(By.name("submit")).click();
    }
}
