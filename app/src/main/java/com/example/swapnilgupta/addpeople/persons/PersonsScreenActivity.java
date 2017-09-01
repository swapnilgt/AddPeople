package com.example.swapnilgupta.addpeople.persons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.swapnilgupta.addpeople.R;
import com.example.swapnilgupta.addpeople.api.PersonsServiceApiImpl;
import com.example.swapnilgupta.addpeople.data.InMemoryPersonsRepo;
import com.example.swapnilgupta.addpeople.models.Person;

import java.util.List;

public class PersonsScreenActivity extends AppCompatActivity implements PersonsScreenContract.View {

    TextView list;
    TextView next;

    private PersonsScreenContract.UserActionListener mActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_screen);

        list = (TextView) findViewById(R.id.tvList);
        next = (TextView) findViewById(R.id.tvNext);

        list.setMovementMethod(new ScrollingMovementMethod());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionListener.userClickNext();
            }
        });

        mActionListener = new PersonsScreenPresenter(
                new InMemoryPersonsRepo(new PersonsServiceApiImpl()), this);

        mActionListener.startUserGenTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("TagDebug", "inside onresume");
        mActionListener.loadUsers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActionListener.stopUserGenTask();
    }

    @Override
    public void updateUserList(List<Person> persons) {
        final StringBuilder b = new StringBuilder();
        for(Person p: persons) {
            b.append(p.getId() + "    ");
            b.append(p.getName() + "\n");
        }
        list.setText(b.toString());
    }
}
