package com.example.fw;

import com.example.tests.ContactObject;
import com.example.tests.GroupObject;
import com.example.utils.SortedListOf;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 20.01.13
 */
public class ApplicationModel {
    private SortedListOf<GroupObject> groups;
    private SortedListOf<ContactObject> contacts;

    public SortedListOf<GroupObject> getGroups() {
        return groups;
    }

    public void setGroups(SortedListOf<GroupObject> groups) {
        this.groups = new SortedListOf<GroupObject>(groups);
    }

    public SortedListOf<ContactObject> getContacts() {
        return contacts;
    }

    public void setContacts(SortedListOf<ContactObject> contacts) {
        this.contacts = new SortedListOf<ContactObject>(contacts);
    }

    public ApplicationModel addGroup(GroupObject groupObject) {
        if (groupObject.getName().contains("'")) {
            return this;
        }
        groups = groups.withAdded(groupObject);
        return this;
    }

    public ApplicationModel removeGroup(Integer[] toDeleteIndex) {
        if (toDeleteIndex != null) {
            groups = groups.without(toDeleteIndex);
        }
        return this;
    }

    public ApplicationModel addContact(ContactObject contactObject) {
        contacts = contacts.withAdded(contactObject);
        return this;
    }

    public ApplicationModel removeContact(Integer toDeleteIndex) {
        contacts = contacts.without(toDeleteIndex + 1);
        return this;
    }
}
