package com.example.tests;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testNonEmptyGroupCreation() throws Exception {
        openMainPage();
        gotoGroupsPage();
        initGroupCreation();
        GroupData groupData = new GroupData();
        groupData.setFooter("new footer");
        groupData.setHeader("new header");
        groupData.setName("extra new name");
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupsPage();

        // интереса ради добавил проверку того, что группа создалась (хоть и плохую - с xpath не разобрался еще)
        checkCreated(groupData.getName());
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
        openMainPage();
        gotoGroupsPage();
        initGroupCreation();
        GroupData emptyGroupData = new GroupData();
        fillGroupForm(emptyGroupData);
        submitGroupCreation();
        returnToGroupsPage();
    }

}
