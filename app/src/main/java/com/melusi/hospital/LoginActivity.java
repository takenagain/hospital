package com.melusi.hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public static ArrayList<String> values = new ArrayList<> (Arrays.asList(
            "Bed1", "Bed2", "Bed3", "Bed4", "Bed5", "Bed1", "Bed2", "Bed3",
            "Bed4", "Bed5", "Bed1", "Bed2", "Bed3", "Bed4", "Bed5"
    ));

    public static ArrayList<String> hospitals = new ArrayList<> (Arrays.asList(
            "Hospital A", "Hospital A", "Hospital A", "Hospital A", "Hospital A",
            "Hospital B", "Hospital B", "Hospital B", "Hospital B", "Hospital B",
            "Hospital C", "Hospital C", "Hospital C", "Hospital C", "Hospital C"
            ));

    public static ArrayList<Boolean> toggles = new ArrayList<>(Arrays.asList(
            false, true, false, false, true, false, false, true, false,
            false, true, false, false, true, false
    ));

    public static ArrayList<Boolean> reserved = new ArrayList<>(Arrays.asList(
            false, false, false, false, true, false, false, false, false,
            false, false, false, false, false, false
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        Button mNurse = findViewById(R.id.nurse);
        mNurse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), NurseView.class);
                startActivity(in);
            }
        });

        Button mDriver = findViewById(R.id.driver);
        mDriver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), DriverView.class);
                startActivity(in);
            }
        });
    }

}

