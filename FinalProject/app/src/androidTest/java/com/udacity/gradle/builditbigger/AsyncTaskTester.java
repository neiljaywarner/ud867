package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
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
        Espresso.onView(withId(R.id.textViewJoke)).check(ViewAssertions.matches(isValidString()));


    }

    /**
     * Checks to see if a TextView's text is valid - not null, not empty, etc.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> isValidString() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                if (textView == null)
                {
                    return false;
                }

                if (textView.getText() == null)
                {
                    return false;
                }

                if (textView.getText().length() == 0)
                {
                    return false;
                }

                return true;
                //TODO: Code cleanup would be good here.

            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text NOT empty string");
            }
        };
    }

    // ssee; http://www.programcreek.com/java-api-examples/index.php?source_dir=Ironhide-master/lib/src/main/java/com/mindbodyonline/ironhide/Infrastructure/Extensions/TextViewMatchers.java

}
