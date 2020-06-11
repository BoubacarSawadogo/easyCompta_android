package com.example.easycompta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycompta.R;
import com.example.easycompta.entities.User;
import com.example.easycompta.manager.UserManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
Context ctx;
TextView tv_prenom;
DrawerLayout drawerLayout;
TextView tv_etats;
TextView tv_saisie_depense;
TextView bienvenue;
LinearLayout ll;
ImageView img_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv_etats = findViewById(R.id.tv_etats);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        bienvenue = findViewById(R.id.bienvenue);
        tv_saisie_depense = findViewById(R.id.tv_saisie_depense);
        ll = findViewById(R.id.container);
        img_menu = findViewById(R.id.img_menu);


        ll.setBackgroundColor(getResources().getColor(R.color.colorblue));

        tv_etats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainRegister.class);
                startActivity(intent);
            }
        });

    }



    public void openEtat(){
        Intent intent = new Intent(getApplicationContext(), MainRegister.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      // drawerLayout = findViewById(R.id.drawerLayout);

        switch(item.getItemId()){
             case R.id.hamburgerMenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;
            case R.id.etats:
                Intent intent = new Intent(getApplicationContext(), EtatsActivity.class);
                startActivity(intent);
                return true;
            case R.id.deconnexion:
                Intent intentlog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentlog);
                return true;
            case R.id.new_vente:
                Intent intentvent = new Intent(getApplicationContext(), SaisieVenteFormActivity.class);
                startActivity(intentvent);
                return true;
            case R.id.new_depense:
                Intent intentdep = new Intent(getApplicationContext(), SaisieDepenseFormActivity.class);
                startActivity(intentdep);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }

