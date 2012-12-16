package com.example.tests;

import com.example.utils.SortedListOf;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupTests extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testGroupCreationWithValidData(GroupObject groupObject) throws Exception {
        SortedListOf<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().createGroup(groupObject);
        SortedListOf<GroupObject> newList = app.getGroupHelper().getGroups();

        assertThat(newList, equalTo(oldList.withAdded(groupObject)));
    }

    @Test
    public void testGroupCreationWithInvalidData() throws Exception {
        SortedListOf<GroupObject> oldList = app.getGroupHelper().getGroups();
        GroupObject groupObject = new GroupObject().withName("invalid ' name");

        app.getGroupHelper().createGroup(groupObject);
        SortedListOf<GroupObject> newList = app.getGroupHelper().getGroups();

        assertThat(newList, equalTo(oldList));
    }

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testEditSomeGroup(GroupObject groupObject) throws Exception {
        Random rnd = new Random();
        SortedListOf<GroupObject> oldList = app.getGroupHelper().getGroups();
        Integer[] toModify = new Integer[]{rnd.nextInt(oldList.size()-2) + 1};

        app.getGroupHelper().modifyContact(groupObject, toModify);
        SortedListOf<GroupObject> newList = app.getGroupHelper().getGroups();

        assertThat(newList, equalTo(oldList.without(toModify).withAdded(groupObject)));
    }

    @Test
    public void testRemoveSomeGroups() throws Exception {
        Random rnd = new Random();
        Integer[] toDelete = new Integer[]{rnd.nextInt(5)+1, rnd.nextInt(25)+1};
        Arrays.sort(toDelete, Collections.reverseOrder());
        SortedListOf<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().deleteGroup(toDelete);
        SortedListOf<GroupObject> newList = app.getGroupHelper().getGroups();

        assertThat(newList, equalTo(oldList.without(toDelete)));
    }

    @Test
    public void testRemoveZeroGroups() throws Exception {
        SortedListOf<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().deleteGroup(null);
        SortedListOf<GroupObject> newList = app.getGroupHelper().getGroups();

        assertThat(newList, equalTo(oldList));
    }

}
