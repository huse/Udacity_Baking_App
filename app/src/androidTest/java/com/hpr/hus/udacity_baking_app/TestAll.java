package com.hpr.hus.udacity_baking_app;

import android.os.Build;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by hk640d on 1/9/2018.
 */

public class TestAll {
    private IdlingResource idlingResource;
    @Rule
    public IntentsTestRule<MainActivity> testRuleActivity =
            new IntentsTestRule<>(MainActivity.class);



    @BeforeClass
    public static void grantPhonePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getTargetContext().getPackageName()
                            + " android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    @Before
    public void registerIdlingResource() {
        idlingResource = testRuleActivity.getActivity().getIdlingResource();
       // Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void allInteractionTest() {
        onView(withId(R.id.recipe_fragment_main_activity)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_recipe_recyclerview_)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_recipe_recyclerview_))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(RecipeDetailActivity.class.getName()));
        onView(withId(R.id.recycler_view_recipe_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_recipe_detail))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.steps_detail_text2)).check(matches(isDisplayed()));



    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

}
