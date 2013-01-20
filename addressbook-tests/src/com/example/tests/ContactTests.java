package com.example.tests;


import com.example.utils.SortedListOf;
import org.testng.annotations.Test;

import java.util.Random;

import static com.example.tests.ContactObjectGenerator.generateRandomString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */
public class ContactTests extends TestBase {

    @Test(dataProvider = "contactsFromXml")
    public void testContactCreation(ContactObject contactObject) throws Exception {
        SortedListOf<ContactObject> oldList = app.getModel().getContacts();
        app.getContactHelper().createContact(contactObject);
        SortedListOf<ContactObject> newList = app.getModel().getContacts();

        assertThat(newList, equalTo(oldList.withAdded(contactObject)));
    }

    @Test(dataProvider = "contactsFromCsv")
    public void testContactModification(ContactObject contactObject) throws Exception {
        SortedListOf<ContactObject> oldList = app.getModel().getContacts();
        Random rnd = new Random();
        Integer toModify = rnd.nextInt(oldList.size()-1);

        app.getContactHelper().modifyContact(contactObject, toModify);
        SortedListOf<ContactObject> newList = app.getModel().getContacts();

        assertThat(newList, equalTo(oldList.without(toModify + 1).withAdded(contactObject)));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().getContacts()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getContactsFromUI()));
            }
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        SortedListOf<ContactObject> oldList = app.getModel().getContacts();
        Random rnd = new Random();
        Integer toDelete = rnd.nextInt(oldList.size()-2);

        app.getContactHelper().deleteContact(toDelete);
        SortedListOf<ContactObject> newList = app.getModel().getContacts();

        assertThat(newList, equalTo(oldList.without(toDelete + 1)));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().getContacts()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getContactsFromUI()));
            }
        }
    }

    @Test
    public void testContactMoveToGroup() throws Exception {
        String generatedName = generateRandomString();
        app.getGroupHelper().createGroup(new GroupObject().withName(generatedName));
        SortedListOf<ContactObject> oldList = app.getModel().getContacts();

        app.getContactHelper().moveAllContactsToGroup(generatedName);
        SortedListOf<ContactObject> newList = app.getModel().getContacts();

        assertThat(newList, equalTo(oldList));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().getContacts()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getContactsFromUI()));
            }
        }
    }

}
