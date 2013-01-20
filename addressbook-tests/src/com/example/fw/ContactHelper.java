package com.example.fw;

import com.example.tests.ContactObject;
import com.example.utils.ListOf;
import com.example.utils.SortedListOf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class ContactHelper extends WebDriverHelperBase {

    private SortedListOf<ContactObject> cachedContacts;

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public SortedListOf<ContactObject> getContacts() {
        if(cachedContacts == null) {
            rebuildCache();
        }
        return cachedContacts;
    }

    private void rebuildCache() {
        manager.navigateTo().mainPage();
        cachedContacts= new SortedListOf<ContactObject>();
        List<WebElement> rows = getRows();
        for (WebElement row : rows) {
            ContactObject contact = new ContactObject()
                .withLastname(getLastname(row))
                .withFirstname(getFirstname(row))
                .withPhone(getPhone(row));
            cachedContacts.add(contact);
        }
    }

    public ContactHelper createContact (ContactObject contactObject) {
        manager.navigateTo().addContactPage();
        fillContact(contactObject);
        submitContact();
        manager.navigateTo().mainPage();
        rebuildCache();
        return this;
    }

    public ContactHelper modifyContact (ContactObject contactObject, Integer toModify) {
        manager.navigateTo().mainPage();
        initContactModification(toModify);
        fillContact(contactObject);
        updateContact();
        manager.navigateTo().mainPage();
        rebuildCache();
        return this;
    }

    public ContactHelper deleteContact (Integer toDelete) {
        manager.navigateTo().mainPage();
        initContactModification(toDelete);
        deleteContact();
        manager.navigateTo().mainPage();
        rebuildCache();
        return this;
    }

    public ContactHelper moveAllContactsToGroup (String groupName) {
        manager.navigateTo().mainPage();
        selectAllContacts();
        changeGroup(groupName);
        manager.navigateTo().mainPage();
        filterByGroup(groupName);
        rebuildCache();
        return this;
    }

    public ListOf<ContactObject> getUnsortedContacts() {
        manager.navigateTo().mainPage();
        ListOf<ContactObject> unsortedContacts= new ListOf<ContactObject>();
        List<WebElement> rows = getRows();
        for (WebElement row : rows) {
            ContactObject contact = new ContactObject()
                    .withLastname(getLastname(row))
                    .withFirstname(getFirstname(row))
                    .withPhone(getPhone(row));
            unsortedContacts.add(contact);
        }
        return unsortedContacts;
    }

    public ListOf<ContactObject> getPrintedContacts() {
        ListOf<ContactObject> printedContacts= new ListOf<ContactObject>();
        List<WebElement> contacts = driver.findElements(By.xpath("//td[@valign='top']"));
        String[] splitted;
        String phone = "";

        for (WebElement cont : contacts) {
            splitted = cont.getText().split("\n");
            //Присваиваем переменной phone значение телефона, который выше всех в таблице
            for(int i = splitted.length - 1;i > 0; i--) {
                if(splitted[i].matches("[H,M,W]: (.*)")) phone = splitted[i].replaceAll("[H,M,W]: (.*)","$1");
            }

            //Чистим от мусора
            phone = phone.replaceAll("\\s+","");

            ContactObject contact = new ContactObject()
                    .withLastname(splitted[0].replaceAll("(.*?)\\s(.*?):","$2"))
                    .withFirstname(splitted[0].replaceAll("(.*?)\\s(.*?):","$1"))
                    .withPhone(phone);
            printedContacts.add(contact);
        }

        return printedContacts;
    }
    //-----------------------------------------------------------------------------------------------

    public ContactHelper submitContact() {
        click(By.name("submit"));
        cachedContacts = null;
        return this;
    }

    public ContactHelper fillContact(ContactObject contactObject) {
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
        return this;
    }

    public ContactHelper selectContactsById(int[] contactIndexes) {
        int contactCount = driver.findElements(By.xpath("//input[@type='checkbox']")).size() - 1;
        for(int i = 0; i < contactIndexes.length; i++) {
            if(contactIndexes[i] < contactCount) {
                click(By.xpath("//*[@id='id" + (contactIndexes[i] + 1) + "']"));
            }
        }
        return this;
    }

    public ContactHelper initContactModification(int index) {
        click(By.xpath("//tr[" + (index + 2) + "]/td[7]/a/img"));
        return this;
    }

    public ContactHelper selectContactByLastName(String lastName) {
        click(By.xpath("//tr/td[2][contains(text(),'" + lastName + "')]/../td/input"));
        return this;
    }

    public ContactHelper selectAllContacts() {
        click(By.xpath("//tbody/tr[last()]/td[1]/input"));
        return this;
    }

    public ContactHelper sendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
        return this;
    }

    public ContactHelper changeGroup(String groupName) {
        select(By.name("to_group"),groupName);
        click(By.xpath("//input[@name='add']"));
        cachedContacts = null;
        return this;
    }

    public ContactHelper filterByGroup(String groupName) {
        select(By.name("group"),groupName);
        cachedContacts = null;
        return this;
    }

    public ContactHelper updateContact() {
        click(By.xpath("//form[1]/input[@name='update']"));
        cachedContacts = null;
        return this;
    }

    public ContactHelper deleteContact() {
        click(By.xpath("//form[2]/input[@name='update']"));
        cachedContacts = null;
        return this;
    }

    public ContactHelper printPhones() {
        click(By.xpath("//li[6]/a"));
        return this;
    }

    private List<WebElement> getRows() {
        return driver.findElements(By.xpath("//tbody/tr[@name='entry']"));
    }

    private String getFirstname(WebElement row) {
        return row.findElement(By.xpath(".//td[3]")).getText();
    }

    private String getPhone(WebElement row) {
        return row.findElement(By.xpath(".//td[5]")).getText();
    }

    private String getLastname(WebElement row) {
        return row.findElement(By.xpath(".//td[2]")).getText();
    }

}


