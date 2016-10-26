package com.bikash.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LocalSignIn extends AppCompatActivity {

    private Button welcomeButton;
    private EditText instituteSelector;
    private EditText deparemtntSelector;
    private EditText batchSelector;
    private EditText nametext;

    private String institute;
    private String department;
    private String batch;
    private String userInfo;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_sign_in);

        //Getting the edit views by their ids
        instituteSelector = (EditText) findViewById(R.id.InstituteSelectorEditText);
        deparemtntSelector = (EditText) findViewById(R.id.DeparementSelectorEditText);
        batchSelector = (EditText) findViewById(R.id.BatchSelectorEditText);
        nametext = (EditText) findViewById(R.id.NameSelectorEditText);


        sharedPreferences = this.getSharedPreferences("com.bikash.student", Context.MODE_PRIVATE);





        //Geetting the butto with its id
        welcomeButton = (Button) findViewById(R.id.WelcomeContinueButton);

        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Getting the user input
                 * It is inside try catch
                 * because user may keep any field blank
                 *
                 */

                try{

                    HomeActivity.mUsername = instituteSelector.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    institute = instituteSelector.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    department = deparemtntSelector.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }

                try{

                    batch = batchSelector.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }

                userInfo = institute+department+batch;

                Log.i("Name", userInfo);


                HomeActivity.userGroup = userInfo;
                sharedPreferences.edit().putString("userGroup", userInfo).apply();
                sharedPreferences.edit().putString("userName", HomeActivity.mUsername).apply();
                Toast.makeText(getBaseContext(), "Your are logged into group " + userInfo, Toast.LENGTH_LONG).show();

                finish();

            }
        });

    }


}
