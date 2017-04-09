package com.nothing.android_facebook_layout;

import java.io.Serializable;

/**
 * Created by THM on 4/6/2017.
 */
public class StatusItem implements Serializable{
    private String mName, mTime, mStatus;
    private int mImage, mAvatar;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }

    public StatusItem(String name, String time, String status, int image, int avatar) {

        mName = name;
        mTime = time;
        mStatus = status;
        mImage = image;
        mAvatar = avatar;
    }
}
