package com.example.swapnilgupta.addpeople.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class MySQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "MySQLHelper";

    // If you change the database schema, you must increment the database version.
    private static final int DB_VERSION = 1;

    // DB Name, same is used to name the sqlite DB file
    private static final String DB_NAME = "test.db";

    private static final String SQL_CREATE_PERSONS = "CREATE TABLE " + SQLContract.Person.TABLE_NAME + "(" +
            SQLContract.Person._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            SQLContract.Person.COLUMN_NAME_NAME + " INTEGER NOT NULL)";

    public MySQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating the table ..");
        db.execSQL(SQL_CREATE_PERSONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO - Wrtie update and create statements for the new version of the db here ..
    }
}
