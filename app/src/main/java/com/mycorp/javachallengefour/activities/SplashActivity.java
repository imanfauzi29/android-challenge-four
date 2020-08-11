package com.mycorp.javachallengefour.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.luqman.dev.helloworld.R;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static Boolean terms;
    public static final String  MY_SHARED = "session_status";
    public static final String  SESSION_TERMS = "syarat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(MY_SHARED, Context.MODE_PRIVATE);

        terms = sharedPreferences.getBoolean(SESSION_TERMS, false);

        int ms = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!terms){
                    startActivity(new Intent(SplashActivity.this, TermActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, ms);
    }
}