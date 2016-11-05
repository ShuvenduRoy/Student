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

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    static String  mUsername = "ANONYMOUS";
    private String mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    public static String userGroup = "ANONYMOUS";
    SharedPreferences sharedPreferences;
    public static String userEmail="ANONYMOUS";
    public static String userPassword="ANONYMOUS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);

        try{

            userGroup = sharedPreferences.getString("userGroup", "ANONYMOUS");
            Log.i("USERDATA4",userGroup);
            userEmail = sharedPreferences.getString("userEmail","ANONYMOUS");
            Log.i("USERDATA4",userEmail);
            mUsername = sharedPreferences.getString("userName","ANONYMOUS");
            Log.i("USERDATA4",mUsername);


                Firebase firebaseName = new Firebase("https://student-eaf3d.firebaseio.com/users");
                firebaseName.child(HomeActivity.userGroup).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                       try{
                           User u = dataSnapshot.getValue(User.class);
                           Log.i("USERDATA1",u.getEmail());
                           Log.i("USERDATA2",u.getUsername());
                           Log.i("USERDATA3", userEmail);
                           if(u.getEmail().equals(userEmail)){
                               mUsername = u.getUsername();
                               sharedPreferences.edit().putString("userName", mUsername).apply();
                           }
                       } catch (Exception e){
                           e.printStackTrace();
                       }
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


        } catch (Exception e){
            e.printStackTrace();
        }

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, MainActivity.class));
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




        //Routine activity
        final TextView routine = (TextView)findViewById(R.id.routine);
        routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    try{
                        Intent i = new Intent(HomeActivity.this, Routine.class);
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );


        final TextView presentance = (TextView)findViewById(R.id.presentance);
        presentance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try{
                    Intent i = new Intent(HomeActivity.this, Presence.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );


        final TextView upComingEvent = (TextView) findViewById(R.id.upComingEvent);
        upComingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(HomeActivity.this, BasicEventsActivity.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        final TextView groupChat = (TextView) findViewById(R.id.groupChatTextview);
//        groupChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    Intent i = new Intent(HomeActivity.this, GroupChat.class);
//                    startActivity(i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        final TextView file = (TextView) findViewById(R.id.file_layout);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(HomeActivity.this, File.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final TextView developer = (TextView) findViewById(R.id.developer);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(HomeActivity.this, Developer.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        Intent intent = getIntent();
        finish();
        startActivity(intent);


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
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            case R.id.log_menu:
                startActivity(new Intent(HomeActivity.this, LogMenu.class));
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
