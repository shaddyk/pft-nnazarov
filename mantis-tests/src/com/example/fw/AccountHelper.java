package com.example.fw;

import com.example.tests.UserObject;
import org.openqa.selenium.By;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 26.01.13
 */
public class AccountHelper extends WebDriverHelperBase {

    public AccountHelper(ApplicationManager manager) {
        super(manager);
    }


    public void signUp(UserObject user) throws Exception{
        manager.navigateTo().signUpPage();
        fillUserData(user);

        String errorMessage = getErrorMessage();
        if(errorMessage != null) {
            throw new Exception(errorMessage);
        }

        pause(15000);
        manager.navigateTo().openUrl(getConfirmationLink(
                manager.getMailHelper().getNewMail(user.getLogin(), user.getPassword())));
        updatePassword(user);
    }

    public String loggedUser() {
        return driver.findElement(By.xpath("//td[@class='login-info-left']/span[1]")).getText();
    }

    private void fillUserData(UserObject user) {
        type(By.name("username"), user.getLogin());
        type(By.name("email"), user.getEmail());
        click(By.xpath("//input[@type='submit']"));
    }

    private void updatePassword(UserObject user) {
        type(By.name("password"), user.getPassword());
        type(By.name("password_confirm"), user.getPassword());
        click(By.xpath("//input[@type='submit']"));
    }

    public void login(UserObject user) {
        manager.navigateTo().openUrl(manager.getProperty("baseUrl"));
        type(By.name("username"), user.getLogin());
        type(By.name("password"), user.getPassword());
        click(By.xpath("//input[@type='submit']"));
    }

    public String getConfirmationLink(String text) {
        Pattern regex = Pattern.compile("http\\S*");
        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(By.xpath("//td/p[@style='color:red']")).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void logOut() {
        click(By.xpath("//table[2]/tbody/tr/td[1]/a[last()]"));
    }
}
