package com.example.fw;

import org.netbeans.jemmy.operators.JMenuBarOperator;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 30.12.12
 */
public class MenuHelper extends HelpersBase {

    public MenuHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void pushCreateFolder() {

        JMenuBarOperator menu = new JMenuBarOperator(mainFrame);
        menu.pushMenuNoBlock("File|New folder...");
    }

    public void pushDeleteFolder() {

        JMenuBarOperator menu = new JMenuBarOperator(mainFrame);
        menu.pushMenuNoBlock("File|Delete");
    }
}
