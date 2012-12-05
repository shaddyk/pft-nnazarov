package com.example.tests;

import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */
public class ContactTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoContactsPage();
        ContactObject contactObject = new ContactObject();
        app.getContactHelper().fillContact(contactObject);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().returnToMainPage();
    }

    @Test
    public void testContactSendMail() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().selectContactsById(new int[]{5, 55});
        app.getContactHelper().selectContactByLastName("testname");
        //app.getContactHelper().sendEmail();
    }

    @Test
    public void testContactDetails() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().actionByLastName(0,"testname");
    }

    @Test
    public void testModifyContact() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().actionByLastName(1,"lastname");
        ContactObject contactObject = new ContactObject();
        contactObject.setFirstname("NEW FIRST NAME");
        app.getContactHelper().fillContact(contactObject);
        app.getContactHelper().updateContact();
    }

    @Test
    public void testDeleteContact() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().actionByLastName(1,"lastname");
        app.getContactHelper().deleteContact();
    }

    @Test
    public void moveToGroup() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().changeGroup("extra edited name");

    }

}
