package com.example.tests;

public class ContactObject implements Comparable<ContactObject>{
    // default values
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
        return "Lastname: " + lastname + ", Firstname: " + firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactObject that = (ContactObject) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getWphone() {
        return wphone;
    }

    public void setWphone(String wphone) {
        this.wphone = wphone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSmail() {
        return smail;
    }

    public void setSmail(String smail) {
        this.smail = smail;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public void setBmonth(String bmonth) {
        this.bmonth = bmonth;
    }

    public String getByear() {
        return byear;
    }

    public void setByear(String byear) {
        this.byear = byear;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

}
