package com.example.swapnilgupta.addpeople.sqlite;

import android.provider.BaseColumns;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class SQLContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SQLContract() {
    }

    public static class Person implements BaseColumns {
        static final String TABLE_NAME = "person";
        static final String COLUMN_NAME_NAME = "name";
    }
}
