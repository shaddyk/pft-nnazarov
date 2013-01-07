package com.example.fw;

import java.io.File;

import autoitx4java.AutoItX;

import com.jacob.com.ComThread;
import com.jacob.com.LibraryLoader;

public class AutoItHelper extends HelperBase {

    static {
        File jacob = new File("../lib/jacob-1.17-M2-x86.dll");
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacob.getAbsolutePath());
    }

    private String winTitle;
    private String winText;
    private final AutoItX aux;

    public AutoItHelper(ApplicationManager app) {
        super(app);
        ComThread.InitMTA();
        aux = new AutoItX();
    }

    public AutoItHelper winWaitAndActivate(String winTitle, String winText, int timeout) {
        this.winTitle = winTitle;
        this.winText = winText;
        aux.winWait(winTitle, winText, timeout);
        aux.winActivate(winTitle, winText);
        aux.winWaitActive(winTitle, winText, timeout);
        System.out.println("Window activated: " + winTitle);
        return this;
    }

    public AutoItHelper send(String text) {
        aux.send(text);
        return this;
    }

    public AutoItHelper send(String controlID, String text) {
        aux.controlFocus(winTitle, winText, controlID);
        aux.send(text);
        return this;
    }
    
    public AutoItHelper click(String controlID) {
        aux.controlClick(winTitle, winText, controlID);
        System.out.println("Control clicked: " + controlID);
        return this;
    }

    public String getText(String controlID) {
        return aux.controlGetText(winTitle, winText, controlID);
    }

    public boolean checkWinActive (String winTitle) {
        return aux.winWaitActive(winTitle, "", 5);
    }

}
