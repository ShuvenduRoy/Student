package com.bikash.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class BasicEventsActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> events;
    static ArrayAdapter arrayAdapter;
    private Firebase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Primary setup
         */
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toast.makeText(this,"Syncing events...",Toast.LENGTH_LONG).show();

        /**
         * Wiring things up
         * getting all elemnets
         */
        setContentView(R.layout.activity_basic_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UpComing Events");
        listView = (ListView) findViewById(R.id.listView);


        /**
         * Initializing variable and data base connection
         */
        firebaseDatabase = new Firebase("https://student-eaf3d.firebaseio.com/events/"+HomeActivity.userGroup);
        events = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);
        listView.setAdapter(arrayAdapter);
        firebaseDatabase.keepSynced(true);



        /**
         * Fire Base data base added
         * onchildAdded listener will keep arraylist updated
         */




        firebaseDatabase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                Event e = dataSnapshot.getValue(Event.class);

                String eventadded = new String(e.getDate() + " " + e.getTime() + "\n" + e.getEvent());

                events.add(eventadded);
                Collections.sort(events);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



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
                Intent i = new Intent(getApplicationContext(), Events.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
