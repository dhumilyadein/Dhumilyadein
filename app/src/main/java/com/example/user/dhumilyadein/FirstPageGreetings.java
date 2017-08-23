package com.example.user.dhumilyadein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FirstPageGreetings extends AppCompatActivity {

    private final String LOGGER = "kapilLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_greetings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navigating to home page
        navigateToHomePage();

    }

    @Override
    public void onBackPressed() {


    }

    private void navigateToHomePage() {

        final Context context = this;

        ImageView imageView = (ImageView) findViewById(R.id.fistPageGreetingsImg);

        //Log.d(LOGGER, "before run");
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Log.d(LOGGER, "in run");
                //Intent intent = new Intent(context, AartiMenu.class);
                Intent intent = new Intent(context, AppMenu.class);
                startActivity(intent);

                finish();
            }
        }, 2000);
    }

}
