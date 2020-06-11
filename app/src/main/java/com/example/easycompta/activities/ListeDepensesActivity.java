package com.example.easycompta.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easycompta.R;
import com.example.easycompta.adapter.DepenseAdapter;
import com.example.easycompta.adapter.VenteAdapter;
import com.example.easycompta.entities.Depense;
import com.example.easycompta.entities.Vente;
import com.example.easycompta.manager.DepenseManager;
import com.example.easycompta.manager.VenteManager;

public class ListeDepensesActivity extends AppCompatActivity {

    ListView lv_depense;
    Button addButton_depense;
    LinearLayout ll_conteneur_leste_depenses;
    Context ctx;
    Depense depense, depenseBB;
    DepenseAdapter depenseAdapter;
    View layoutFromXml;
    EditText date, description, quantite, montant, fournisseur, statut;
    TextView total_depense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_depenses);

        lv_depense = findViewById(R.id.lv_depense);
        addButton_depense = findViewById(R.id.addButton_depense);
        ll_conteneur_leste_depenses = findViewById(R.id.ll_conteneur_leste_depenses);
        total_depense = findViewById(R.id.total_vente);


        ll_conteneur_leste_depenses.setOnCreateContextMenuListener(this);

        total_depense.setText("" + DepenseManager.calculMontantTotal(ctx));

        addButton_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verssaisiedepense = new Intent(ctx, SaisieDepenseFormActivity.class);
                startActivity(verssaisiedepense);
            }
        });


        lv_depense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                depense = depenseAdapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Que voulez vous faire?");
                builder.setPositiveButton("modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
                        LayoutInflater inflater = getLayoutInflater();
                        layoutFromXml = inflater.inflate(R.layout.activity_modify_depense, null);
                        builder1.setView(layoutFromXml);

                        date = layoutFromXml.findViewById(R.id.date_depense_modify);
                        description = layoutFromXml.findViewById(R.id.description_depense_modify);
                        quantite = layoutFromXml.findViewById(R.id.quantite_depense_modify);
                        montant = layoutFromXml.findViewById(R.id.montant_depense_modify);
                        fournisseur = layoutFromXml.findViewById(R.id.nom_fournisseur_modify);
                        statut = layoutFromXml.findViewById(R.id.status_depense_modify);

                        date.setText(depense.getDate());
                        description.setText(depense.getDescription());
                        quantite.setText(""+depense.getQuantite());
                        montant.setText(""+depense.getMontant());
                        fournisseur.setText(depense.getFournisseur());
                        statut.setText(depense.getStatut());



                        builder1.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                //venteB = new Vente();

                                EditText aa = layoutFromXml.findViewById(R.id.description_depense_modify);
                                EditText d = layoutFromXml.findViewById(R.id.date_depense_modify);
                                EditText quantites = layoutFromXml.findViewById(R.id.quantite_depense_modify);
                                EditText montant = layoutFromXml.findViewById(R.id.montant_depense_modify);
                                EditText fourn = layoutFromXml.findViewById(R.id.nom_fournisseur_modify);
                                EditText statut = layoutFromXml.findViewById(R.id.status_depense_modify);

                                String newdesc = aa.getText().toString();
                                String date = d.getText().toString();
                                int quantite = Integer.parseInt(quantites.getText().toString());
                                String fourni = fourn.getText().toString();
                                Double montan = Double.parseDouble(montant.getText().toString());
                                String stat = statut.getText().toString();


                                //       vente.setId(position + 1);
                                depense.setDate(date);
                                depense.setDescription(newdesc);
                                depense.setQuantite(quantite);
                                depense.setMontant(montan);
                                depense.setFournisseur(fourni);
                                depense.setStatut(stat);

                                DepenseManager.updateDepense(depense, ctx);
                                depenseAdapter.notifyDataSetChanged();
                                Toast.makeText(ctx, "Modifier", Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                            }
                        });

                        builder1.setNegativeButton("Annuler", null);

                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();
                    }
                });
                builder.setNegativeButton("supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog ad = (AlertDialog) dialog;
                        int id = depense.getId();
                        DepenseManager.deleteDepense(id, ctx);
                        depenseAdapter.remove(depense);
                        ad.dismiss();
                        depenseAdapter.notifyDataSetChanged();
                        total_depense.setText("" + DepenseManager.calculMontantTotal(ctx));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        depenseAdapter = new DepenseAdapter(this, R.layout.row_depense, DepenseManager.getAll(this));
        lv_depense.setAdapter(depenseAdapter);
        depenseAdapter.notifyDataSetChanged();

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
