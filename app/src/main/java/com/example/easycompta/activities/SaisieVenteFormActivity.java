package com.example.easycompta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.easycompta.R;
import com.example.easycompta.entities.Vente;
import com.example.easycompta.manager.VenteManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SaisieVenteFormActivity extends AppCompatActivity {

    EditText date_vente;
    EditText description_vente;
    EditText montant_vente;
    EditText qte_vendu;
    EditText nom_client;
    RadioGroup rgroup_statut_vente;
    Context ctx;
    Button add_new_vente;
   // BusinessBookDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_vente_form);

      //  db = new BusinessBookDatabaseHelper(this, "businessdatabase", null, 1);
      //  ConnexionBd.importDatabase(ctx);


        date_vente = findViewById(R.id.date_vente);
        description_vente = findViewById(R.id.description_vente);
        montant_vente = findViewById(R.id.montant_vente);
        qte_vendu = findViewById(R.id.qte_vendue);
        nom_client = findViewById(R.id.nom_client);
        rgroup_statut_vente = findViewById(R.id.rgroup_statut_vente);
        add_new_vente = findViewById(R.id.add_new_vente);

        // Date picker method
        final Calendar myCalendar = Calendar.getInstance();
     //   EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

                date_vente.setText(sdf.format(myCalendar.getTime()));
            }
        };

        date_vente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        add_new_vente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = date_vente.getText().toString().trim();
                String description = description_vente.getText().toString();
                float montant = Float.parseFloat(montant_vente.getText().toString());
                Integer quantite = Integer.parseInt(qte_vendu.getText().toString());
                String client = nom_client.getText().toString();

                int radioButtonCheckedId = rgroup_statut_vente.getCheckedRadioButtonId();
                RadioButton radioButtonChecked = findViewById(radioButtonCheckedId);
                String statut = radioButtonChecked.getText().toString();

                Vente newVente = new Vente();
                newVente.setDate(date);
                newVente.setDescription(description);
                newVente.setMontant(montant);
                newVente.setQuantite(quantite);
                newVente.setClient(client);
                newVente.setStatut(statut);

            boolean inser = VenteManager.addVente(newVente,ctx);

            if(inser==true){
                Toast.makeText(getApplicationContext(),"Ajoute aux ventes", Toast.LENGTH_LONG).show();
                Intent listContact = new Intent(ctx, ListeVentesActivity.class);
                startActivity(listContact);
                finish();

            } else{
                Toast.makeText(getApplicationContext(),"Echec d'ajout a la liste", Toast.LENGTH_LONG).show();

            }
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
