package com.example.luba.supergraandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ZasadyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zasady);
    }

    public void wroc(View view) {
        super.onBackPressed();
    }
}
