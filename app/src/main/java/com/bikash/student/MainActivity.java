package com.bikash.student;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button button;
    private Button signupButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignup();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    private void startSignup(){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        HomeActivity.userEmail = email;
        HomeActivity.userPassword = password;

        if(password.length()<6){
            Toast.makeText(MainActivity.this, "Password have to be 6 character atleast", Toast.LENGTH_LONG).show();
            startSignup();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                startActivity(new Intent(MainActivity.this, LocalSignIn.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Sign in problem", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void startSignin(){
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            startActivity(new Intent(MainActivity.this, LocalSignIn.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Sign in problem", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("Restart" , "Called");

        if(mAuth.getCurrentUser()!=null){
            finish();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Resume" , "Called");

        if(mAuth.getCurrentUser()!=null){
            finish();
        }


    }
}

