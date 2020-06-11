package com.example.easycompta.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easycompta.R;
import com.example.easycompta.manager.DepenseManager;
import com.example.easycompta.manager.VenteManager;

public class BeneficeActivity extends AppCompatActivity {

    TextView vte_total, dep_total, benefice, message;
    ImageView imageConteneur;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefice);

        vte_total = findViewById(R.id.vte_total);
        dep_total = findViewById(R.id.dep_total);
        benefice = findViewById(R.id.benefice);
        message = findViewById(R.id.message);
        imageConteneur = findViewById(R.id.imageConteneur);

        double ventes = VenteManager.calculMontantTotal(ctx);
        double depenses = DepenseManager.calculMontantTotal(ctx);
        double gain = ventes - depenses;

        vte_total.setText(ventes+"$");
        dep_total.setText(depenses+"$");

        benefice.setText(gain+"$");
        if(gain<0){
            benefice.setBackgroundColor(getResources().getColor(R.color.redColor));
            message.setText("Vous êtes en perte!");
            imageConteneur.setImageResource(R.drawable.lachepas);
        } else if(gain>0){
            message.setText("Vous avez un gain !!");
            imageConteneur.setImageResource(R.drawable.congrat);
        }
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
