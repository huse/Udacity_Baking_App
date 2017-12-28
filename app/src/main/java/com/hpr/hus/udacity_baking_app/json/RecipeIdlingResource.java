package com.hpr.hus.udacity_baking_app.json;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hk640d on 12/25/2017.
 */

public class RecipeIdlingResource implements IdlingResource {

    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Nullable
    private volatile ResourceCallback mCallback;


    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);


    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }


}