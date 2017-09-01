package com.example.swapnilgupta.addpeople.sqlite;

import android.util.Log;

import com.example.swapnilgupta.addpeople.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class SQLDataUtils {

    private static List<Person> PERSONS_LIST;
    private static final Random rn = new Random();
    private static final int MIN = 10;
    private static final int MAX = 40;


    public static boolean addNewPerson() {

        if(PERSONS_LIST == null) {
            PERSONS_LIST = SQLUtils.fetchPersonList();
        }

        final Person p = new Person();
        if(PERSONS_LIST.size() == 0) {
            p.setId(0);
        } else {
            p.setId(PERSONS_LIST.get(PERSONS_LIST.size() - 1).getId() + 1);
        }

        int n = MAX - MIN + 1;
        int i = rn.nextInt() % n;
        int newName =  MIN + i;
        p.setName(newName);

        PERSONS_LIST.add(p);

        // call to add this to the SQL ..
        return SQLUtils.addNewPerson(p);
    }

    public static List<Person> getPersonsList() {
        if(PERSONS_LIST == null) {
            Log.w("TagDebug", "persons list is null ..");
            return SQLUtils.fetchPersonList();
        }

        Log.w("TagDebug", "pserson list size .." + PERSONS_LIST.size());
        return PERSONS_LIST;
    }
}
