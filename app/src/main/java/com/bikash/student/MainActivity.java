package com.bikash.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername = "ANONYMOUS";
    private String mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    public static String userGroup = "";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);

        try{

            userGroup = sharedPreferences.getString("userGroup", "");

        } catch (Exception e){
            e.printStackTrace();
        }

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        /**
         * Local Authorization Check
         */

        if(userGroup=="")
        {
            Intent i = new Intent(MainActivity.this, LocalSignIn.class);
            startActivity(i);

        } else {

            Toast.makeText(getBaseContext(), "Your are logged into group\n         " + userGroup, Toast.LENGTH_LONG).show();

        }


        //Routine activity
        final TextView routine = (TextView)findViewById(R.id.routine);
        routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Intent i = new Intent(MainActivity.this, Routine.class);
                    startActivity(i);
                    finish();
                    return;
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

        final TextView developer = (TextView) findViewById(R.id.developer);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Developer.class);
                startActivity(i);
            }
        });

        final LinearLayout developer_fb = (LinearLayout) findViewById(R.id.developer_fb);
        developer_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/shuvendu.roy.758";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }



    @Override
    protected void onRestart() {
        super.onRestart();

        if(userGroup=="")
        {
            finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = "ANONYMOUS";
                startActivity(new Intent(this, SignInActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Error", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
