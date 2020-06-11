package com.example.easycompta.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easycompta.R;
import com.example.easycompta.manager.DepenseManager;
import com.example.easycompta.manager.VenteManager;

public class StockActivity extends AppCompatActivity {

    TextView qte_achete;
    TextView qte_vendu;
    TextView stock_final;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        qte_achete = findViewById(R.id.qte_achete);
        qte_vendu = findViewById(R.id.qte_vendu);
        stock_final = findViewById(R.id.stock_final);


        int qte_totale_acheter = DepenseManager.calculQuantiteTotaleAchete(ctx);
        int qte_total_vendue = VenteManager.calculQuantiteTotaleVendu(ctx);


        int stock_final_total = qte_totale_acheter - qte_total_vendue;

        qte_achete.setText(String.valueOf(qte_totale_acheter));
        qte_vendu.setText(String.valueOf(qte_total_vendue));
        stock_final.setText(String.valueOf(stock_final_total));

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
