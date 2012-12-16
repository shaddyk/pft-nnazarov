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

        //Использую ListOf чтобы сравнивать порядок следования записей
        //Вопрос в следующем - можно ли как-то сравнивать SortedListOf и простой ListOf?
        //Ни один из ассертов мне не позволил такого сделать, хотя базовый класс у них по идее одинаковый
        //Сравнивать SortedList'ы не имеет смысла, т.к. тогда теряется смысл проверки порядка вывода
        //Пришлось делать лишний метод - getUnsortedContacts

        ListOf<ContactObject> oldList = app.getContactHelper().getUnsortedContacts();
        app.getContactHelper().printPhones();
        ListOf<ContactObject> newList = app.getContactHelper().getPrintedContacts();

        assertThat(newList, equalTo(oldList));
    }
}
