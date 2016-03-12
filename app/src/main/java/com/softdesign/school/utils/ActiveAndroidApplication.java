package com.softdesign.school.utils;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

public class ActiveAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
