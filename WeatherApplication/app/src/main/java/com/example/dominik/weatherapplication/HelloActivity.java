package com.example.dominik.weatherapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void onNextClick(View view){
        Intent intent = new Intent(this,SelectRegActivity.class);
        startActivity(intent);
        finish();
    }





}
