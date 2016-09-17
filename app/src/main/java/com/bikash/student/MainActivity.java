package com.bikash.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Routine activity
        final TextView routine = (TextView)findViewById(R.id.routine);
        routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Intent i = new Intent(MainActivity.this, Routine.class);
                    startActivity(i);
                }
            }
        );


        final TextView presentance = (TextView)findViewById(R.id.presentance);
        presentance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, Presence.class);
                startActivity(i);
            }
        }
        );


        final TextView upComingEvent = (TextView) findViewById(R.id.upComingEvent);
        upComingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BasicEventsActivity.class);
                startActivity(i);
            }
        });


        final TextView file = (TextView) findViewById(R.id.file_layout);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, File.class);
                startActivity(i);
            }
        });

    }


}
