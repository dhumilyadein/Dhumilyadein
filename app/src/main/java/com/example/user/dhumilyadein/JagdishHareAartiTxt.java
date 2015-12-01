package com.example.user.dhumilyadein;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class JagdishHareAartiTxt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jagdish_hare_aarti_txt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView jagdishHareTextView = (TextView)findViewById(R.id.jagdishHareAartiTxtLbl);

        jagdishHareTextView.setMovementMethod(new ScrollingMovementMethod());
    }

}
