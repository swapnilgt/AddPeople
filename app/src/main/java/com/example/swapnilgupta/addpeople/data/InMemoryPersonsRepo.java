package com.example.swapnilgupta.addpeople.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.swapnilgupta.addpeople.api.PersonsServiceApi;
import com.example.swapnilgupta.addpeople.models.Person;

import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class InMemoryPersonsRepo implements PersonsRepository {

    private PersonsServiceApi mPersonsServiceApi;

    public InMemoryPersonsRepo(PersonsServiceApi mPersonsServiceApi) {
        this.mPersonsServiceApi = mPersonsServiceApi;
    }

    @Override
    public void loadPersonsList(@NonNull final LoadPersonsCallback callback) {
        Log.w("TagDebug", "loading list from the api ..");
        mPersonsServiceApi.getPersonsList
                (new PersonsServiceApi.PersonsServiceCallback<List<Person>>() {
            @Override
            public void onLoaded(List<Person> elem) {
                callback.onPersonsLoaded(elem);
            }

            @Override
            public void onSaved(List<Person> elem) {

            }
        });
    }

    @Override
    public void addPerson(@NonNull final AddPersonCallback callback) {
        mPersonsServiceApi.addNewPerson(new PersonsServiceApi.PersonsServiceCallback<Boolean>() {
            @Override
            public void onLoaded(Boolean elem) {

            }

            @Override
            public void onSaved(Boolean elem) {
                callback.onPersonAdded(elem);
            }
        });
    }
}
