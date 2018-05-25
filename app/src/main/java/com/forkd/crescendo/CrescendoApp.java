package com.forkd.crescendo;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class CrescendoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
