package com.bikash.student;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Scanner;

import static java.util.Calendar.*;

public class Presence extends AppCompatActivity {
    Scanner sc;
    int n = 5;

    // Static is required to access it from other class in this case from EditPresence class

    static public Subject[] s = new Subject[5];
    static public TextView[] sub_name = new TextView[5];
    static public TextView[] sub_percentage = new TextView[5];
    static public TextView[] sub_count = new TextView[5];

    EditText EditClassName;
    TextView currentClassView;
    LinearLayout editLayout;

    /*
    we need another activity to edit the presence count
    and EditPresent function will start a new activity with the integer i the index of subject
     */

    public void EditPresent(View view, int i){

        Intent intent = new Intent(Presence.this, EditPresence.class);
        intent.putExtra("id", i);
        startActivity(intent);
    }

    public void EditView0(View view){
        EditPresent(view,0);
    }
    public void EditView1(View view){
        EditPresent(view,1);
    }
    public void EditView2(View view){
        EditPresent(view,2);
    }
    public void EditView3(View view){
        EditPresent(view,3);
    }
    public void EditView4(View view){
        EditPresent(view,4);
    }


    public void Reset(View view){

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are You Sure")
                .setMessage("This will delete all Presence data")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(i=0; i<n; i++){
                            s[i].total_class=0;
                            s[i].present_classs=0;

                            sub_count[i].setText("0/0");
                            sub_percentage[i].setText("0%");
                            sub_percentage[i].setBackgroundColor(Color.parseColor("#FD8E09"));
                        }
                    }
                })
                .setNegativeButton("No" , null).show();

    }

    //Activity of cancle Button
    public void cancleClassChange(View view){
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName.setText("");
    }

    //Activity of Save Button
    //Activity of Save Button
    public void saveClass(View view){
        EditClassName = (EditText) findViewById(R.id.changeClassName);
        String className = EditClassName.getText().toString() + " ";

        sc = new Scanner(className);
        String finalName = sc.nextLine();

        currentClassView.setText(finalName);
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName.setText("");
    }

    public void changeClass(View view){
        System.out.print("Clicked");

        editLayout.setVisibility(View.VISIBLE);
        currentClassView = (TextView) view;
    }

    //Adding functionality for the button "+" id = Present
    public void PresentButton(View view, int i){
        s[i].total_class++;
        s[i].present_classs++;

        int per = (s[i].present_classs*100)/ s[i].total_class;
        sub_percentage[i].setText(Integer.toString(per) + "%");
        sub_count[i].setText(s[i].present_classs+"/"+ s[i].total_class);


        if(per<60){
            sub_percentage[i].setBackgroundColor(Color.parseColor("#ff0000"));
        } else {
            sub_percentage[i].setBackgroundColor(Color.parseColor("#379237"));
        }


    }

    public void presentButton0(View view){
        PresentButton(view,0);
    }
    public void presentButton1(View view){
        PresentButton(view,1);
    }
    public void presentButton2(View view){
        PresentButton(view,2);
    }
    public void presentButton3(View view){
        PresentButton(view,3);
    }
    public void presentButton4(View view){
        PresentButton(view,4);
    }


    //Adding functionality for the button "-" id = Basent
    public void AbsentButton(View view, int i){
        s[i].total_class++;

        int per = (s[i].present_classs*100)/ s[i].total_class;
        sub_percentage[i].setText(Integer.toString(per) + "%");
        sub_count[i].setText(s[i].present_classs+"/"+ s[i].total_class);


        if(per<60){
            sub_percentage[i].setBackgroundColor(Color.parseColor("#ff0000"));
        } else {
            sub_percentage[i].setBackgroundColor(Color.parseColor("#379237"));
        }


    }


    public void absentButton0(View view){
        AbsentButton(view, 0);
    }
    public void absentButton1(View view){
        AbsentButton(view, 1);
    }
    public void absentButton2(View view){
        AbsentButton(view, 2);
    }
    public void absentButton3(View view){
        AbsentButton(view, 3);
    }
    public void absentButton4(View view){
        AbsentButton(view, 4);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_presentance);


        editLayout = (LinearLayout) findViewById(R.id.editClassLayout);
        getSupportActionBar().setTitle("Presence");

        //Making a teacher Object
        s[0] = new Subject("Advance Algorithm",0,0);
        s[1] = new Subject("Microcontroller",0,0);
        s[2] = new Subject("Numerical Method",0,0);
        s[3] = new Subject("Math",0,0);
        s[4] = new Subject("Hum",0,0);

        try {
            FileInputStream fis = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            char[] data = new char[50];
            String final_data = " ";
            int size;
            try{
                while((size=isr.read(data))>0){
                    String read_data = String.copyValueOf(data,0,size);
                    final_data+=read_data;
                    data = new char[50];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scanner sc = new Scanner(final_data);
            for(int i=0; i<n; i++){
                s[i].total_class = sc.nextInt();
                s[i].present_classs = sc.nextInt();
            }

        } catch (FileNotFoundException e) {

            Calendar calendar = getInstance();
            calendar.set(HOUR_OF_DAY,1);
            calendar.set(MINUTE, 10);
            calendar.set(SECOND, 00);
            calendar.set(Calendar.AM_PM, Calendar.PM);


            Intent notifyIntent = new Intent(this,MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 3, notifyIntent, 0);
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.i("time ",Long.toString(System.currentTimeMillis()));
            Log.i("time ", Long.toString(calendar.getTimeInMillis()));


            Toast.makeText(getBaseContext(), "Click on the name to change it", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }




        //Getting textview for all ids bt its id
        sub_name[0] = (TextView)findViewById(R.id.t0);  //Name of Teachar 1 or Subject 1
        sub_percentage[0] = (TextView)findViewById(R.id.t0p); //Subject 1 Percentage count for Subject 1
        sub_count[0] = (TextView)findViewById(R.id.count_0); //View for 0/0 like presentation

        sub_name[1] = (TextView)findViewById(R.id.t1);
        sub_percentage[1] = (TextView)findViewById(R.id.t1p);
        sub_count[1] = (TextView)findViewById(R.id.count_1);

        sub_name[2] = (TextView)findViewById(R.id.t2);
        sub_percentage[2] = (TextView)findViewById(R.id.t2p);
        sub_count[2] = (TextView)findViewById(R.id.count_2);

        sub_name[3] = (TextView)findViewById(R.id.t3);
        sub_percentage[3] = (TextView)findViewById(R.id.t3p);
        sub_count[3] = (TextView)findViewById(R.id.count_3);

        sub_name[4] = (TextView)findViewById(R.id.t4);
        sub_percentage[4] = (TextView)findViewById(R.id.t4p);
        sub_count[4] = (TextView)findViewById(R.id.count_4);




        //Working with Subject
        for(int i=0; i<n; i++){
            sub_name[i].setText(s[i].name);
            if(s[i].total_class == 0){
                sub_percentage[i].setText("0%");
                sub_percentage[i].setBackgroundColor(Color.parseColor("#FD8E09"));
            } else {
                int per = (s[i].present_classs*100)/ s[i].total_class;
                sub_percentage[i].setText(Integer.toString(per) + "%");
                sub_count[i].setText(s[i].present_classs+"/"+ s[i].total_class);


                if(per<60){
                    sub_percentage[i].setBackgroundColor(Color.parseColor("#ff0000"));
                } else {
                    sub_percentage[i].setBackgroundColor(Color.parseColor("#379237"));
                }
            }
        }



        try {
            FileInputStream f = openFileInput("sub.txt");
            InputStreamReader is = new InputStreamReader(f);

            char[] data = new char[50];
            String final_data = "";
            int size;

            try{
                while((size=is.read(data))>0){
                    String read_data = String.copyValueOf(data,0,size);
                    final_data+=read_data;
                    data = new char[50];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scanner scn = new Scanner(final_data);

            for(int i=0; i<n; i++){
                String str = scn.nextLine();
                sub_name[i].setText(str);
            }



        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    } // End of onCreate

    @Override
    protected void onResume() {

        super.onResume();

        //Working with Subject
        for(int i=0; i<n; i++){
            sub_name[i].setText(s[i].name);
            if(s[i].total_class == 0){
                sub_percentage[i].setText("0%");
                sub_percentage[i].setBackgroundColor(Color.parseColor("#FD8E09"));
            } else {
                int per = (s[i].present_classs*100)/ s[i].total_class;
                sub_percentage[i].setText(Integer.toString(per) + "%");
                sub_count[i].setText(s[i].present_classs+"/"+ s[i].total_class);


                if(per<60){
                    sub_percentage[i].setBackgroundColor(Color.parseColor("#ff0000"));
                } else {
                    sub_percentage[i].setBackgroundColor(Color.parseColor("#379237"));
                }
            }
        }
    }

    @Override
    protected void onDestroy(){


        try {
            FileOutputStream f = openFileOutput("sub.txt",MODE_WORLD_READABLE);
            OutputStreamWriter w = new OutputStreamWriter(f);
            try {
                for(int i=0; i<n; i++){
                    String s = sub_name[i].getText().toString() + "\n";
                    w.write(s);
                }
                w.flush();
                w.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            FileOutputStream file = openFileOutput("data.txt",MODE_WORLD_READABLE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
            try {
                for(int j=0; j<n; j++){
                    writer.write(s[j].total_class + " ");
                    writer.write(s[j].present_classs + " ");
                }
                writer.flush();
                writer.close();
                // Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }
}