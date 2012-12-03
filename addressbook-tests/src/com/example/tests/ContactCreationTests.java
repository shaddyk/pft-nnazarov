package com.example.tests;

import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoContactsPage();
        ContactObject contactObject = new ContactObject();
        app.getContactHelper().fillContact(contactObject);
        app.getContactHelper().submitContact();
        app.getNavigationHelper().returnToMainPage();
    }

}
