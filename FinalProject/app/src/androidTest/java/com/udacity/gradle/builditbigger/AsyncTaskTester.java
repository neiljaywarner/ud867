package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by neil on 4/2/16.
 */
@RunWith(JUnit4.class)
@LargeTest
public class AsyncTaskTester {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testReturnsSomething()
    {

        Espresso.onView(withId(R.id.buttonTellJoke))
                .perform(ViewActions.click());

        // blocked by espresso knowing async task worker pool is not empty
        MainActivity mainActivity = activityActivityTestRule.getActivity();

        String joke = mainActivity.getJoke();
        Log.i("NJW", "in espresso test: joke="+ joke);
    }
}
