package com.bikash.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class Events extends AppCompatActivity implements TextWatcher {
    EditText editText;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        editText = (EditText) findViewById(R.id.editText);

        getSupportActionBar().setTitle("Create New Events");

        Intent i = getIntent();
        noteId = i.getIntExtra("Id", -1);

        if(noteId!= -1){

            editText.setText(BasicEventsActivity.events.get(noteId).toString());

        }

        editText.addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        BasicEventsActivity.events.set(noteId, String.valueOf(charSequence));
        BasicEventsActivity.arrayAdapter.notifyDataSetChanged();

        if(BasicEventsActivity.set == null){
            BasicEventsActivity.set = new HashSet<String>();
        } else {
            BasicEventsActivity.set.clear();
        }

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);

        BasicEventsActivity.set.addAll(BasicEventsActivity.events);
        sharedPreferences.edit().remove("events").apply();
        sharedPreferences.edit().putStringSet("events", BasicEventsActivity.set).apply();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
