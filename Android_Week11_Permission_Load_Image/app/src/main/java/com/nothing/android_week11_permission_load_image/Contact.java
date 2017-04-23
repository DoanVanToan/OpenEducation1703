package com.nothing.android_week11_permission_load_image;

/**
 * Created by THM on 4/23/2017.
 */
public class Contact {
    private String mName, mNumber;

    public Contact(String name, String number) {
        mName = name;
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }
}
