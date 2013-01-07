package com.example.fw;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 07.01.13
 */
public class Contact {

    public String firstName = "defaultFirstName";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact [Lastname: " + getLastName() + ",Firstname: " + getFirstName() +"]";
    }

    public String lastName = "defaultLastName";

    public Contact withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Contact withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
