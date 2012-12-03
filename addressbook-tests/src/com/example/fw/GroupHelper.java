package com.example.fw;

import com.example.tests.GroupObject;
import org.openqa.selenium.By;

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

    public void selectGroups(int[] groupsIndexes) {
        int groupCount = driver.findElements(By.xpath("//form[2]/input[@type='checkbox']")).size();
        for(int i = 0; i < groupsIndexes.length; i++) {
            if(groupsIndexes[i] < groupCount) {
                driver.findElement(By.xpath("//form[2]/input[@type='checkbox'][" + groupsIndexes[i] + "]")).click();
            }
        }
    }

    public void deleteGroup() {
        driver.findElement(By.name("delete")).click();
    }

    public void editGroup() {
        driver.findElement(By.name("edit")).click();
    }
}
