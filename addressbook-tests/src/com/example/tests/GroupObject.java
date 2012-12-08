package com.example.tests;

public class GroupObject implements Comparable<GroupObject> {
    private String name = "new name";
    private String header = "new header";
    private String footer = "new footer";

    public GroupObject() {
    }

    @Override
    public String toString() {
        return name + " " + header + " " + footer;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public int compareTo(GroupObject o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }
}
