package com.example.easycompta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.easycompta.R;

public class SplasScreenActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);

        //rediriger vers la page d'accueil

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Toast.makeText(ctx, "Successfully Login", Toast.LENGTH_SHORT).show();
                Intent pageAcceuil = new Intent(ctx, WelcomeActivity.class);
                startActivity(pageAcceuil);
                finish();
            }
        };
        // handler post delay
        new Handler().postDelayed(runnable, 2000);
    }
}
