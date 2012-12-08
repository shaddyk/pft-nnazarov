package com.example.tests;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class GroupTests extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testGroupCreationWithValidData(GroupObject groupObject) throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();

        List<GroupObject> newList = app.getGroupHelper().getGroups();
        oldList.add(groupObject);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }

    @Test
    public void testGroupCreationWithInvalidData() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().initGroupCreation();
        GroupObject groupObject = new GroupObject();
        groupObject.setName("invalid ' name");
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();

        List<GroupObject> newList = app.getGroupHelper().getGroups();
        assertEquals(newList, oldList);
    }

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testEditSomeGroup(GroupObject groupObject) throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupObject> oldList = app.getGroupHelper().getGroups();
        Random rnd = new Random();

        Integer[] toModify = new Integer[]{rnd.nextInt(oldList.size()-2) + 1};
        app.getGroupHelper().selectGroups(toModify);
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();

        List<GroupObject> newList = app.getGroupHelper().getGroups();
        for(int index : toModify) {
            if(index <= oldList.size() + 1) oldList.remove(index - 1);
            oldList.add(groupObject);
        }
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }

    @Test
    public void testRemoveSomeGroups() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupObject> oldList = app.getGroupHelper().getGroups();

        Random rnd = new Random();
        Integer[] toDelete = new Integer[]{rnd.nextInt(5)+1, rnd.nextInt(25)+1};
        app.getGroupHelper().selectGroups(toDelete);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().returnToGroupsPage();

        //Без сортировки получается, что индекс сползает и удаляются не те записи из листа
        Arrays.sort(toDelete, Collections.reverseOrder());
        List<GroupObject> newList = app.getGroupHelper().getGroups();
        for(int index : toDelete) {
            if(index <= oldList.size()) oldList.remove(index - 1);
        }
        assertEquals(newList, oldList);
    }

    @Test
    public void testRemoveZeroGroups() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupObject> oldList = app.getGroupHelper().getGroups();

        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().returnToGroupsPage();

        List<GroupObject> newList = app.getGroupHelper().getGroups();
        assertEquals(newList, oldList);
    }

}
