package com.example.tests;

import com.example.fw.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    public String generateRandomString() {
        char[] alphabet = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String string = "";
        Random rnd = new Random();
        int length = rnd.nextInt(10);

        for(int i = 0; i < length; i++) {
            string = string + alphabet[rnd.nextInt(alphabet.length)];
        }

        return string;
    }

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        List<Object[]> list = new ArrayList<Object[]>();

        for(int i = 0; i < 3; i++) {
            GroupObject groupObject = new GroupObject();
            groupObject.setName(generateRandomString());
            groupObject.setFooter(generateRandomString());
            groupObject.setHeader(generateRandomString());
            list.add(new Object[]{groupObject});
        }

        return list.iterator();
    }
}
