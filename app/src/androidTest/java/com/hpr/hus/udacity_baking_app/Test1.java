package com.hpr.hus.udacity_baking_app;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by hk640d on 1/8/2018.
 */

public class Test1 {
    public static ViewAction ViewId(final int ids) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(ids);
                v.performClick();
            }
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "ids.";
            }


        };
    }
}

