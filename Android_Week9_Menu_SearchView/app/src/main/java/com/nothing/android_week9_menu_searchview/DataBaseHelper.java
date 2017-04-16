package com.nothing.android_week9_menu_searchview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by THM on 4/17/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "contact.db";
    private final static int DB_VERSION = 1;
    private final static String COMMAND_CREATE_CONTACT_TABLE = "CREATE TABLE "
        + ContactContract.ContractEntry.TABLE_NAME
        + " ( "
        + ContactContract.ContractEntry._ID
        + " INTEGER PRIMARY KEY, "
        + ContactContract.ContractEntry.COLUMN_NAME
        + " TEXT, "
        + ContactContract.ContractEntry.COLUMN_PHONE
        + " TEXT, "
        + ContactContract.ContractEntry.COLUMN_ADDRESS
        + " TEXT)";
    private final static String COMMAND_DROP_CONTACT_TABLE = "DROP TABLE " + ContactContract
        .ContractEntry.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMMAND_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(COMMAND_DROP_CONTACT_TABLE);
        db.execSQL(COMMAND_CREATE_CONTACT_TABLE);
    }

    // 1 get all contact from db

}
