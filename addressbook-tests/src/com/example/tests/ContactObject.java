package com.example.tests;

public class ContactObject implements Comparable<ContactObject>{
    // default values
    private String id = "0";
    private String firstname = "firstname";
    private String lastname = "lastname";
    private String address = "address";
    private String phone = "+7 903 555 11 22";
    private String mphone = "mphone";
    private String wphone = "work phone";
    private String mail = "mail@gov.ru";
    private String smail = "second mail";
    private String bday = "5";
    private String bmonth = "May";
    private String byear = "1500";
    private String saddress = "second address";
    private String sphone = "second phone";

    public ContactObject() {
    }

    @Override
    public int compareTo(ContactObject o) {
        if(this.lastname.compareToIgnoreCase(o.lastname) == 0) return this.firstname.compareToIgnoreCase(o.firstname);
        else return this.lastname.compareToIgnoreCase(o.lastname);
    }

    @Override
    public String toString() {
        return "ContactObject [Lastname: " + lastname +
                ", Firstname: " + firstname + ", Mobile phone: " + phone +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactObject that = (ContactObject) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }


    public String getId() {
        return id;
    }

    public ContactObject withId(String id) {
        this.sphone = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public ContactObject withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public ContactObject withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ContactObject withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ContactObject withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMphone() {
        return mphone;
    }

    public ContactObject withMphone(String mphone) {
        this.mphone = mphone;
        return this;
    }

    public String getWphone() {
        return wphone;
    }

    public ContactObject withWphone(String wphone) {
        this.wphone = wphone;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public ContactObject withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getSmail() {
        return smail;
    }

    public ContactObject withSmail(String smail) {
        this.smail = smail;
        return this;
    }

    public String getBday() {
        return bday;
    }

    public ContactObject withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public String getBmonth() {
        return bmonth;
    }

    public ContactObject withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public String getByear() {
        return byear;
    }

    public ContactObject withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public String getSaddress() {
        return saddress;
    }

    public ContactObject withSaddress(String saddress) {
        this.saddress = saddress;
        return this;
    }

    public String getSphone() {
        return sphone;
    }

    public ContactObject withSphone(String sphone) {
        this.sphone = sphone;
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public void setWphone(String wphone) {
        this.wphone = wphone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSmail(String smail) {
        this.smail = smail;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public void setBmonth(String bmonth) {
        this.bmonth = bmonth;
    }

    public void setByear(String byear) {
        this.byear = byear;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }
}
