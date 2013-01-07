package com.example.fw;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 01.12.12
 */
public class ApplicationManager {

    public Properties properties;
    private ContactHelper contactHelper;
    private ProcessHelper processHelper;
    private AutoItHelper autoItHelper;

    public ApplicationManager(Properties properties) throws IOException {
        this.properties = properties;
        getProcessHelper().startAppUnderTests();
    }

    public void stop() {
        getProcessHelper().stopAppUnderTests();
    }

    public ContactHelper getContactHelper() {
        if (contactHelper == null) {
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }

    public ProcessHelper getProcessHelper() {
        if (processHelper == null) {
            processHelper = new ProcessHelper(this);
        }
        return processHelper;
    }

    public AutoItHelper getAutoItHelper() {
        if (autoItHelper == null) {
            autoItHelper = new AutoItHelper(this);
        }
        return autoItHelper;
    }

}
