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

import java.util.Calendar;

public class Events extends AppCompatActivity implements TextWatcher {
    EditText editText;
    int noteId;
    String time;

    Calendar calendar;
    TimePicker timePicker;
    TextView setDateTextView;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_events);


        editText = (EditText) findViewById(R.id.EventNameEditView);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        setDateTextView = (TextView) findViewById(R.id.setDateTextView);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        getSupportActionBar().setTitle("Create New Events");

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
        time = hour + ":" + min;
        Log.i("Time", time);

        String s = setDateTextView.getText() + " " + time + "\n";
        editText.setText(s + editText.getText());

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
        setDateTextView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        BasicEventsActivity.events.set(noteId, String.valueOf(charSequence));
        BasicEventsActivity.arrayAdapter.notifyDataSetChanged();

//        if(BasicEventsActivity.set == null){
//            BasicEventsActivity.set = new HashSet<String>();
//        } else {
//            BasicEventsActivity.set.clear();
//        }
//
//        SharedPreferences sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);
//
//        BasicEventsActivity.set.addAll(BasicEventsActivity.events);
//        sharedPreferences.edit().remove("events").apply();
//        sharedPreferences.edit().putStringSet("events", BasicEventsActivity.set).apply();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
