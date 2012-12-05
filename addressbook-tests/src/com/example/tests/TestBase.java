package com.example.tests;

import com.example.fw.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */

public class TestBase  {

    public static ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
        app = new ApplicationManager();
    }

    @AfterTest
    public void tearDown() throws Exception {
        app.stop();
    }
}
