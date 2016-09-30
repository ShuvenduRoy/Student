package com.bikash.student;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by bikash on 9/28/16.
 */
public class Student extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
