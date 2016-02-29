package com.softdesign.school.ui.activities;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

public class ActiveAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
