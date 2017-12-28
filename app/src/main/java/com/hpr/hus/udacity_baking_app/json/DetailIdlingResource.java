package com.hpr.hus.udacity_baking_app.json;

import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hk640d on 12/21/2017.
 */
import android.support.annotation.Nullable;


import java.util.concurrent.atomic.AtomicBoolean;
import android.support.test.espresso.IdlingResource;
public class DetailIdlingResource implements IdlingResource {



    public void setIdleStatus(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
    @Nullable
    private volatile ResourceCallback mCallback;

    // Idleness is controlled with this boolean.
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }



}