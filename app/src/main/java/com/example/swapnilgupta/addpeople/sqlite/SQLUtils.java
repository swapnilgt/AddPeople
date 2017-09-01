package com.example.swapnilgupta.addpeople.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.swapnilgupta.addpeople.MyApplication;
import com.example.swapnilgupta.addpeople.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class SQLUtils {

    private static final String TAG = "SQLUtils";

    /**
     * Call this from a non-UI thread ...
     * @return
     */
    public static final List<Person> fetchPersonList() {
        // getting the handle of the database object ..
        final SQLiteDatabase db = MyApplication.mSQLHelper.getReadableDatabase();

        final Cursor c = db.query(
                SQLContract.Person.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(c != null) {

            Log.d(TAG, "The number of users read from the database are: " + c.getCount());
            final List<Person> retList = new ArrayList<>();

            final int _ID = c.getColumnIndex(SQLContract.Person._ID);
            final int COLUMN_NAME_NAME = c.getColumnIndex(SQLContract.Person.COLUMN_NAME_NAME);

            while(c.moveToNext()) {
                final Person p = new Person();
                p.setId(c.getInt(_ID));
                p.setName(c.getInt(COLUMN_NAME_NAME));
                retList.add(p);
            }

            c.close();
            return retList;

        } else {
            Log.d(TAG, "could not init the cursor ..");
            return null;
        }
    }

    /**
     * Call this from a non-UI thread ..
     * @param p
     * @return
     */
    public static boolean addNewPerson(Person p) {
        SQLiteDatabase db = MyApplication.mSQLHelper.getWritableDatabase();
        final ContentValues cv = new ContentValues();
        cv.put(SQLContract.Person.COLUMN_NAME_NAME, p.getName());
        db.beginTransaction();

        try {
            db.insertOrThrow(SQLContract.Person.TABLE_NAME, null, cv);
        } catch (SQLException e) {
            Log.d(TAG, "Insertion operation failed");
            return false;
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        return true;
    }
}
