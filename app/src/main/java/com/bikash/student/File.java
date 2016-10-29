package com.bikash.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class File extends AppCompatActivity {

    Button button;
    SharedPreferences sharedPreferences;
    Firebase firebase;

    String fb = "https://www.facebook.com";
    String drive =  "https://drive.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_file);

        firebase = new Firebase("https://student-eaf3d.firebaseio.com/");

        try{
            fb = sharedPreferences.getString("fb", "https://www.facebook.com");
            drive = sharedPreferences.getString("drive", "https://drive.google.com");
        } catch (Exception e){
            e.printStackTrace();
        }

        Firebase childRef = firebase.child("links").child(HomeActivity.userGroup);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                fb = map.get("fb");
                drive = map.get("drive");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        getSupportActionBar().setTitle("File");
        button = (Button) findViewById(R.id.chnageLinkButton);
        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);

        //Webview
        final TextView fbT = (TextView) findViewById(R.id.fackBookOpener);
        fbT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(fb));
                startActivity(i);
            }
        });


        final TextView driveT = (TextView) findViewById(R.id.googleDriveOpener);
        driveT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(drive));
                startActivity(i);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(File.this, ChangeLink.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        sharedPreferences.edit().putString("fb", fb).apply();
        sharedPreferences.edit().putString("drive", drive).apply();
        super.onDestroy();
    }
}
