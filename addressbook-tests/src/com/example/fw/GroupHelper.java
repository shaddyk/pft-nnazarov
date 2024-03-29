package com.example.fw;

import com.example.tests.GroupObject;
import com.example.utils.SortedListOf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class GroupHelper extends WebDriverHelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public SortedListOf<GroupObject> getGroupsFromUI() {
        SortedListOf<GroupObject> groups = new SortedListOf<GroupObject>();

        manager.navigateTo().groupsPage();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            String name = checkbox.getAttribute("title").replaceAll("Select \\((.*)\\)","$1");
            groups.add(new GroupObject().withName(name));
        }
        return groups;
    }

    private SortedListOf<GroupObject> getGroupsFromDB() {
        return manager.getHibernateHelper().getGroups();
    }

    public GroupHelper createGroup(GroupObject groupObject) {
        manager.navigateTo().groupsPage();
        initGroupCreation();
        fillGroupForm(groupObject);
        submitGroup();
        returnToGroupsPage();
        manager.getModel().addGroup(groupObject);
        return this;
    }

    public GroupHelper deleteGroup(Integer[] toDeleteIndex) {
        manager.navigateTo().groupsPage();
        if(toDeleteIndex != null) selectGroups(toDeleteIndex);
        submitGroupRemoval();
        returnToGroupsPage();
        manager.getModel().removeGroup(toDeleteIndex);
        return this;
    }

    public GroupHelper modifyContact(GroupObject groupObject, Integer[] toModifyIndex) {
        manager.navigateTo().groupsPage();
        selectGroups(toModifyIndex);
        editGroup();
        fillGroupForm(groupObject);
        submitGroup();
        returnToGroupsPage();
        manager.getModel().removeGroup(toModifyIndex).addGroup(groupObject);
        return this;
    }

    //----------------------------------------------------------------------------

    public GroupHelper submitGroup() {
        click(By.xpath("//form/input[@type='submit']"));
        return this;
    }

    public GroupHelper returnToGroupsPage() {
        click(By.linkText("group page"));
        return this;
    }

    public GroupHelper initGroupCreation() {
        click(By.name("new"));
        return this;
    }

    public GroupHelper fillGroupForm(GroupObject groupObject) {
        type(By.name("group_name"), groupObject.getName());
        type(By.name("group_header"), groupObject.getHeader());
        type(By.name("group_footer"), groupObject.getFooter());
        return this;
    }

    public GroupHelper selectGroups(Integer[] groupsIndexes) {
        int groupCount = driver.findElements(By.xpath("//form[2]/input[@type='checkbox']")).size();
        for(int i = 0; i < groupsIndexes.length; i++) {
            if(groupsIndexes[i] <= groupCount) {
               click(By.xpath("//form[2]/input[@type='checkbox'][" + groupsIndexes[i] + "]"));
            }
        }
        return this;
    }

    public GroupHelper submitGroupRemoval() {
        click(By.name("delete"));
        return this;
    }

    public GroupHelper editGroup() {
        click(By.name("edit"));
        return this;
    }

}
