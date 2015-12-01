package com.example.user.dhumilyadein;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.Timer;

public class LoginScreen extends AppCompatActivity {

    private final String LOGGER = "kapilLog";
    private Button loginBtn;
    private Button showPasswordBtn;
    private Button tryAgainBtn;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private RelativeLayout loginScreenLayout;
    private final String userPassword = "vyas";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginScreenLayout = (RelativeLayout) findViewById(R.id.loginScreenLayout);

        //show password
        addActionListenerOnShowPasswordBtn();

        //Login button
        addActionListenerOnLoginBtn();

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

        showPasswordBtn = (Button) findViewById(R.id.showPasswordBtn);
        passwordTxt = (EditText) findViewById(R.id.passwordTxt);

//        showPasswordBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d(LOGGER, "addActionListenerOnShowPasswordBtn start");
//                Editable password = passwordTxt.getText();
//                Log.d(LOGGER, password.toString());
//
//                passwordTxt.setHint(password.toString());
//                passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//
//                Log.d(LOGGER, "addActionListenerOnShowPasswordBtn end");
//            }
//        });

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
                        //passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.d(LOGGER, "Up");
                        passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        //passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
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

        final Context context = this;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnLoginBtn start");
                Editable password = passwordTxt.getText();
                Log.d(LOGGER, password.toString());

//                passwordTxt.setText("Password reset");
//                passwordTxt.setTransformationMethod(null);
                //passwordTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                boolean doVerification = true;

                if (userPassword.equals(password.toString())) {
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

//                    final Dialog dialog = new Dialog(context);
//                    dialog.setContentView(R.layout.content_invalid_login);
//                    dialog.show();
//
//                    tryAgainBtn = (Button) findViewById(R.id.tryAgainBtn);
//                    tryAgainBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            dialog.dismiss();;
//                        }
//                    });

                    //dialog.dismiss();
                }

                Log.d(LOGGER, "addActionListenerOnLoginBtn end");
            }
        });
    }

    private void showPopup(final Activity context) {

        int popupWidth = 200;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        RelativeLayout viewGroup = (RelativeLayout) context.findViewById(R.id.popupLayout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.content_invalid_login, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
//        popup.setWidth(popupWidth);
//        popup.setHeight(popupHeight);

        popup.setOutsideTouchable(false);
        //popup.setBackgroundDrawable(null);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

//        // Clear the default translucent background
//        popup.setBackgroundDrawable(new BitmapDrawable());

        //The "x" and "y" position of the "Show Button" on screen.
//        Point p = new Point();
//        p.x = 30;
//        p.y = 30;
        // Displaying the popup at the specified location, + offsets.
        //popup.showAtLocation(layout, Gravity.NO_GRAVITY, 75, 450);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.tryAgainBtn);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
                loginScreenLayout.setFocusable(false);
            }
        });
    }


}
