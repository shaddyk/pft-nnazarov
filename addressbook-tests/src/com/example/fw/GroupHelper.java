package com.example.fw;

import com.example.tests.GroupObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void submitGroup() {
        click(By.xpath("//form/input[@type='submit']"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupObject groupObject) {
        type(By.name("group_name"), groupObject.getName());
        type(By.name("group_header"), groupObject.getHeader());
        type(By.name("group_footer"), groupObject.getFooter());
    }

    public void selectGroups(Integer[] groupsIndexes) {
        int groupCount = driver.findElements(By.xpath("//form[2]/input[@type='checkbox']")).size();
        for(int i = 0; i < groupsIndexes.length; i++) {
            if(groupsIndexes[i] <= groupCount) {
               click(By.xpath("//form[2]/input[@type='checkbox'][" + groupsIndexes[i] + "]"));
            }
        }
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void editGroup() {
        click(By.name("edit"));
    }

    public List<GroupObject> getGroups() {
        List<GroupObject> groups = new ArrayList<GroupObject>();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            GroupObject group = new GroupObject();
            group.setName(checkbox.getAttribute("title").replaceAll("Select \\((.*)\\)","$1"));
            groups.add(group);
        }
        return groups;
    }
}
