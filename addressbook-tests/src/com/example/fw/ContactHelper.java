package com.example.fw;

import com.example.tests.ContactObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void submitContact() {
        click(By.name("submit"));
    }

    public void fillContact(ContactObject contactObject) {
        type(By.name("firstname"), contactObject.getFirstname());
        type(By.name("lastname"), contactObject.getLastname());
        type(By.name("address"), contactObject.getAddress());
        type(By.name("home"), contactObject.getPhone());
        type(By.name("mobile"), contactObject.getMphone());
        type(By.name("work"), contactObject.getWphone());
        type(By.name("email"), contactObject.getMail());
        type(By.name("email2"), contactObject.getSmail());
        select(By.name("bday"), contactObject.getBday());
        select(By.name("bmonth"), contactObject.getBmonth());
        type(By.name("byear"), contactObject.getByear());
        type(By.name("address2"), contactObject.getSaddress());
        type(By.name("phone2"), contactObject.getSphone());
    }

    public void selectContactsById(int[] contactIndexes) {
        int contactCount = driver.findElements(By.xpath("//input[@type='checkbox']")).size() - 1;
        for(int i = 0; i < contactIndexes.length; i++) {
            if(contactIndexes[i] < contactCount) {
                click(By.xpath("//*[@id='id" + (contactIndexes[i] + 1) + "']"));
            }
        }
    }

    public void modifyContactByIndex(int index) {
        click(By.xpath("//tr[" + (index + 1) + "]/td[7]/a/img"));
    }

    public void selectContactByLastName(String lastName) {
        click(By.xpath("//tr/td[2][contains(text(),'" + lastName + "')]/../td/input"));
    }

    public void actionByLastName(int action, String lastName) {
        // action map
        //0 - Details
        //1 - Edit
        //2 - Vcard
        //3 - Gmaps
        //4 - Home
        click(By.xpath("//tr/td[2][contains(text(),'" + lastName + "')]/../td["+ (action + 6) +"]/a/img"));
    }


    public void selectAllContacts() {
        //Я видел комментарий про "а выбирается то только один контакт"
        //На последнем чекбоксе висит функция выделения всех доступных контактов
        click(By.xpath("//tbody/tr[last()]/td[1]/input"));
    }

    public void sendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void changeGroup(String groupName) {
        select(By.name("to_group"),groupName);
        click(By.xpath("//input[@name='add']"));
    }

    public void filterByGroup(String groupName) {
        select(By.name("group"),groupName);
    }

    public void updateContact() {
        click(By.xpath("//form[1]/input[@name='update']"));
    }

    public void deleteContact() {
        click(By.xpath("//form[2]/input[@name='update']"));
    }

    public List<ContactObject> getContacts() {
        List<ContactObject> contacts = new ArrayList<ContactObject>();
        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            ContactObject contact = new ContactObject();
            contact.setLastname(checkbox.getAttribute("title").replaceAll("Select \\((.*?)?\\s(.*?)?\\)","$2"));
            contact.setFirstname(checkbox.getAttribute("title").replaceAll("Select \\((.*?)?\\s(.*?)?\\)","$1"));
            contacts.add(contact);
        }
        return contacts;
    }
}


