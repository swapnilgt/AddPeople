package com.example.swapnilgupta.addpeople.persons;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.swapnilgupta.addpeople.data.PersonsRepository;
import com.example.swapnilgupta.addpeople.models.Person;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class PersonsScreenPresenter implements PersonsScreenContract.UserActionListener {

    private PersonsRepository mPersonsRepository;
    private WeakReference<PersonsScreenContract.View> mView;
    private boolean mUserGenTaskRunning = false;

    private static final String TAG = "PersonsScreenPresenter";

    public PersonsScreenPresenter(PersonsRepository mPersonsRepository,
                                  PersonsScreenContract.View mView) {
        this.mPersonsRepository = mPersonsRepository;
        this.mView = new WeakReference<>(mView);
    }

    @Override
    public void loadUsers() {
        Log.w("TagDebug", "loading list from the repo ..");
        mPersonsRepository.loadPersonsList(new PersonsRepository.LoadPersonsCallback() {
            @Override
            public void onPersonsLoaded(final List<Person> persons) {
                Log.w("TagDebug", "got list from the repo ..." + persons);
                if(mView.get() != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mView.get().updateUserList(persons);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void userClickNext() {
        mPersonsRepository.addPerson(new PersonsRepository.AddPersonCallback() {
            @Override
            public void onPersonAdded(boolean success) {
                if(mView.get() != null) {
                    if(success) {
                        loadUsers();
                    } else {
                        Log.d(TAG, "Error in adding a user ...");
                    }
                }
            }
        });
    }

    @Override
    public void startUserGenTask() {
        mUserGenTaskRunning = true;
        final Handler handler = new Handler();
        final int delay = 10000;

        Log.d(TAG, "Starting the user generation task");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPersonsRepository.addPerson(new PersonsRepository.AddPersonCallback() {
                    @Override
                    public void onPersonAdded(boolean success) {
                        if(mView.get() != null) {
                            if (success) {
                                loadUsers();
                            } else {
                                Log.d(TAG, "Error in adding a user ...");
                            }
                        }
                    }
                });
                if(mUserGenTaskRunning) {
                    handler.postDelayed(this, delay);
                } else {
                    Log.d(TAG, "The application is killed.. stopping the thread!");
                }
            }
        }, delay);

    }

    @Override
    public void stopUserGenTask() {
        Log.d(TAG, "Stopping the user generation task");
        mUserGenTaskRunning = false;
    }
}
