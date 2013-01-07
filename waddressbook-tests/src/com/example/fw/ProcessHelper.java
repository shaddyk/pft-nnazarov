package com.example.fw;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 07.01.13
 */
public class ProcessHelper extends HelperBase {

    private Process process;

    public ProcessHelper(ApplicationManager manager) {
        super(manager);
    }

    public void startAppUnderTests() throws IOException {
        String command = manager.properties.getProperty("app.path");
        process = Runtime.getRuntime().exec(command);
    }

    public void stopAppUnderTests() {
        process.destroy();
    }
}
