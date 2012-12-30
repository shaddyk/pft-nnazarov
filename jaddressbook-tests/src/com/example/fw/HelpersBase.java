package com.example.fw;

import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 30.12.12
 */
public class HelpersBase {

    protected final ApplicationManager manager;
    protected final JFrameOperator mainFrame;

    public HelpersBase(ApplicationManager applicationManager) {
        this.manager = applicationManager;
        this.mainFrame = manager.getApplication();
    }

    protected String waitMessageDialog(String title, int timeout) {
        long start = System.currentTimeMillis();
        long currentTime = start;
        while(currentTime < start + timeout) {
            JDialog dialog = JDialogOperator.findJDialog(mainFrame.getOwner(), title, false, false);
            if(dialog != null) {
                JDialogOperator dialogOp = new JDialogOperator(dialog);
                String message = new JLabelOperator(dialogOp).getText();
                dialogOp.requestClose();
                return message;
            }
            try {
                Thread.sleep(timeout/5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime = System.currentTimeMillis();
        }
        return null;
    }
}
