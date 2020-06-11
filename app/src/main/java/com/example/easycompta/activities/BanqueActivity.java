package com.example.easycompta.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easycompta.R;
import com.example.easycompta.manager.DepenseManager;
import com.example.easycompta.manager.VenteManager;

public class BanqueActivity extends AppCompatActivity {

    TextView payefournisseur, payeclient, solde_banque;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banque);

        payeclient = findViewById(R.id.payeclient);
        payefournisseur = findViewById(R.id.payefournisseur);
        solde_banque = findViewById(R.id.solde_banque);

        double sommepayeparclients = VenteManager.calculMontantTotalPayeParClients(ctx);
        double sommespayeeauxfournisseur = DepenseManager.calculDepensesTotalesPayees(ctx);
        double solde = sommepayeparclients - sommespayeeauxfournisseur;
        payeclient.setText(sommepayeparclients+"$");
        payefournisseur.setText(sommespayeeauxfournisseur+"$");
        solde_banque.setText(solde+"$");

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
