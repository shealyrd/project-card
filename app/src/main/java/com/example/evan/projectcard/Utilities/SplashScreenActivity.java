package com.example.evan.projectcard.Utilities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.evan.projectcard.MainScreen.MainScreenActivity;

/**
 * Created by Evan on 10/16/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
        finish();
    }
}