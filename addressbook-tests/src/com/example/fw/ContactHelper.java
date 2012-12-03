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

}
