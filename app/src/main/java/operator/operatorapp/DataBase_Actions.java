package operator.operatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mohammed on 11/11/2015.
 */
public class DataBase_Actions {
    private static DataBase dBInstance=null;
    public static void init(Context context)
    {
        if(dBInstance==null)
        {
            dBInstance=DataBase.getInstance(context);
        }
    }
    public static boolean insertItem  (String item_name,Integer x_cord,Integer y_cord,Integer z_cord,Integer weight)
    {
        SQLiteDatabase db = dBInstance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_NAME, item_name);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_WEIGHT, weight);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_X_CORD, x_cord);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Y_CORD, y_cord);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Z_CORD, z_cord);
        db.insert(FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME, null, contentValues);
        return true;

    }

    public static Cursor getData(String name){
        SQLiteDatabase db = dBInstance.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from item where name="+name+"", null );
        return res;
    }

    public static int numberOfRows(){
        SQLiteDatabase db = dBInstance.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME);
        return numRows;
    }

    public static boolean updateItem (String name,Integer x_cord,Integer y_cord,Integer z_cord,Integer weight)
    {
        SQLiteDatabase db = dBInstance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_NAME, name);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_X_CORD, x_cord);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Y_CORD, y_cord);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Z_CORD, z_cord);
        contentValues.put(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_WEIGHT, weight);
        db.update("items", contentValues, "name = ? ", new String[] { name} );
        return true;
    }

    public static Integer deleteContact (String name)
    {
        SQLiteDatabase db = dBInstance.getWritableDatabase();
        return db.delete("items",
                "name = ? ",
                new String[] { name});
    }

    public static ArrayList<String> getAllitems()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();

        SQLiteDatabase db = dBInstance.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME, null );
        res.moveToFirst();

        if (res .moveToFirst()) {

            while (res.isAfterLast() == false) {
                String name = res.getString(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_NAME));

                array_list.add(name);
                res.moveToNext();
            }
        }
        db.close();
        return array_list;
    }
    public static ArrayList<Item> getAllitemsObject()
    {
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = dBInstance.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+FeederReaderContract.FeedEntry.ITEMS_TABLE_NAME, null );
        res.moveToFirst();

        if (res .moveToFirst()) {

            while (res.isAfterLast() == false) {
                String name = res.getString(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_NAME));
                double weight =res.getDouble(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_ITEM_WEIGHT));
                double x_cord = res.getDouble(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_X_CORD));
                double y_cord = res.getDouble(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Y_CORD));
                double z_cord = res.getDouble(res
                        .getColumnIndex(FeederReaderContract.FeedEntry.ITEMS_COLUMN_Z_CORD));
                array_list.add(new Item(new Cords(x_cord,y_cord,z_cord),name,weight));

                res.moveToNext();
            }
        }
        db.close();
        return array_list;
    }
}
