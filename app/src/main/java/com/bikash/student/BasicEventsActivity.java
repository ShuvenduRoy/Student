package com.bikash.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class BasicEventsActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> events;
    static ArrayAdapter arrayAdapter;
    //static Set<String> set;
    //SharedPreferences sharedPreferences;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_basic_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UpComing Events");




        mydatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
        events = new ArrayList<>();


        listView = (ListView) findViewById(R.id.listView);

        try{
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR)");

            Cursor c = mydatabase.rawQuery("SELECT * FROM events", null);

            int eventIndex = c.getColumnIndex("event");

            c.moveToFirst();
            while(c!=null){

                String evnetName = c.getString(eventIndex);
                c.moveToNext();

                events.add(evnetName);
                Log.i("Event", evnetName);
            }
        } catch (Exception e){

            e.printStackTrace();

        }

        Collections.sort(events);





//        //Storing Data
//        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);
//        set = sharedPreferences.getStringSet("events", null);
//
//        events.clear();
//
//        if(set!=null){
//            events.addAll(set);
//        } else {
//            set = new HashSet<String>();
//            set.addAll(events);
//            sharedPreferences.edit().putStringSet("events", set).apply();
//        }




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

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                new AlertDialog.Builder(BasicEventsActivity.this)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Are you sure?")
//                        .setMessage("Do you want to delete this note?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                events.remove(position);
//
//                                SharedPreferences sharedPreferences = BasicEventsActivity.this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);
//
//                                if (set == null) {
//
//                                    set = new HashSet<String>();
//
//                                } else {
//
//                                    set.clear();
//
//                                }
//
//                                set.addAll(events);
//                                sharedPreferences.edit().remove("notes").apply();
//                                sharedPreferences.edit().putStringSet("notes", set).apply();
//                                arrayAdapter.notifyDataSetChanged();
//
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//
//                return true;
//            }
//        });
//
//
//
//
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                events.add("");
//                sharedPreferences = BasicEventsActivity.this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);
//
//
//                if(set == null){
//                    set = new HashSet<String>();
//                } else {
//                    set.clear();
//                }
//
//                set.addAll(events);
//                arrayAdapter.notifyDataSetChanged();
//
//                sharedPreferences.edit().remove("events").apply();
//                sharedPreferences.edit().putStringSet("events", set).apply();


                Intent i = new Intent(getApplicationContext(), Events.class);
                i.putExtra("Id", events.size()-1);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onDestroy() {

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR)" );

        mydatabase.delete("events",null,null);


        for(int i=0; i<events.size(); i++){

            String e = events.get(i);
            String sql = "INSERT INTO events (event) VALUES ('"+e+"') ";
            mydatabase.execSQL(sql);

        }

        super.onDestroy();
    }
}
