package com.bikash.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class ChangeLink extends AppCompatActivity {
    EditText drive;
    EditText fb;
    Button save;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_link);

        drive = (EditText) findViewById(R.id.driveLink);
        fb = (EditText) findViewById(R.id.facebookLink);
        save = (Button) findViewById(R.id.saveLinkButton);



        firebase = new Firebase("https://student-eaf3d.firebaseio.com/");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fbLink = fb.getText().toString();
                final String driveLink = drive.getText().toString();

                if(fbLink.length()>1){
                    firebase.child("links").child(HomeActivity.userGroup).child("fb").setValue(fbLink);
                }

                if(driveLink.length()>1){
                    firebase.child("links").child(HomeActivity.userGroup).child("drive").setValue(driveLink);
                }

                finish();
            }
        });

    }
}
