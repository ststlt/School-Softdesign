package com.softdesign.school.utils;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.softdesign.school.data.storage.models.UserActive;

import java.util.ArrayList;

public class UserAsyncLoader extends AsyncTaskLoader<ArrayList<UserActive>> {
    public UserAsyncLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<UserActive> loadInBackground() {
        ArrayList<UserActive> users = new ArrayList<UserActive>();
        users.addAll(UserActive.getAllUsers());
        return users;
    }
}
