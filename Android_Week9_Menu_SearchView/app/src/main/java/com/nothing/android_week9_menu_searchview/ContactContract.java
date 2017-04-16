package com.nothing.android_week9_menu_searchview;

import android.provider.BaseColumns;

/**
 * Created by THM on 4/17/2017.
 */
public class ContactContract {
    public class ContractEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_contact";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
    }
}
