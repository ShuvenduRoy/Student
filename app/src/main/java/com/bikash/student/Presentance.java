package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Presentance extends AppCompatActivity {
    public Teacher t;
    public TextView s1_name;
    public TextView s1_percentage;
    public TextView s1_count;

    //Adding functionality for the button "+" id = Present
    public void presentButton(View view){
        t.total_class++;
        t.present_classs++;

        int per = (t.present_classs*100)/t.total_class;
        s1_percentage.setText(Integer.toString(per) + "%");
        s1_count.setText(t.present_classs+"/"+t.total_class);


        if(per>=60){
            s1_percentage.setBackgroundColor(Color.parseColor("#379237"));
        } else {
            s1_percentage.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }


    //Adding functionality for the button "-" id = Basent
    public void absentButton(View view){
        t.total_class++;

        int per = (t.present_classs*100)/t.total_class;
        s1_percentage.setText(Integer.toString(per) + "%");
        s1_count.setText(t.present_classs+"/"+t.total_class);


        if(per>60){
            s1_percentage.setBackgroundColor(Color.parseColor("#379237"));
        } else {
            s1_percentage.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentance);

        //Making a teacher Object
        t  = new Teacher("Advanced Algorithm",0,0);


        //Getting textview for all ids bt its id
        s1_name = (TextView)findViewById(R.id.t1);  //Name of Teachar 1 or Subject 1
        s1_percentage = (TextView)findViewById(R.id.t1p); //Subject 1 Percentage count for Teacher 1
        s1_count = (TextView)findViewById(R.id.count_1);



        //Working with Subject 1
        s1_name.setText(t.name);
        s1_percentage.setText( "0%");
        s1_percentage.setBackgroundColor(Color.parseColor("#379237"));




    }
}
