package com.example.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.fail;

public class SignUpTests extends TestBase {

    public UserObject user = new UserObject()
            .withLogin("testuser")
            .withEmail("testuser@localhost.localdomain")
            .withPassword("123456");

    @BeforeClass
    public void prepareMailAndDB() {
        createMailUser(user);
        removeMantisUsers();
    }

    @AfterClass
    public void сlearMantisUsers() {
        removeMantisUsers();
    }

    @Test
    public void signUpForNewUserTest() throws Exception {
        app.getAccountHelper().signUp(user);
        assertThat(app.getAccountHelper().loggedUser(),equalTo(user.getLogin()));
        app.getAccountHelper().logOut();
    }

    @Test
    public void signUpWithExistingUserTest() throws Exception {
        try {
            app.getAccountHelper().signUp(user);
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("username is already being used"));
            return;
        }
        fail("Exception expected");
    }

}
