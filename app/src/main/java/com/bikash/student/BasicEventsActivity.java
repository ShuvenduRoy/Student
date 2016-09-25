package com.bikash.student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BasicEventsActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> events = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_basic_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setTitle("UpComing Events");

        //listView = (ListView) findViewById(R.id.listView);

        String bikash;




        //Storing Data
        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);
        set = sharedPreferences.getStringSet("events", null);

        events.clear();

        if(set!=null){
            events.addAll(set);
        } else {
            set = new HashSet<String>();
            set.addAll(events);
            sharedPreferences.edit().putStringSet("events", set).apply();
        }




        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);
        listView.setAdapter(arrayAdapter);


        /**
         * Create an action when any of the item will be clicked
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Events.class);
                i.putExtra("Id", position);
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(BasicEventsActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                events.remove(position);

                                SharedPreferences sharedPreferences = BasicEventsActivity.this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);

                                if (set == null) {

                                    set = new HashSet<String>();

                                } else {

                                    set.clear();

                                }

                                set.addAll(events);
                                sharedPreferences.edit().remove("notes").apply();
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                                arrayAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                events.add("");
                sharedPreferences = BasicEventsActivity.this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);


                if(set == null){
                    set = new HashSet<String>();
                } else {
                    set.clear();
                }

                set.addAll(events);
                arrayAdapter.notifyDataSetChanged();

                sharedPreferences.edit().remove("events").apply();
                sharedPreferences.edit().putStringSet("events", set).apply();


                Intent i = new Intent(getApplicationContext(), Events.class);
                i.putExtra("Id", events.size()-1);
                startActivity(i);

            }
        });

    }

}
