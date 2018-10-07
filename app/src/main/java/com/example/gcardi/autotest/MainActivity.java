package com.example.gcardi.autotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTeory;
    Button btnTests;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "create called");

        btnTeory = findViewById(R.id.btn_teory);
        btnTests = findViewById(R.id.btn_tests);

        btnTeory.setOnClickListener(this);
        btnTests.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_teory :
                break;
            case R.id.btn_tests :
                Intent intent = new Intent(MainActivity.this, TestsActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "resume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "pause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy called");
    }
}
