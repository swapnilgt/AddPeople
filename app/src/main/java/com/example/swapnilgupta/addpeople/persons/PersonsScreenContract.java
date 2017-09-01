package com.example.swapnilgupta.addpeople.persons;

import com.example.swapnilgupta.addpeople.models.Person;

import java.util.List;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public interface PersonsScreenContract {
    interface View {
        void updateUserList(List<Person> persons);
    }

    interface UserActionListener {
        void loadUsers();
        void userClickNext();
        void startUserGenTask();
        void stopUserGenTask();
    }
}
