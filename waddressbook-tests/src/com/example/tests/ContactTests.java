package com.example.tests;

import com.example.fw.Contact;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 07.01.13
 */
public class ContactTests extends TestBase {

    @Test
    public void createContactWithValidData() {
        String firstname = generateRandomString();
        String lastname = generateRandomString();

        Contact contact = new Contact()
                .withFirstName(firstname)
                .withLastName(lastname);
        app.getContactHelper().createContact(contact);
        Contact createdContact = app.getContactHelper().getFirstContact();

        assertThat(createdContact,equalTo(contact));
    }

    @Test
    public void deleteAllContactsTest() {
        app.getContactHelper()
                .createContact(new Contact()
                        .withFirstName(generateRandomString())
                        .withLastName(generateRandomString()));
        app.getContactHelper().deleteAllContacts();

        assertThat(app.getContactHelper().editFirstContact(),is("No contacts selected"));
    }
}
