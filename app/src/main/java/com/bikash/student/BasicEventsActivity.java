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
    //static Set<String> set;
    //SharedPreferences sharedPreferences;
    SQLiteDatabase mydatabase;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_basic_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UpComing Events");

        Toast.makeText(this,"Syncing events...",Toast.LENGTH_LONG).show();




        mydatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
        events = new ArrayList<>();


        listView = (ListView) findViewById(R.id.listView);


        /**
         * Fire Base data base added
         * onchildAdded listener will keep arraylist updated
         */

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabaseReference.child("events").child(MainActivity.userGroup).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Event e = dataSnapshot.getValue(Event.class);

                String eventadded = new String(e.getDate() + " " + e.getTime() + "\n" + e.getEvent());

                events.add(eventadded);
                Collections.sort(events);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        /**
         * This try block loads all data from local data base
         * but if we start using firebase data base adding this data will duplicate our data
         */

//        try{
//            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR)");
//
//            Cursor c = mydatabase.rawQuery("SELECT * FROM events", null);
//
//            int eventIndex = c.getColumnIndex("event");
//
//            c.moveToFirst();
//            while(c!=null){
//
//                String evnetName = c.getString(eventIndex);
//                c.moveToNext();
//
//                events.add(evnetName);
//                Log.i("Event", evnetName);
//            }
//        } catch (Exception e){
//
//            e.printStackTrace();
//
//        }

        Collections.sort(events);








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

                /*
                 IT was required in local data base system to add a new and send its id to intend
                 but not requird in server bases system
                 */
                //events.add("");



                Intent i = new Intent(getApplicationContext(), Events.class);
                //i.putExtra("Id", events.size()-1);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onDestroy() {

        /**
         *
         * Nothing of this is required after adding server
         */

//        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR)" );
//
//        mydatabase.delete("events",null,null);
//
//
//
//        for(int i=0; i<events.size(); i++){
//
//            String e = events.get(i);
//            String sql = "INSERT INTO events (event) VALUES ('"+e+"') ";
//            mydatabase.execSQL(sql);
//
//        }

        super.onDestroy();
    }
}
