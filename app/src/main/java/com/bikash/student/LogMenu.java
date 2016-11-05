package com.bikash.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Stack;

public class LogMenu extends AppCompatActivity {
    Firebase firebase;
    Stack<String> logs;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_menu);

        firebase = new Firebase("https://student-eaf3d.firebaseio.com/log");
        logs = new Stack<>();
        listView = (ListView) findViewById(R.id.logList);
        final ArrayAdapter<String> logAdapter = new ArrayAdapter<String>(LogMenu.this,android.R.layout.simple_list_item_1,logs);
        listView.setAdapter(logAdapter);


        firebase.child(HomeActivity.userGroup).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String log = (String) dataSnapshot.getValue();
                Log.i("GROUP", log);
                logs.add(log);
                logAdapter.notifyDataSetChanged();
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
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
