package com.example.fw;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 20.01.13
 */
public abstract class HelperBase {
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void pause(Integer delay) throws InterruptedException {
        Thread.sleep(delay);
    }
}
