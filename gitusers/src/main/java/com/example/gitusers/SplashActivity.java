package com.example.gitusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//Не работает. Экран до загрузки основоого приложения
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
    /*    try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        startActivity(intent);
        finish();
    }
}
