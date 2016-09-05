package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Presentance extends AppCompatActivity {
    int n = 5;
    public Subject[] s = new Subject[5];
    public TextView[] sub_name = new TextView[5];
    public TextView[] sub_percentage = new TextView[5];
    public TextView[] sub_count = new TextView[5];

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


        try {
            FileOutputStream file = openFileOutput("data.txt",MODE_WORLD_READABLE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
            try {
                for(int j=0; j<n; j++){
                    writer.write(s[j].total_class + " ");
                    writer.write(s[j].present_classs + " ");}
                writer.flush();
                writer.close();
               // Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        try {
            FileOutputStream file = openFileOutput("data.txt",MODE_WORLD_READABLE);
            OutputStreamWriter writer = new OutputStreamWriter(file);

                try {
                    for(int j=0; j<n; j++){
                    writer.write(s[j].total_class + " ");
                    writer.write(s[j].present_classs + " ");}
                    writer.flush();
                    writer.close();
                    //Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        setContentView(R.layout.activity_presentance);



        //Making a teacher Object
        s[0] = new Subject("MicroProcessor",0,0);
        s[1] = new Subject("Advanced Algorithm",0,0);
        s[2] = new Subject("Numerical Method",0,0);
        s[3] = new Subject("Math",0,0);
        s[4] = new Subject("Economics",0,0);

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

            Toast.makeText(getBaseContext(), final_data , Toast.LENGTH_SHORT).show();
            Scanner sc = new Scanner(final_data);
            for(int i=0; i<n; i++){
                s[i].total_class = sc.nextInt();
                s[i].present_classs = sc.nextInt();
            }

        } catch (FileNotFoundException e) {
            Toast.makeText(getBaseContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
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




        //Working with Subject 1
        for(int i=0; i<n; i++){
            sub_name[i].setText(s[i].name);
            sub_percentage[i].setText( "0%");
            sub_percentage[i].setBackgroundColor(Color.parseColor("#379237"));
        }




    } // End of onCreat

    @Override
    protected void onDestroy(){
        Toast.makeText(getBaseContext(), "Destroy", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}