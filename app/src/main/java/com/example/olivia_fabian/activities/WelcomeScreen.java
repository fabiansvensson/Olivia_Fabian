package com.example.olivia_fabian.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.olivia_fabian.R;

public class WelcomeScreen extends AppCompatActivity {
    private ProgressBar pgsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        pgsBar.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                createIntent();
                pgsBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    public void createIntent() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
