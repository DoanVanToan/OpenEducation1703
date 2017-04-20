package toandoan.framgia.com.android_12_menu_searchview_dialog.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.ContactContract;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 17/04/2017.
 */

public class ContactDataSource extends DatabaseHelper {
    public ContactDataSource(Context context) {
        super(context);
    }

    // 1 get all contact from db
    public List<Contact> getAllContacts() {
        List<Contact> result = new ArrayList<>();
        String[] columns = {
                ContactContract.ContactEntry._ID, ContactContract.ContactEntry.COLUMN_PHONE,
                ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };
        // 1  open db
        SQLiteDatabase database = getWritableDatabase();
        String oderBy = ContactContract.ContactEntry._ID + " DESC";
        // 2. querry de lay ra cursor
        Cursor cursor = database.query(ContactContract.ContactEntry.TABLE_NAME, columns, // columns
                null,       // Collection
                null,       // Conlection Args
                null,       // Group by
                null,       // Group by args
                oderBy,     // oder
                null);      // limit
        // 3. parse cursor to get result
        if (cursor != null && cursor.moveToFirst()) {
            // Có dữ liệu
            do {
                Contact contact = new Contact(cursor);
                result.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        // Ko có dữ liệu
        // 4. close db
        database.close();

        return result;
    }

    public long insertContact(Contact contact) {
        // 1. open db
        SQLiteDatabase database = getWritableDatabase();

        // 2 khai baso content value
        ContentValues values = new ContentValues();
        values.put(ContactContract.ContactEntry.COLUMN_NAME, contact.getName());
        values.put(ContactContract.ContactEntry.COLUMN_PHONE, contact.getPhone());
        values.put(ContactContract.ContactEntry.COLUMN_ADDRESS, contact.getAddress());
        long result = database.insert(ContactContract.ContactEntry.TABLE_NAME, null, values);

        database.close();
        return result;
    }

    public boolean deleteContactById(int id) {
        // 1 open db
        SQLiteDatabase database = getWritableDatabase();

        String whereClause = ContactContract.ContactEntry._ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        long result =
                database.delete(ContactContract.ContactEntry.TABLE_NAME, whereClause, whereArgs);

        database.close();
        return result > 0;
    }

    public boolean updateContact(Contact contact) {
        // 1 open db
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ContactContract.ContactEntry.COLUMN_NAME, contact.getName());
        values.put(ContactContract.ContactEntry.COLUMN_PHONE, contact.getPhone());
        values.put(ContactContract.ContactEntry.COLUMN_ADDRESS, contact.getAddress());


        String whereClause = ContactContract.ContactEntry._ID + " =?";
        String[] whereArgs = { String.valueOf(contact.getId()) };

        int result = database.update(ContactContract.ContactEntry.TABLE_NAME,
                values, whereClause, whereArgs);

        database.close();

        return result > 0;
    }
}
