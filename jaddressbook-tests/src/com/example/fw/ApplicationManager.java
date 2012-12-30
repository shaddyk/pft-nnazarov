package com.example.fw;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JFrameOperator;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */

public class ApplicationManager {

    public FolderHelper folderHelper;
    public MenuHelper menuHelper;
    private JFrameOperator mainFrame;

    public void stop() {
        getApplication().requestClose();
    }

    public FolderHelper getFolderHelper() {
        if(folderHelper == null) {
            folderHelper = new FolderHelper(this);
        }
        return folderHelper;
    }

    public MenuHelper getMenuHelper() {
        if(menuHelper == null) {
            menuHelper = new MenuHelper(this);
        }
        return menuHelper;
    }

    public JFrameOperator getApplication() {
        if(mainFrame == null) {
            try {
                new ClassReference("addressbook.AddressBookFrame").startApplication();
                mainFrame = new JFrameOperator("jAddressBook");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mainFrame;
    }
}
