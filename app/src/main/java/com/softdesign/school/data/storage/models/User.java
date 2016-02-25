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

    public User(String mFirstName, String mLastName, Drawable mImage, String mVkLink, String mGitLink, int mRait) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mRate = mRait;
        this.mImage = mImage;
        this.mVkLink = mVkLink;
        this.mGitLink = mGitLink;
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

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmRait(int mRait) {
        this.mRate = mRait;
    }


    public void setmImage(Drawable mImage) {
        this.mImage = mImage;
    }

    public void setmVkLink(String mVkLink) {
        this.mVkLink = mVkLink;
    }

    public void setmGitLink(String mGitLink) {
        this.mGitLink = mGitLink;
    }

    public void saveUserData(String phone, String email, String vk, String git, String bio){

    }
}
