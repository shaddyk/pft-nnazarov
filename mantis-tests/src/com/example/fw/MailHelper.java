package com.example.fw;

import javax.mail.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 26.01.13
 */

public class MailHelper extends HelperBase {

    private String mailserver;

    public MailHelper(ApplicationManager manager) {
        super(manager);
        this.mailserver = manager.getProperty("mailserver.host");
    }

    public String getNewMail(String user, String password) throws Exception {
        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);
        Store store = session.getStore("pop3");
        store.connect(mailserver, user, password);
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        if (folder.getMessageCount() != 1) {
            return null;
        }
        Message message = folder.getMessage(1);
        message.setFlag(Flags.Flag.DELETED, true);
        String msg = (String) message.getContent();
        folder.close(true);
        store.close();
        return msg;
    }
}
