package com.example.tests;

import org.testng.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 24.11.12
 */
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        openMainPage();
        gotoContactsPage();
        ContactData contactData = new ContactData();
        // Проверим, что дефолтные значения корректно переопределяются
        contactData.setBmonth("July");
        fillContact(contactData);
        submitContact();
        returnToMainPage();
        //Тот же метод, что и для групп: ищем в исходном тексте любое упоминание фамилии
        checkCreated(contactData.getLastname());
    }

}
