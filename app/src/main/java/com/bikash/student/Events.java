package com.bikash.student;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Events extends AppCompatActivity implements TextWatcher {
    EditText editText;
    int noteId;
    String time;

    Calendar calendar;
    TimePicker timePicker;
    TextView setDateTextView;
    int year, month, day;


    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle("Create New Events");

        setContentView(R.layout.activity_events);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();



        editText = (EditText) findViewById(R.id.EventNameEditView);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        setDateTextView = (TextView) findViewById(R.id.setDateTextView);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);



        Intent i = getIntent();
        noteId = i.getIntExtra("Id", -1);

        if(noteId!= -1){

            editText.setText(BasicEventsActivity.events.get(noteId).toString());

        }

        editText.addTextChangedListener(this);



    }


    public void saveTime(View view){
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();

        String sunTime;
        if(hour>=12){
            sunTime="PM";
        } else {
            sunTime="AM";
        }

        hour = hour%12;

        if(hour==0){
            hour = 12;
        }

        time = hour + ":" + min+" "+sunTime;
        Log.i("Time", time);

        Event e = new Event(setDateTextView.getText().toString(), time, editText.getText().toString());

        String s = setDateTextView.getText() + " " + time + "\n";
        s += editText.getText();
        editText.setText(s);



        mFirebaseDatabaseReference.child("events").child(MainActivity.userGroup).push().setValue(e);

        finish();
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        setDateTextView.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        BasicEventsActivity.events.set(noteId, String.valueOf(charSequence));
        BasicEventsActivity.arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
