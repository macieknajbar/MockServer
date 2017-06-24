package com.example.mockserver.main;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.mockserver.R;
import com.example.mockserver.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public class MainActivityTest_Java {

    @Rule public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    private final String MOCK_SERVER = "Mock Server";

    @Test public void seesListOfProjectsAndOneSpecificThatSheWasSearchingFor() {
        // TODO: Finish test and change repo's history
        // Betty opens application
        activity.launchActivity(new Intent());

        // And sees a progress bar indicating lazy loading data from server
        onView(ViewMatchers.withId(R.id.progress_bar))
                .check(matches(isDisplayed()));

        // Boom! Progress bar disappeared and a list showed up
        // FIXME: wait until list is displayed - consider idling resources
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.progress_bar))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.project_list))
                .check(matches(isDisplayed()));
        // Among the projects Betty found the project that she was interested in named "Mock server"
        onView(withText(MOCK_SERVER))
                .check(matches(isDisplayed()));
        // TODO: When considering article about TDD, now the article got too long
//        onData(withText(MOCK_SERVER)).perform(scrollTo());
    }
}
