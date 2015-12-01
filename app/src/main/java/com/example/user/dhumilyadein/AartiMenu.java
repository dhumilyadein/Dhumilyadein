package com.example.user.dhumilyadein;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AartiMenu extends AppCompatActivity {

    private final String LOGGER = "kapilLog";

    private Button ganeshjiaartiBtn;
    private Button ambejiaartiBtn;
    private Button ganeshjiaartiTxtBtn;
    private Button jagdishHareAartiTxtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Button listener
        addActionListenerOnBtn();
    }

    private void addActionListenerOnBtn() {

        ganeshjiaartiBtn = (Button) findViewById(R.id.ganeshjiaartiBtn);
        ambejiaartiBtn = (Button) findViewById(R.id.ambejiaartiBtn);
        ganeshjiaartiTxtBtn = (Button) findViewById(R.id.ganeshjiaartiTxtBtn);
        jagdishHareAartiTxtBtn = (Button) findViewById(R.id.jagdishHareAartiTxtBtn);

        final Context context = this;

        ganeshjiaartiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnBtn ganeshjiaarti start");
                Intent intent = new Intent(context, GaneshAarti.class);
                    startActivity(intent);

                Log.d(LOGGER, "addActionListenerOnBtn ganeshjiaarti end");
            }
        });

        ambejiaartiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnBtn ambejiaarti start");
                Intent intent = new Intent(context, AmbejiAarti.class);
                startActivity(intent);

                Log.d(LOGGER, "addActionListenerOnBtn ambejiaarti end");
            }
        });

        ganeshjiaartiTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnBtn GaneshjiAartiTxt start");
                Intent intent = new Intent(context, GaneshjiAartiTxt.class);
                startActivity(intent);

                Log.d(LOGGER, "addActionListenerOnBtn GaneshjiAartiTxt end");
            }
        });

        jagdishHareAartiTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(LOGGER, "addActionListenerOnBtn ambejiaarti start");
                Intent intent = new Intent(context, JagdishHareAartiTxt.class);
                startActivity(intent);

                Log.d(LOGGER, "addActionListenerOnBtn ambejiaarti end");
            }
        });
    }

}
