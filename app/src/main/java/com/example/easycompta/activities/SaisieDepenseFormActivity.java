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
import com.example.easycompta.entities.Depense;
import com.example.easycompta.entities.Vente;
import com.example.easycompta.manager.DepenseManager;
import com.example.easycompta.manager.VenteManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SaisieDepenseFormActivity extends AppCompatActivity {

    EditText date_depense;
    EditText description_depense;
    EditText montant_depense;
    EditText qte_depense;
    EditText nom_fournisseur;
    RadioGroup rgroup_statut_depense;
    Context ctx;
    Button add_new_depense;
    // BusinessBookDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_depense_form);

        // Recuperation des des champs du formulaire de saisie
        date_depense = findViewById(R.id.date_depense);
        description_depense = findViewById(R.id.description_depense);
        montant_depense = findViewById(R.id.montant_depense);
        qte_depense = findViewById(R.id.qte_depense);
        nom_fournisseur = findViewById(R.id.nom_fournisseur);
        rgroup_statut_depense = findViewById(R.id.rgroup_statut_depense);
        add_new_depense = findViewById(R.id.add_new_depense);

        // Date picker method
        final Calendar myCalendar = Calendar.getInstance();
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
                date_depense.setText(sdf.format(myCalendar.getTime()));
            }
        };

       date_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        add_new_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Recuperatio du contenu de chaque champs du formulaire de saisie de depense
                String date = date_depense.getText().toString().trim();
                String description = description_depense.getText().toString();
                float montant = Float.parseFloat(montant_depense.getText().toString());
                Integer quantite = Integer.parseInt(qte_depense.getText().toString());
                String fournisseur = nom_fournisseur.getText().toString();

                int radioButtonCheckedId = rgroup_statut_depense.getCheckedRadioButtonId();
                RadioButton radioButtonChecked = findViewById(radioButtonCheckedId);
                String statut = radioButtonChecked.getText().toString();

                Depense newDepense = new Depense();
                newDepense.setDate(date);
                newDepense.setDescription(description);
                newDepense.setMontant(montant);
                newDepense.setQuantite(quantite);

                newDepense.setFournisseur(fournisseur);
                newDepense.setStatut(statut);

                boolean inser = DepenseManager.addDepense(newDepense,ctx);

                if(inser==true){
                    Toast.makeText(getApplicationContext(),"Ajoute aux depense", Toast.LENGTH_LONG).show();
                    Intent versListeDepenses = new Intent(ctx, ListeDepensesActivity.class);
                    startActivity(versListeDepenses);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(),"Echec d'ajout a la liste de depenses", Toast.LENGTH_LONG).show();
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
