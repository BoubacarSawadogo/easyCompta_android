package com.example.easycompta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.easycompta.R;

import javax.xml.datatype.Duration;

public class EtatsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Context ctx;
    ImageView img;
    TextView vente_total;
    TextView liste_des_depenses;
    TextView etat_stock;
    TextView voirbenefice, versbanque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etats);

        img = findViewById(R.id.logo_etats_activity);
        vente_total = findViewById(R.id.vente_total);
        liste_des_depenses = findViewById(R.id.liste_des_depenses);
        etat_stock = findViewById(R.id.etat_stock);
        voirbenefice = findViewById(R.id.voirbenefice);
        versbanque = findViewById(R.id.versbanque);

       // Inimation image rotation
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.logo_etats_activity);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.clockwise);
                image.startAnimation(animation);

                YoYo.with(Techniques.RollOut)
                        .duration(2000)
                        .repeat(1)
                        .playOn(vente_total);
                YoYo.with(Techniques.RollOut)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.RollOut)
                        .duration(2000)
                        .repeat(1)
                        .playOn(liste_des_depenses);
                YoYo.with(Techniques.RollOut)
                        .duration(2000)
                        .repeat(1)
                        .playOn(voirbenefice);
                YoYo.with(Techniques.RollOut)
                        .duration(2000)
                        .repeat(1)
                        .playOn(etat_stock);
            }
        });

        vente_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(liste_des_depenses);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(voirbenefice);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(etat_stock);

                Intent versVente = new Intent(ctx, ListeVentesActivity.class);
                startActivity(versVente);
            }
        });

        liste_des_depenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(vente_total);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(voirbenefice);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(etat_stock);
                Intent versdepenses = new Intent(ctx, ListeDepensesActivity.class);
                startActivity(versdepenses);
            }
        });
        voirbenefice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(vente_total);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(liste_des_depenses);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(etat_stock);
                Intent versbenefice = new Intent(ctx, BeneficeActivity.class);
                startActivity(versbenefice);
            }
        });


        etat_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(vente_total);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(liste_des_depenses);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(voirbenefice);
                Intent versetatstock = new Intent(ctx, StockActivity.class);
                startActivity(versetatstock);
            }
        });

        versbanque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(vente_total);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(versbanque);
                YoYo.with(Techniques.BounceInLeft)
                        .duration(2000)
                        .repeat(1)
                        .playOn(liste_des_depenses);
                YoYo.with(Techniques.BounceInRight)
                        .duration(2000)
                        .repeat(1)
                        .playOn(etat_stock);
                Intent versbanque = new Intent(ctx, BanqueActivity.class);
                startActivity(versbanque);
            }
        });

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
           /*  case R.id.hamburgerMenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                return true;*/
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
