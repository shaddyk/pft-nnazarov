package com.example.tests;

public class GroupObject implements Comparable<GroupObject> {
    private String name = "new name";
    private String header = "new header";
    private String footer = "new footer";

    public GroupObject() {
    }

    @Override
    public String toString() {
        return "GroupObject [Name: " + name + ", Header: " + header + ", Footer: " + footer +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupObject that = (GroupObject) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(GroupObject o) {
        return this.name.compareToIgnoreCase(o.name);
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public GroupObject withName(String name) {
        this.name = name;
        return this;
    }

    public GroupObject withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public GroupObject withHeader(String header) {
        this.header = header;
        return this;
    }
}
