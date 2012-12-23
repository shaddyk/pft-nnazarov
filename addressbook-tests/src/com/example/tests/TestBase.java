package com.example.tests;

import com.example.fw.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static com.example.tests.ContactObjectGenerator.*;
import static com.example.tests.GroupObjectGenerator.*;

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

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        return wrapGroupsForDataProvider(generateRandomGroups(3)).iterator();
    }

    public static List<Object[]> wrapGroupsForDataProvider(List<GroupObject> groups) {
        List<Object[]> list = new ArrayList<Object[]>();
        for(GroupObject group : groups) {
            list.add(new Object[]{group});
        }
        return list;
    }

    @DataProvider
    public Iterator<Object[]> groupsFromXml() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
    }

    @DataProvider
    public Iterator<Object[]> groupsFromCsv() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromCsvFile("groups.csv")).iterator();
    }

    @DataProvider
    public Iterator<Object[]> randomContactGenerator() {
        return wrapContactsForDataProvider(generateRandomContacts(3)).iterator();
    }

    public static List<Object[]> wrapContactsForDataProvider(List<ContactObject> contacts) {
        List<Object[]> list = new ArrayList<Object[]>();
        for(ContactObject contact : contacts) {
            list.add(new Object[]{contact});
        }
        return list;
    }

    @DataProvider
    public Iterator<Object[]> contactsFromXml() throws IOException {
        return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactsFromCsv() throws IOException {
        return wrapContactsForDataProvider(loadContactsFromCsvFile("contacts.csv")).iterator();
    }
}
