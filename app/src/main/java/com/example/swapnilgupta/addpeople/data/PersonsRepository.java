package com.example.swapnilgupta.addpeople.data;

import android.support.annotation.NonNull;

import com.example.swapnilgupta.addpeople.models.Person;

import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public interface PersonsRepository {

    interface LoadPersonsCallback {
        void onPersonsLoaded(List<Person> persons);
    }

    void loadPersonsList(@NonNull LoadPersonsCallback callback);

    interface AddPersonCallback {
        void onPersonAdded(boolean success);
    }

    void addPerson(@NonNull AddPersonCallback callback);
}
