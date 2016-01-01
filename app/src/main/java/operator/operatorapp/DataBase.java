package operator.operatorapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohammed on 11/10/2015.
 */
public class DataBase extends SQLiteOpenHelper {
    private static DataBase sInstance;
    private static final String DATABASE_NAME="items.db";
    private static final int DATABASE_VERSION=1;
    private static final String TEXT_TYPE = "TEXT";
    private static final String INTEGER_TYPE="INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME + "(" +
                    "_id"+" INTEGER primary key autoincrement,"+
                    FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_NAME + " TEXT," +
                    FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_WEIGHT+ " REAL,"+
                    FeederReaderContract.FeedEntry.ITEMS_COLUMN_X_CORD +" REAL," +
                    FeederReaderContract.FeedEntry.ITEMS_COLUMN_Y_CORD+" REAL,"+
                    FeederReaderContract.FeedEntry.ITEMS_COLUMN_Z_CORD+" REAL);";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME;


    private DataBase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("shit", "created table");
        Log.d("path",db.getPath().toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    public static synchronized DataBase getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBase(context.getApplicationContext());
        }
        return sInstance;
    }

}
