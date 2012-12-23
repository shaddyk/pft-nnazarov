package com.example.tests;

import com.example.utils.ListOf;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 16.12.12
 */
public class PrintTests extends TestBase {

    @Test
    public void testPrintContactsData() throws Exception {

        ListOf<ContactObject> oldList = app.getContactHelper().getUnsortedContacts();
        app.getContactHelper().printPhones();
        ListOf<ContactObject> newList = app.getContactHelper().getPrintedContacts();

        assertThat(newList, equalTo(oldList));
        assertThat(newList, equalTo(oldList));
    }
}
