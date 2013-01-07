package com.example.fw;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 07.01.13
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(Contact contact) {
        initContactCreation();
        fillContactForm(contact);
        confirmContactCreation();
    }

    private void initContactCreation() {
        manager.getAutoItHelper()
                .winWaitAndActivate("AddressBook Portable","", 5)
                .click("Add")
                .winWaitAndActivate("Add Contact","", 5);
    }

    private void fillContactForm(Contact contact) {
        manager.getAutoItHelper()
                .send("TDBEdit12", contact.getFirstName())
                .send("TDBEdit11", contact.getLastName());
    }

    private void confirmContactCreation() {
        manager.getAutoItHelper()
                .click("Save")
                .winWaitAndActivate("AddressBook Portable", "", 5);
    }

    public Contact getFirstContact() {
        editFirstContact();
        Contact contact = new Contact()
                .withFirstName(getFirstName())
                .withLastName(getLastName());
        cancelContactEditing();
        return contact;
    }

    private String getLastName() {
        return manager.getAutoItHelper().getText("TDBEdit11");
    }

    private String getFirstName() {
        return manager.getAutoItHelper().getText("TDBEdit12");
    }

    private void cancelContactEditing() {
        manager.getAutoItHelper()
                .click("Cancel")
                .winWaitAndActivate("AddressBook Portable", "", 5);
    }

    public String editFirstContact() {
        selectFirstContact();
        manager.getAutoItHelper()
                .click("Edit")
                .winWaitAndActivate("Information", "", 1);
        if(manager.getAutoItHelper().checkWinActive("Information"))  {
            return "No contacts selected";
        }
        manager.getAutoItHelper()
                .winWaitAndActivate("Update Contact", "", 5);
        return null;
    }

    private void selectFirstContact() {
        manager.getAutoItHelper()
                .winWaitAndActivate("AddressBook Portable", "", 5)
                .send("{DOWN}");
    }

    public void deleteAllContacts() {
        manager.getAutoItHelper()
                .winWaitAndActivate("AddressBook Portable", "", 5)
                .click("Select All")
                .click("Delete")
                .winWaitAndActivate("Confirm", "", 5)
                .click("TButton2")
                .winWaitAndActivate("AddressBook Portable", "", 5);
    }

}
