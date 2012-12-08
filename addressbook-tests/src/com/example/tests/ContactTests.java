package com.example.tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */
public class ContactTests extends TestBase {

    @Test(dataProvider = "randomContactGenerator")
    public void testContactCreation(ContactObject contactObject) throws Exception {
        app.getNavigationHelper().openMainPage();

        List<ContactObject> oldList = app.getContactHelper().getContacts();

        app.getNavigationHelper().addNewContact();
        app.getContactHelper().fillContact(contactObject);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().returnToMainPage();

        List<ContactObject> newList = app.getContactHelper().getContacts();
        oldList.add(contactObject);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }

    //@Test
    public void testContactSendMail() throws Exception {
        Random rnd = new Random();
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().selectContactsById(new int[]{rnd.nextInt(5)+1, rnd.nextInt(55)+1});
        app.getContactHelper().sendEmail();
    }

    @Test(dataProvider = "randomContactGenerator")
    public void testModifyContact(ContactObject contactObject) throws Exception {
        Random rnd = new Random();
        app.getNavigationHelper().openMainPage();

        List<ContactObject> oldList = app.getContactHelper().getContacts();
        Integer toModify = rnd.nextInt(oldList.size()-2);

        app.getContactHelper().modifyContactByIndex(toModify+1);
        app.getContactHelper().fillContact(contactObject);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().returnToMainPage();

        List<ContactObject> newList = app.getContactHelper().getContacts();
        oldList.remove((int)toModify);
        oldList.add(contactObject);

        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }

    @Test
    public void testDeleteContact() throws Exception {
        Random rnd = new Random();
        app.getNavigationHelper().openMainPage();

        List<ContactObject> oldList = app.getContactHelper().getContacts();
        Integer toModify = rnd.nextInt(oldList.size()-2);

        app.getContactHelper().modifyContactByIndex(toModify + 1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToMainPage();

        List<ContactObject> newList = app.getContactHelper().getContacts();
        oldList.remove((int)toModify);
        assertEquals(newList, oldList);
    }

    @Test
    public void moveToGroup() throws Exception {
        app.getNavigationHelper().openMainPage();

        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().initGroupCreation();
        GroupObject groupObject = new GroupObject();
        String generatedName = generateRandomString();
        groupObject.setName(generatedName);
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().openMainPage();

        List<ContactObject> oldList = app.getContactHelper().getContacts();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().changeGroup(generatedName);
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().filterByGroup(generatedName);

        List<ContactObject> newList = app.getContactHelper().getContacts();
        assertEquals(newList,oldList);
    }

}
