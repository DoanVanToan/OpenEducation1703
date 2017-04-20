package toandoan.framgia.com.android_12_menu_searchview_dialog.data;

import android.provider.BaseColumns;

/**
 * Created by framgia on 17/04/2017.
 */

public class ContactContract {
    public class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_contact";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "ADDRESS";
    }
}
