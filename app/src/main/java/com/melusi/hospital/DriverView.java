package com.melusi.hospital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.ToggleButton;

public class DriverView extends AppCompatActivity {

    private DriverListAdapter adapter = new DriverListAdapter(this, LoginActivity.values,
            LoginActivity.hospitals, LoginActivity.toggles, LoginActivity.reserved);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ToggleButton[] btnWord = new ToggleButton[10];

        ListView list = (ListView) findViewById(R.id.driver_list);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.Update(LoginActivity.values, LoginActivity.hospitals,
                LoginActivity.toggles, LoginActivity.reserved);
        adapter.notifyDataSetChanged();
    }

}
