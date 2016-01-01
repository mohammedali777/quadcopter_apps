package operator.operatorapp;

import android.provider.BaseColumns;

/**
 * Created by mohammed on 11/11/2015.
 */
public final class FeederReaderContract {
    public FeederReaderContract(){}
    public static abstract  class FeedEntry implements BaseColumns
    {
       // public static final String DATABASE_NAME = "MyDBName.db";
        public static final String ITEMS_TABLE_NAME = "items";
        public static final String ITEMS_COLUMN_ITEM_NAME = "items_name";
        public static final String ITEMS_COLUMN_ITEM_WEIGHT = "weight";
        public static final String ITEMS_COLUMN_X_CORD = "x_cord";
        public static final String ITEMS_COLUMN_Y_CORD = "y_cord";
        public static final String ITEMS_COLUMN_Z_CORD = "z_cord";


    }
}
