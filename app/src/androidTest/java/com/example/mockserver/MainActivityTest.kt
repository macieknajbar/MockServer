package com.example.mockserver

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Main activity test.
 *
 * Testing mock server's data returned.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test fun seesListOfProjectsAndOneSpecificThatSheWasSearchingFor() {
        // Betty opens application
        // And sees a progress bar indicating lazy loading data from server
        // Boom! Progress bard disappeared and a list showed up
        // Among the projects Betty found the project that she was interested in named "Mock server"
    }
}