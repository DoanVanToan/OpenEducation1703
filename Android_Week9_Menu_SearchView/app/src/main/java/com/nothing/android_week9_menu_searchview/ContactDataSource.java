package com.nothing.android_week9_menu_searchview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by THM on 4/17/2017.
 */
public class ContactDataSource extends DataBaseHelper {
    public ContactDataSource(Context context) {
        super(context);
    }

    public List<Contact> getAllContact() {
        List<Contact> result = new ArrayList<>();
        String[] columns = {
            ContactContract.ContractEntry._ID,
            ContactContract.ContractEntry.COLUMN_NAME,
            ContactContract.ContractEntry.COLUMN_PHONE,
            ContactContract.ContractEntry.COLUMN_ADDRESS
        };
        SQLiteDatabase db = getWritableDatabase();
        String orderBy = ContactContract.ContractEntry._ID + " DESC";
        Cursor cursor = db.query(
            ContactContract.ContractEntry.TABLE_NAME,
            columns,
            null, // collection
            null, // collection args
            null, // group by
            null, // group by args
            orderBy, // order
            null //limit
        );
        if (cursor != null && cursor.moveToFirst()) {
            // have data
            do {
                result.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }
        // no data
        cursor.close();
        db.close();
        return result;
    }

    public long insertContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.ContractEntry.COLUMN_NAME, contact.getName());
        values.put(ContactContract.ContractEntry.COLUMN_ADDRESS, contact.getAddress());
        values.put(ContactContract.ContractEntry.COLUMN_PHONE, contact.getNumber());
        long result = db.insert(ContactContract.ContractEntry.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public boolean updateContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContactContract.ContractEntry.COLUMN_NAME, contact.getName());
        values.put(ContactContract.ContractEntry.COLUMN_ADDRESS, contact.getAddress());
        values.put(ContactContract.ContractEntry.COLUMN_PHONE, contact.getNumber());
        String whereClause = ContactContract.ContractEntry._ID + "=?";
        String[] whereClauseArgs = {String.valueOf(contact.getId())};
        int result = db.update(ContactContract.ContractEntry.TABLE_NAME, values, whereClause,
            whereClauseArgs);
        db.close();
        return result > 0;
    }

    public boolean deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = ContactContract.ContractEntry._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        long result = db.delete(ContactContract.ContractEntry.TABLE_NAME, whereClause, whereArgs);
        db.close();
        return result > 0;
    }
}
