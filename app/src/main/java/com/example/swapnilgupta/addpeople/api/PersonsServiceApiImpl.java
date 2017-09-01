package com.example.swapnilgupta.addpeople.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.swapnilgupta.addpeople.models.Person;
import com.example.swapnilgupta.addpeople.sqlite.SQLDataUtils;

import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class PersonsServiceApiImpl implements PersonsServiceApi {

    @Override
    public void getPersonsList(@NonNull PersonsServiceCallback<List<Person>> callback) {
        Log.w("TagDebug", "loading list from the sql data utils ..");
        callback.onLoaded(SQLDataUtils.getPersonsList());
    }

    @Override
    public void addNewPerson(@NonNull PersonsServiceCallback<Boolean> callback) {
        callback.onSaved(SQLDataUtils.addNewPerson());
    }
}
