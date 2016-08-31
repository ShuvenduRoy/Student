package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Presentance extends AppCompatActivity {
    public Teacher t;
    public TextView t1_n;
    public TextView t1_p;

    public void presentButton(View view){
        t.total_class++;
        t.present_classs++;

        int per = (t.present_classs*100)/t.total_class;
        t1_p.setText(Integer.toString(per) + "%");


        if(per>=60){
            t1_p.setBackgroundColor(Color.parseColor("#379237"));
        } else {
            t1_p.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }

    public void absentButton(View view){
        t.total_class++;

        int per = (t.present_classs*100)/t.total_class;
        t1_p.setText(Integer.toString(per) + "%");


        if(per>60){
            t1_p.setBackgroundColor(Color.parseColor("#379237"));
        } else {
            t1_p.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentance);

        //Making a teacher Object
        t  = new Teacher("Rana Sir",1,1);


        //Getting textview bt its id
        t1_n = (TextView)findViewById(R.id.t1);
        t1_n.setText(t.name);

        t1_p = (TextView)findViewById(R.id.t1p);
        int per = (t.present_classs*100)/t.total_class;
        t1_p.setText(Integer.toString(per) + "%");


        if(per>60){
            t1_p.setBackgroundColor(Color.parseColor("#379237"));
        } else {
            t1_p.setBackgroundColor(Color.parseColor("#ff0000"));
        }


    }
}
