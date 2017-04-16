package com.nothing.android_week9_menu_searchview;

import android.database.Cursor;

/**
 * Created by THM on 4/13/2017.
 */
public class Contact {
    private int mId;
    private String mName;
    private String mNumber;
    private String mAddress;
    private String mSearch = "";

    public Contact(String name, String number, String address) {
        mName = name;
        mNumber = number;
        mAddress = address;
    }

    public Contact(int id, String name, String number, String address) {
        mId = id;
        mName = name;
        mNumber = number;
        mAddress = address;
    }
    public Contact(Cursor cursor){
        mId = cursor.getInt(cursor.getColumnIndex(ContactContract.ContractEntry._ID));
        mName = cursor.getString(cursor.getColumnIndex(ContactContract
            .ContractEntry.COLUMN_NAME));
        mNumber = cursor.getString(cursor.getColumnIndex(ContactContract
            .ContractEntry.COLUMN_PHONE));
        mAddress = cursor.getString(cursor.getColumnIndex(ContactContract
            .ContractEntry.COLUMN_ADDRESS));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSearch() {
        return mSearch;
    }

    public void setSearch(String search) {
        mSearch = search;
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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
