package com.example.tests;

import com.example.utils.SortedListOf;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupTests extends TestBase {

    @Test(dataProvider = "groupsFromXml")
    public void testGroupCreationWithValidData(GroupObject groupObject) throws Exception {
        SortedListOf<GroupObject> oldList = app.getModel().getGroups();

        app.getGroupHelper().createGroup(groupObject);
        SortedListOf<GroupObject> newList = app.getModel().getGroups();

        assertThat(newList, equalTo(oldList.withAdded(groupObject)));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().getGroups()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getGroupsFromUI()));
            }
        }
    }

    @Test(dataProvider = "groupsFromCsv")
    public void testEditSomeGroup(GroupObject groupObject) throws Exception {
        Random rnd = new Random();
        SortedListOf<GroupObject> oldList = app.getModel().getGroups();
        Integer[] toModify = new Integer[]{rnd.nextInt(oldList.size()-2) + 1};

        app.getGroupHelper().modifyContact(groupObject, toModify);
        SortedListOf<GroupObject> newList = app.getModel().getGroups();

        assertThat(newList, equalTo(oldList.without(toModify).withAdded(groupObject)));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().getGroups()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getGroupsFromUI()));
            }
        }
    }

    @Test
    public void testGroupCreationWithInvalidData() throws Exception {
        SortedListOf<GroupObject> oldList = app.getModel().getGroups();
        GroupObject groupObject = new GroupObject().withName("invalid ' name");

        app.getGroupHelper().createGroup(groupObject);
        SortedListOf<GroupObject> newList = app.getModel().getGroups();

        assertThat(newList, equalTo(oldList));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().getGroups()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getGroupsFromUI()));
            }
        }
    }

    @Test
    public void testRemoveSomeGroups() throws Exception {
        Random rnd = new Random();
        Integer[] toDelete = new Integer[]{rnd.nextInt(5)+1, rnd.nextInt(25)+1};
        Arrays.sort(toDelete, Collections.reverseOrder());
        SortedListOf<GroupObject> oldList = app.getModel().getGroups();

        app.getGroupHelper().deleteGroup(toDelete);
        SortedListOf<GroupObject> newList = app.getModel().getGroups();

        assertThat(newList, equalTo(oldList.without(toDelete)));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().getGroups()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getGroupsFromUI()));
            }
        }
    }

    @Test
    public void testRemoveZeroGroups() throws Exception {
        SortedListOf<GroupObject> oldList = app.getModel().getGroups();

        app.getGroupHelper().deleteGroup(null);
        SortedListOf<GroupObject> newList = app.getModel().getGroups();

        assertThat(newList, equalTo(oldList));

        if(timeToCheck()) {
            if(app.getProperty("check.db").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().getGroups()));
            }
            if(app.getProperty("check.ui").equals("yes")) {
                assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getGroupsFromUI()));
            }
        }
    }

}
