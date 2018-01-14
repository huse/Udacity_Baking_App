package com.hpr.hus.udacity_baking_app.idelingTest;

import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hk640d on 12/21/2017.
 */


import android.support.test.espresso.IdlingResource;
public class DetailIdlingResource implements IdlingResource {



    public void settingIdleStatus(boolean isInIdle) {
        atomicBoolean.set(isInIdle);
        if (isInIdle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
    @Nullable
    private volatile ResourceCallback resourceCallback;

    // Idleness is controlled with this boolean.
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return atomicBoolean.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }



}