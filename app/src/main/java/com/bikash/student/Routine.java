package com.bikash.student;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Routine extends AppCompatActivity {

    EditText EditClassName;
    TextView currentClassView;
    LinearLayout editLayout;

    //Activity of cancle Button
    public void cancleClassChange(View view){
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName.setText("");
    }

    //Activity of Save Button
    public void saveClass(View view){
        EditClassName = (EditText) findViewById(R.id.changeClassName);
        String className = EditClassName.getText().toString();
        currentClassView.setText(className);
        editLayout.setVisibility(View.INVISIBLE);
        EditClassName.setText("");
    }

    public void changeClass(View view){
        System.out.print("Clicked");

        editLayout.setVisibility(View.VISIBLE);
        currentClassView = (TextView) view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_routine);

        editLayout = (LinearLayout) findViewById(R.id.editClassLayout);

    }
}
