package toandoan.framgia.com.android_12_menu_searchview_dialog.data.model;

import android.database.Cursor;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.ContactContract;

/**
 * Created by framgia on 13/04/2017.
 */

public class Contact {
    private int mId;
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(int id, String name, String phone, String address) {
        mId = id;
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public Contact(Cursor cursor){
        mId = cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry._ID));
        mName = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME));
        mAddress = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_ADDRESS));
        mPhone = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE));
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
