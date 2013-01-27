package com.example.tests;

public class UserObject implements Comparable<UserObject> {
    private String id = "0";
    private String login = "new login";
    private String password = "123456";
    private String email = "test@localhost.localdomain";

    public UserObject() {
    }

    @Override
    public String toString() {
        return "UserObject [Login: " + login + ", Email: " + email + ", Password: " + password +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserObject that = (UserObject) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public int compareTo(UserObject o) {
        return this.login.compareToIgnoreCase(o.login);
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserObject withId(String id) {
        this.id = id;
        return this;
    }

    public UserObject withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserObject withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserObject withEmail(String email) {
        this.email = email;
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
