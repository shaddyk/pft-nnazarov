package com.example.tests;

import com.example.fw.Folders;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Nikita Nazarov
 * Date: 16.12.12
 */
public class FolderTests extends TestBase {

    @Test
    public void testFolderCreation() throws Exception {
        String folder = generateRandomString();
        Folders oldFolders = app.getFolderHelper().getFolders();

        assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
        Folders newFolders = app.getFolderHelper().getFolders();

        assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
    }

    @Test
    public void testSameNamesFoldersCreation() throws Exception {
        String folder = generateRandomString();
        Folders oldFolders = app.getFolderHelper().getFolders();

        assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
        assertThat(app.getFolderHelper().createFolder(folder), containsString("Duplicated folder name"));
        Folders newFolders = app.getFolderHelper().getFolders();

        assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
    }

    @Test
    public void testFolderDelete() throws Exception {
        String folder = generateRandomString();
        Folders oldFolders = app.getFolderHelper().getFolders();

        assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
        Folders newFolders = app.getFolderHelper().getFolders();
        assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));

        app.getFolderHelper().deleteLastFolder();
        newFolders = app.getFolderHelper().getFolders();
        assertThat(newFolders, equalTo(oldFolders));
    }
}
