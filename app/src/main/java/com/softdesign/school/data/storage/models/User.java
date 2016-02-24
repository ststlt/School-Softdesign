package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

public class User {

    private String mFirstName;
    private String mLastName;
    private int mRate;
    private Drawable mImage;
    private String mVkLink;
    private String mGitLink;
    private int mHomeTask;

    public User(Drawable mImage, String mLastName, String mFirstName) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mImage = mImage;
    }

    public String getmFirstName() {
        return this.mFirstName;
    }

    public String getmLastName() {
        return this.mLastName;
    }

    public int getmRate() {
        return this.mRate;
    }

    public Drawable getmImage() {
        return this.mImage;
    }

    public String getmVkLink() {
        return this.mVkLink;
    }

    public String getmGitLink() {
        return this.mGitLink;
    }

    public int getmHomeTask() {
        return this.mHomeTask;
    }
}
