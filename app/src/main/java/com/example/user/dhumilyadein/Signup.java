package com.example.user.dhumilyadein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.database.SharedPreferenceSample;
import com.example.user.helper.CommonHelper;

public class Signup extends AppCompatActivity {

    Context context = null;

    private Button signupBtn;
    private EditText usernameSU;
    private EditText passwordSU;
    private EditText confirmPasswordSU;

    private SharedPreferenceSample sps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        sps = new SharedPreferenceSample(context, "DYUserDetails");

        usernameSU = (EditText) findViewById(R.id.usernameSUTxt);
        passwordSU = (EditText) findViewById(R.id.passwordSUTxt);
        confirmPasswordSU = (EditText) findViewById(R.id.confirmpasswordSUTxt);


        // Sign up
        signup();

    }

    private void signup() {

        signupBtn = (Button) findViewById(R.id.singupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameStored = null;
                String usernameSUStr = usernameSU.getText().toString();
                String passwordSUStr = passwordSU.getText().toString();
                String confirmPasswordSUStr = confirmPasswordSU.getText().toString();

                if (CommonHelper.isNotNullOrEmpty(usernameSUStr)
                        && CommonHelper.isNotNullOrEmpty(passwordSUStr)
                        && CommonHelper.isNotNullOrEmpty(confirmPasswordSUStr)) {

                    if(passwordSUStr.equals(confirmPasswordSUStr)) {

                        /*
                        Fetch record for the given username, if exits
                         */
                        usernameStored = sps.getData(usernameSUStr);

                        Intent intent = new Intent(context, LoginScreen.class);
                        startActivity(intent);
                        finish();

                        if (LoginScreen.DEFAULT.equals(usernameStored)) {
                            sps.saveData(usernameSUStr, passwordSUStr);
                        } else {
                            // Username already exists

                        }
                    } else {
                        // password and confirmPassword do not match
                    }
                } else {
                    //Mandatory fields missing
                }
            }
        });
    }

}
