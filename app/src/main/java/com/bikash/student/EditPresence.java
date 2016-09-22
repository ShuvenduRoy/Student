package com.bikash.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditPresence extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_presence);

        i = getIntent().getIntExtra("id", 0);
        //Toast.makeText(getBaseContext(), "Get value "+ i , Toast.LENGTH_SHORT).show();

        /*
        Getting the edit view by their id and setting the initail value
        it is required in case the user leave the field blank and press save
        other wise the app would crush
         */
        EditText et1 = (EditText) findViewById(R.id.editTextPresent);
        EditText et2 = (EditText) findViewById(R.id.editTextTotal);

        int n1 = Presence.s[i].present_classs;
        int n2 = Presence.s[i].total_class;



        et1.setText(String.valueOf(n1)); //Converting int to String
        et2.setText(String.valueOf(n2));


    }

    public void SaveButton(View view){
        Toast.makeText(getBaseContext(), "Saved" , Toast.LENGTH_SHORT).show();

        //Setting the value in the class Object

        EditText et1 = (EditText) findViewById(R.id.editTextPresent);
        EditText et2 = (EditText) findViewById(R.id.editTextTotal);

        String str1 = et1.getText().toString();
        String str2 = et2.getText().toString();

        Presence.s[i].present_classs = Integer.parseInt(str1); // making string a integer
        Presence.s[i].total_class = Integer.parseInt(str2);
    }
}
