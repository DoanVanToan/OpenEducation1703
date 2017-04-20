package toandoan.framgia.com.android_12_menu_searchview_dialog.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.ContactContract;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 17/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "contact.db";
    private final static int DB_VERSION = 2;

    private final static String COMMAND_CREATE_CONTACT_TABLE = "CREATE TABLE "
            + ContactContract.ContactEntry.TABLE_NAME
            + "( "
            + ContactContract.ContactEntry._ID
            + " INTEGER PRIMARY KEY NOT NULL, "
            + ContactContract.ContactEntry.COLUMN_NAME
            + " TEXT, "
            + ContactContract.ContactEntry.COLUMN_ADDRESS
            + " TEXT, "
            + ContactContract.ContactEntry.COLUMN_PHONE
            + " TEXT)";

    private final static String COMMAND_DELETE_CONTACT_TABLE =
            "DROP TABLE " + ContactContract.ContactEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(COMMAND_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // delete old db
        sqLiteDatabase.execSQL(COMMAND_DELETE_CONTACT_TABLE);
        // and create new db
        sqLiteDatabase.execSQL(COMMAND_CREATE_CONTACT_TABLE);
    }

}
