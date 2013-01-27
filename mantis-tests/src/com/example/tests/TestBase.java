package com.example.tests;

import com.example.fw.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */

public class TestBase  {

    public static ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
        String configFile = System.getProperty("configFile","application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(new File(configFile)));
        app = new ApplicationManager(properties);
    }

    @AfterTest
    public void tearDown() throws Exception {
        app.stop();
    }

    public void createMailUser(UserObject user) {
        if(!app.getJamesHelper().doesUserExist(user.getLogin())){
            app.getJamesHelper().createUser(user.getLogin(), user.getPassword());
        }
    }

    public void removeMantisUsers() {
        if(app.getHibernateHelper().getUsers().size() > 1) {
            app.getHibernateHelper().removeUsersExcludeAdmin();
        }
    }
}
