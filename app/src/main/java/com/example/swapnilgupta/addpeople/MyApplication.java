package com.example.swapnilgupta.addpeople;

import android.app.Application;
import android.util.Log;

import com.example.swapnilgupta.addpeople.sqlite.MySQLHelper;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class MyApplication extends Application {

    public static MySQLHelper mSQLHelper;

    public static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "The application instance is created.");

        // instantiating db helper ..
        mSQLHelper = new MySQLHelper(getApplicationContext());
    }

}
