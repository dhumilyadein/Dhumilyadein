package com.example.user.dhumilyadein;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.user.database.SharedPreferenceSample;
import com.example.user.helper.CommonHelper;

public class LoginScreen extends AppCompatActivity {

    private final String LOGGER = "kapilLog";

    final Context context = this;

    private Button loginBtn;
    private Button showPasswordBtn;
    private Button signupBtn;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private RelativeLayout loginScreenLayout;
    private SharedPreferenceSample sps = null;

    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    final static String DEFAULT = "Default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginScreenLayout = (RelativeLayout) findViewById(R.id.loginScreenLayout);
        showPasswordBtn = (Button) findViewById(R.id.showPasswordBtn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);

        sps = new SharedPreferenceSample(context, "DYUserDetails");

        //show password
        addActionListenerOnShowPasswordBtn();

        //Login button
        addActionListenerOnLoginBtn();

        // Sign up button
        addActionListenerOnSignupBtn();

        // hiding password on textbox focus
        // addActionListenerOnPasswordFiledTouch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addActionListenerOnShowPasswordBtn() {

        showPasswordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(LOGGER, "setOnTouchListener start");
                Editable password = passwordTxt.getText();
                Log.d(LOGGER, password.toString());

                //passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Log.d(LOGGER, "Down");
                        passwordTxt.setTransformationMethod(null);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.d(LOGGER, "Up");
                        passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;

                }

                Log.d(LOGGER, "setOnTouchListener end");
                return false;
            }
        });
    }

    private void addActionListenerOnLoginBtn() {

        loginBtn = (Button) findViewById(R.id.loginBtn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);
        usernameTxt = (EditText) findViewById(R.id.usernameTxt);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnLoginBtn start");
                String usernameInputStr = usernameTxt.getText().toString();
                String passwordInputStr = passwordTxt.getText().toString();

                String passwordStoredStr = sps.getData(usernameInputStr);

                boolean doVerification = true;

                if (CommonHelper.isNotNullOrEmpty(usernameInputStr) && CommonHelper.isNotNullOrEmpty(passwordInputStr)) {

                    if (passwordInputStr.equals(passwordStoredStr)) {

                        Intent intent = new Intent(context, FirstPageGreetings.class);
                        startActivity(intent);

                        // Finishing activity so that user can not return to this page more than once, except on start up
                        finish();

                    } else {

                        usernameTxt.setText("");
                        passwordTxt.setText("");
                        passwordTxt.setTransformationMethod(null);

                        loginScreenLayout.setFocusable(false);

                        showPopup(LoginScreen.this);

                    }
                } else {

                }

                Log.d(LOGGER, "addActionListenerOnLoginBtn end");
            }
        });
    }

    private void showPopup(final Activity context) {

        int popupWidth = 200;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        RelativeLayout viewGroup = (RelativeLayout) context.findViewById(R.id.invalidLoginPopupLayout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.content_invalid_login, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setOutsideTouchable(false);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.tryAgainBtn);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
                loginScreenLayout.setFocusable(false);

                passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    private void addActionListenerOnSignupBtn() {

        signupBtn = (Button) findViewById(R.id.singupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Signup.class);
                startActivity(intent);

                // Finishing activiy on redirection as on back from sign up we will create the activity again, and we dont want this activity instance to be active.
                finish();
            }
        });
    }

    private void addActionListenerOnPasswordFiledTouch() {

        passwordTxt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(LOGGER, "Printing on touch");

                // hiding password on activity load (mainly in back button)
                passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());

                return false;
            }
        });
    }

}
