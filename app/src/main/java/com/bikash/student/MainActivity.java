package com.bikash.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView routine = (TextView)findViewById(R.id.routine);
        routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Intent i = new Intent(MainActivity.this, Routine.class);
                    startActivity(i);
                }
            }
        );
    }
}
