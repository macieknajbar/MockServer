package com.example.mockserver;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.mockserver.responses.Repos;
import com.example.mockserver.rest.ServerInjector;
import com.example.mockserver.rest.server.MockServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest_Java {

    @Rule public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class, false, false);

    private final String MOCK_SERVER = "Mock Server";

    private MockServer mockServer;

    @Before public void setUp() {
        mockServer = (MockServer) ServerInjector.INSTANCE.server();
    }

    @Test public void seesListOfProjectsAndOneSpecificThatSheWasSearchingFor() throws InterruptedException, IOException {
        mockServer.rewriteResponse("users/octocat/repos", 200, Repos.INSTANCE.listOfRepos());

        // Betty opens application
        activity.launchActivity(new Intent());

        // List of repositories showed up
        onView(withId(R.id.project_list))
                .check(matches(isDisplayed()));

        // Among the projects Betty found the project that she was interested in named "Mock server"
        onView(withText(MOCK_SERVER))
                .check(matches(isDisplayed()));
    }

    @Test public void displaysErrorToUserOnServerError() {
        mockServer.rewriteResponse("users/octocat/repos", 404, "");

        // Bety opens the application
        activity.launchActivity(new Intent());

        // An error occurs and application shows a message "Oops.. Something went wrong"
        onView(withText("Oops.. Something went wrong"))
                .check(matches(isDisplayed()));
    }
}
