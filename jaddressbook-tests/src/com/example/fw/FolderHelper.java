package com.example.fw;

import org.netbeans.jemmy.operators.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 30.12.12
 */
public class FolderHelper extends HelpersBase {

    public FolderHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public Folders getFolders() {
        List<String> list = new ArrayList<String>();
        JTreeOperator tree = new JTreeOperator(mainFrame);
        Object[] children = tree.getChildren(tree.getRoot());
        for (Object child : children) {
            list.add(child.toString());
        }
        return new Folders(list);
    }

    public String createFolder(String folderName) {
        manager.getMenuHelper().pushCreateFolder();
        JDialogOperator dialog = new JDialogOperator(mainFrame);
        new JTextFieldOperator(dialog).setText(folderName);
        new JButtonOperator(dialog, "OK").push();
        return waitMessageDialog("Warning", 2000);
    }

    public void selectLastFolder() {
        JTreeOperator tree = new JTreeOperator(mainFrame);
        tree.selectRow(tree.getRowCount() - 1);
    }

    public void deleteLastFolder () {
        selectLastFolder();
        manager.getMenuHelper().pushDeleteFolder();
        JDialogOperator dialog = new JDialogOperator(mainFrame);
        new JButtonOperator(dialog, "Yes").push();
    }

}
