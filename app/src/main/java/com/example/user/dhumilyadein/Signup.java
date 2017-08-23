package com.example.user.dhumilyadein;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.database.dbhelper.UsersDBHelper;
import com.example.user.dto.Users;
import com.example.user.helper.CommonHelper;

public class Signup extends AppCompatActivity {

    private final String DEBUG = "kapilDebug";
    private final String TRACE = "kapilTrace";

    Context context = null;

    private Button signupBtn;
    private EditText usernameSU;
    private EditText passwordSU;
    private EditText confirmPasswordSU;

    //private SharedPreferenceSample sps = null;

    private RelativeLayout signupLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        //sps = new SharedPreferenceSample(context, "DYUserDetails");

        usernameSU = (EditText) findViewById(R.id.usernameSUTxt);
        passwordSU = (EditText) findViewById(R.id.passwordSUTxt);
        confirmPasswordSU = (EditText) findViewById(R.id.confirmpasswordSUTxt);


        // Sign up
        signup();

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(context, LoginScreen.class);
        startActivity(intent);

        finish();
    }

    private void signup() {

        signupBtn = (Button) findViewById(R.id.singupBtn);
        signupLayout = (RelativeLayout) findViewById(R.id.signupLayout);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String usernameStored = null;
                String usernameSUStr = usernameSU.getText().toString();
                String passwordSUStr = passwordSU.getText().toString();
                String confirmPasswordSUStr = confirmPasswordSU.getText().toString();

                if (CommonHelper.isNotNullAndNotEmpty(usernameSUStr)
                        && CommonHelper.isNotNullAndNotEmpty(passwordSUStr)
                        && CommonHelper.isNotNullAndNotEmpty(confirmPasswordSUStr)) {

                    if(passwordSUStr.equals(confirmPasswordSUStr)) {

                        /*
                        Fetch record for the given username, if exits
                         */
                        //usernameStored = sps.getData(usernameSUStr);
                        UsersDBHelper usersDBHelper = new UsersDBHelper(context);
                        Users user = usersDBHelper.readUser(usernameSUStr);

                        if(null == user || (null != user && !CommonHelper.isNotNullAndNotEmpty(user.getUsername()))) {
                        //if (LoginScreen.DEFAULT.equals(usernameStored)) {

                            //sps.saveData(usernameSUStr, passwordSUStr);
                            saveUserInDB(usernameSUStr, passwordSUStr);


                            Intent intent = new Intent(context, LoginScreen.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Username already exists
                            Log.d(TRACE, "Username already exists - " + user.getUsername());

                            usernameSU.setText("");
                            passwordSU.setText("");
                            confirmPasswordSU.setText("");
                            passwordSU.setTransformationMethod(null);
                            confirmPasswordSU.setTransformationMethod(null);

                            signupLayout.setFocusable(false);

                            showPopupOnSignUp(Signup.this, "User already exist!", "Try other username");
                            //finish();
                        }
                    } else {
                        // password and confirmPassword do not match
                        showPopupOnSignUp(Signup.this, "Passwords does not match!", "Try again");
                    }
                } else {
                    //Mandatory fields missing
                    showPopupOnSignUp(Signup.this, "Mandatory parameters missing!", "Provide required details");
                }
            }
        });
    }

    private void saveUserInDB(String usernameSUStr, String passwordSUStr) {

        UsersDBHelper usersDBHelper = new UsersDBHelper(context);
        Users users = new Users();
        users.setUsername(usernameSUStr);
        users.setPassword(passwordSUStr);
        users.setEmail(usernameSUStr + "@abc.com");
        usersDBHelper.createUser(users);
        Log.d(TRACE, "User Saved in DB " + usernameSUStr);
    }

    private void showPopupOnSignUp(final Activity context, String textViewTxt, String buttonTxt) {

        int popupWidth = 200;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        RelativeLayout userAlreadyExistPopupLayout = (RelativeLayout) context.findViewById(R.id.userAlreadyExistPopupLayout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.content_user_already_exist_pop_up, userAlreadyExistPopupLayout);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setOutsideTouchable(false);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        TextView t = (TextView)layout.findViewById(R.id.userAlreadyExistLbl);
        t.setText(textViewTxt);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.tryOtherUsernameBtn);
        close.setText(buttonTxt);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
                signupLayout.setFocusable(false);

                passwordSU.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPasswordSU.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

}
