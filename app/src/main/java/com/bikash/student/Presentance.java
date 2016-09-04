package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Presentance extends AppCompatActivity {
    int n = 5;
    public Subject[] s = new Subject[5];
    public TextView[] sub_name = new TextView[5];
    public TextView[] sub_percentage = new TextView[5];
    public TextView[] sub_count = new TextView[5];

    //Adding functionality for the button "+" id = Present
    public void presentButton(View view){
        s[1].total_class++;
        s[1].present_classs++;

        int per = (s[1].present_classs*100)/ s[1].total_class;
        sub_percentage[1].setText(Integer.toString(per) + "%");
        sub_count[1].setText(s[1].present_classs+"/"+ s[1].total_class);


        if(per>=60){
            sub_percentage[1].setBackgroundColor(Color.parseColor("#379237"));
        } else {
            sub_percentage[1].setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }


    //Adding functionality for the button "-" id = Basent
    public void absentButton(View view){
        s[1].total_class++;

        int per = (s[1].present_classs*100)/ s[1].total_class;
        sub_percentage[1].setText(Integer.toString(per) + "%");
        sub_count[1].setText(s[1].present_classs+"/"+ s[1].total_class);


        if(per>60){
            sub_percentage[1].setBackgroundColor(Color.parseColor("#379237"));
        } else {
            sub_percentage[1].setBackgroundColor(Color.parseColor("#ff0000"));
        }
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


        //Getting textview for all ids bt its id
        sub_name[1] = (TextView)findViewById(R.id.t1);  //Name of Teachar 1 or Subject 1
        sub_percentage[1] = (TextView)findViewById(R.id.t1p); //Subject 1 Percentage count for Subject 1
        sub_count[1] = (TextView)findViewById(R.id.count_1); //View for 0/0 like presentation



        //Working with Subject 1
        sub_name[1].setText(s[1].name);
        sub_percentage[1].setText( "0%");
        sub_percentage[1].setBackgroundColor(Color.parseColor("#379237"));




    }
}
