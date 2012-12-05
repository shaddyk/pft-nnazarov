package com.example.fw;

import com.example.tests.ContactObject;
import org.openqa.selenium.By;

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

    public void selectContactByLastName(String lastName) {
        int contactCount = driver.findElements(By.xpath("//input[@type='checkbox']")).size() - 1;
        for(int i = 0; i < contactCount; i++) {
            if(driver.findElement(By.xpath("//tbody/tr[" + (i + 2) + "]/td[2]")).getText().equals(lastName)) {
                click(By.xpath("//tbody/tr[" + (i + 2) + "]/td[1]/input"));
            }
        }
    }

    public void actionByLastName(int action, String lastName) {
        // action map
        //0 - Details
        //1 - Edit
        //2 - Vcard
        //3 - Gmaps
        //4 - Home

        int contactCount = driver.findElements(By.xpath("//input[@type='checkbox']")).size() - 1;
        for(int i = 0; i < contactCount; i++) {
            if(driver.findElement(By.xpath("//tbody/tr[" + (i + 2) + "]/td[2]")).getText().equals(lastName)) {
                click(By.xpath("//tbody/tr[" + (i + 2) + "]/td["+ (action + 6) +"]/a/img"));
                break;
            }
        }
    }


    public void selectAllContacts() {
        click(By.xpath("//tbody/tr[last()]/td[1]/input"));
    }

    public void sendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void changeGroup(String groupName) {
        select(By.name("to_group"),groupName);
        click(By.xpath("//input[@name='add']"));
    }

    public void updateContact() {
        click(By.xpath("//form[1]/input[@name='update']"));
    }

    public void deleteContact() {
        click(By.xpath("//form[2]/input[@name='update']"));
    }


}


