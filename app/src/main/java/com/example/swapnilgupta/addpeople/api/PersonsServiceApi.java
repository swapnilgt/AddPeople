package com.example.swapnilgupta.addpeople.api;

import android.support.annotation.NonNull;

import com.example.swapnilgupta.addpeople.models.Person;

import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public interface PersonsServiceApi {

    interface PersonsServiceCallback<T> {
        void onLoaded(T elem);
        void onSaved(T elem);
    }

    void getPersonsList(@NonNull PersonsServiceCallback<List<Person>> callback);

    void addNewPerson(@NonNull PersonsServiceCallback<Boolean> callback);

}
