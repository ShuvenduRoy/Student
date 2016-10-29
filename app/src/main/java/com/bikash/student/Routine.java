package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Routine extends AppCompatActivity {
    Scanner sc;

    EditText EditClassName;
    TextView currentClassView;
    LinearLayout editLayout;
    String[][] period = new String[6][8];
    TextView[][] periodView = new TextView[6][8];

    Firebase firebase;



    public void changeClass(View view){
        editLayout.setVisibility(View.VISIBLE);
        currentClassView = (TextView) view;
    }

    //Activity of cancle Button
    public void cancleClassChange(View view){
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName = (EditText) findViewById(R.id.changeClassName);
        EditClassName.setText("");
    }

    //Activity of Save Button
    public void saveClass(View view){
        String className = " ";
        String finalName = " ";
        EditClassName = (EditText) findViewById(R.id.changeClassName);


        if(EditClassName.getText() !=null){
            className = EditClassName.getText().toString() + " ";

            sc = new Scanner(className);
            finalName = sc.nextLine();
        } else {
            finalName= " ";
        }


        int viewId = currentClassView.getId();
        TextView t = (TextView) findViewById(viewId);
        Log.i("IDNAME",Integer.toString(viewId));
        t.setText(finalName);
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName.setText("");

        Period period = new Period(String.valueOf(viewId), finalName);
        firebase.child("routine").child(HomeActivity.userGroup).child(String.valueOf(viewId)).setValue(period);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_routine);
        getSupportActionBar().setTitle("Class Routine");

        /**
         * Connecting to database
         */
        firebase = new Firebase("https://student-eaf3d.firebaseio.com/");

        editLayout = (LinearLayout) findViewById(R.id.editClassLayout);

        periodView[1][1] = (TextView) findViewById(R.id.day1_1);
        periodView[1][2] = (TextView) findViewById(R.id.day1_2);
        periodView[1][3] = (TextView) findViewById(R.id.day1_3);
        periodView[1][4] = (TextView) findViewById(R.id.day1_4);
        periodView[1][5] = (TextView) findViewById(R.id.day1_5);
        periodView[1][6] = (TextView) findViewById(R.id.day1_6);
        periodView[1][7] = (TextView) findViewById(R.id.day1_7);

        periodView[2][1] = (TextView) findViewById(R.id.day2_1);
        periodView[2][2] = (TextView) findViewById(R.id.day2_2);
        periodView[2][3] = (TextView) findViewById(R.id.day2_3);
        periodView[2][4] = (TextView) findViewById(R.id.day2_4);
        periodView[2][5] = (TextView) findViewById(R.id.day2_5);
        periodView[2][6] = (TextView) findViewById(R.id.day2_6);
        periodView[2][7] = (TextView) findViewById(R.id.day2_7);

        periodView[3][1] = (TextView) findViewById(R.id.day3_1);
        periodView[3][2] = (TextView) findViewById(R.id.day3_2);
        periodView[3][3] = (TextView) findViewById(R.id.day3_3);
        periodView[3][4] = (TextView) findViewById(R.id.day3_4);
        periodView[3][5] = (TextView) findViewById(R.id.day3_5);
        periodView[3][6] = (TextView) findViewById(R.id.day3_6);
        periodView[3][7] = (TextView) findViewById(R.id.day3_7);

        periodView[4][1] = (TextView) findViewById(R.id.day4_1);
        periodView[4][2] = (TextView) findViewById(R.id.day4_2);
        periodView[4][3] = (TextView) findViewById(R.id.day4_3);
        periodView[4][4] = (TextView) findViewById(R.id.day4_4);
        periodView[4][5] = (TextView) findViewById(R.id.day4_5);
        periodView[4][6] = (TextView) findViewById(R.id.day4_6);
        periodView[4][7] = (TextView) findViewById(R.id.day4_7);

        periodView[5][1] = (TextView) findViewById(R.id.day5_1);
        periodView[5][2] = (TextView) findViewById(R.id.day5_2);
        periodView[5][3] = (TextView) findViewById(R.id.day5_3);
        periodView[5][4] = (TextView) findViewById(R.id.day5_4);
        periodView[5][5] = (TextView) findViewById(R.id.day5_5);
        periodView[5][6] = (TextView) findViewById(R.id.day5_6);
        periodView[5][7] = (TextView) findViewById(R.id.day5_7);

        for(int i=1; i<6; i++){
            for(int j=1; j<8; j++){
                periodView[i][j].setTextSize(13);
            }
        }

        Firebase childRef = firebase.child("routine").child(HomeActivity.userGroup);
        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Period period = dataSnapshot.getValue(Period.class);
                Log.i("data1",period.getPeriod());
                Log.i("data2",period.getSubject());

                int id = Integer.valueOf(period.getPeriod());
                String sub = period.getSubject();

                TextView t = (TextView) findViewById(id);
                t.setText(sub);
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

        try {
            FileInputStream fis = openFileInput("periods.txt");
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

            for(int i=1; i<6; i++){
                for(int j=1; j<8; j++){
                    String periodName;
                    periodName = sc.nextLine();

                    period[i][j] = periodName;
                    periodView[i][j].setText(periodName);
                }
            }



        } catch (FileNotFoundException e) {
            Toast.makeText(getBaseContext(), "Click on the name to change it", Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        try {
            FileOutputStream file = openFileOutput("periods.txt",MODE_WORLD_READABLE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
            try {

                for(int x=1; x<6; x++){
                    for(int y=1; y<8; y++){
                        String Data = periodView[x][y].getText().toString() + "\n";
                        writer.write(Data);
                    }
                }
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
