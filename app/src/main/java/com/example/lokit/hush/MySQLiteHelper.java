package com.example.lokit.hush;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lokit on 10-Jan-16.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SESSION = "session";
    public static final String COLUMN_ROUNDNO = "roundno";
    public static final String COLUMN_ARROWS = "arrows";
    public static final String COLUMN_ROUNDSUM = "roundsum";
    public static final String COLUMN_ROUNDAVE = "average";

    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_TYPE = "event_type";
    public static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String MUTE = "mute" ;
    private static final String VIBRATE = "vibrate" ;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String RADIUS = "radius";



    private static final String DATABASE_NAME = "records.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RECORDS + "(" + EVENT_NAME
            + " string primary key, " + EVENT_TYPE
            + " string, " + START_TIME + " string, " + END_TIME
            + " string, " + MUTE + " integer, " + VIBRATE
            + " integer, " + LATITUDE + " double, " + LONGITUDE + " double " + ")";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //database.execSQL("drop table records");
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }


    public void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, record.getename()); // Contact Name
        values.put(EVENT_TYPE, record.getetype()); // Contact Phone Number
        values.put(START_TIME, record.getstime()); // Contact Phone Number
        values.put(END_TIME, record.getetime());
        values.put(MUTE, record.getmute());
        values.put(VIBRATE, record.getvibr());
        values.put(LATITUDE, record.getLocation().get(0));
        values.put(LONGITUDE, record.getLocation().get(1));
        values.put(RADIUS, record.getLocation().get(2));

        // Inserting Row
        db.insert(TABLE_RECORDS, null, values);
    }

    public ArrayList<Record> getAllRecords() {
        ArrayList<Record> recordList = new ArrayList<Record>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setename(cursor.getString(1));
                record.setetype(cursor.getString(2));
                record.setstime(cursor.getString(3));
                record.setetime(cursor.getString(4));
                record.setmute(cursor.getInt(5));
                record.setvibr(cursor.getInt(6));
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(cursor.getDouble(7));
                temp.add(cursor.getDouble(8));
                temp.add(cursor.getDouble(9));
                record.setLocation(temp);

                recordList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();


        return recordList;
    }


    public int getLastSession() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_RECORDS;
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null) {
            cursor.moveToLast();
            return cursor.getInt(1);
        } else {
            return 0;
        }
    }
}
