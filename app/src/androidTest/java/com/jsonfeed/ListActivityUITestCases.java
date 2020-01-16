package com.jsonfeed;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.partyplanner.jsonfeed.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListActivityUITestCases {


    @Test
    public void testSampleRecyclerVisible() {
        sleep();
        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);



    @Test
    public void testCaseForRecyclerScroll() {
        sleep();
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.my_recycler_view);
        int itemCount = recyclerView.getAdapter().getItemCount();

        Espresso.onView(ViewMatchers.withId(R.id.my_recycler_view))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    private void sleep(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}