package com.example.tests;

import org.testng.annotations.Test;

public class GroupTests extends TestBase {

    @Test
    public void testNonEmptyGroupCreation() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().initGroupCreation();
        GroupObject groupObject = new GroupObject();
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().initGroupCreation();
        GroupObject emptyGroupObject = new GroupObject();
        emptyGroupObject.setFooter("");
        emptyGroupObject.setHeader("");
        emptyGroupObject.setName("");
        app.getGroupHelper().fillGroupForm(emptyGroupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();
    }

    @Test
    public void testRemoveSomeGroups() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().selectGroups(new int[]{1, 2, 150, 5});
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().returnToGroupsPage();
    }

    @Test
    public void testRemoveZeroGroups() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().returnToGroupsPage();
    }

    @Test
    public void testEditSomeGroup() throws Exception {
        app.getNavigationHelper().openMainPage();
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().selectGroups(new int[] {3});
        app.getGroupHelper().editGroup();
        GroupObject groupObject = new GroupObject();
        groupObject.setName("extra edited name");
        app.getGroupHelper().fillGroupForm(groupObject);
        app.getGroupHelper().submitGroup();
        app.getNavigationHelper().returnToGroupsPage();
    }
}
