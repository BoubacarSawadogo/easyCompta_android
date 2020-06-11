package com.example.easycompta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycompta.R;
import com.example.easycompta.adapter.VenteAdapter;
import com.example.easycompta.entities.Vente;
import com.example.easycompta.manager.VenteManager;

public class ListeVentesActivity extends AppCompatActivity {

    ListView lv;
    Button addButton;
    LinearLayout ll;
    Context ctx;
    Vente vente, venteB;
    VenteAdapter venteAdapter;
    View layoutFromXml;
    EditText date, description, quantite, montant, client, statut;
    TextView total_vente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_ventes);

        lv = findViewById(R.id.lv_vente);
        addButton = findViewById(R.id.addButton);
        ll = findViewById(R.id.ll);
        total_vente = findViewById(R.id.total_vente);


        ll.setOnCreateContextMenuListener(this);

        total_vente.setText("" + VenteManager.calculMontantTotal(ctx));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addContact = new Intent(ctx, SaisieVenteFormActivity.class);
                startActivity(addContact);
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                vente = venteAdapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Que voulez vous faire?");
                builder.setPositiveButton("modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
                        LayoutInflater inflater = getLayoutInflater();
                        layoutFromXml = inflater.inflate(R.layout.activity_modify_vente, null);
                        builder1.setView(layoutFromXml);

                        date = layoutFromXml.findViewById(R.id.date_vente_modify);
                        description = layoutFromXml.findViewById(R.id.description_vente_modify);
                        quantite = layoutFromXml.findViewById(R.id.quantite_vendue_modify);
                        montant = layoutFromXml.findViewById(R.id.montant_vente_modify);
                        client = layoutFromXml.findViewById(R.id.nom_client_modify);
                        statut = layoutFromXml.findViewById(R.id.status_modify);

                        date.setText(vente.getDate());
                        description.setText(vente.getDescription());
                        quantite.setText(""+vente.getQuantite());
                        montant.setText(""+vente.getMontant());
                        client.setText(vente.getClient());
                        statut.setText(vente.getStatut());



                        builder1.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                venteB = new Vente();

                                EditText aa = layoutFromXml.findViewById(R.id.description_vente_modify);
                                EditText d = layoutFromXml.findViewById(R.id.date_vente_modify);
                                EditText quantites = layoutFromXml.findViewById(R.id.quantite_vendue_modify);
                                EditText montant = layoutFromXml.findViewById(R.id.montant_vente_modify);
                                EditText client = layoutFromXml.findViewById(R.id.nom_client_modify);
                                EditText statut = layoutFromXml.findViewById(R.id.status_modify);

                                String newdesc = aa.getText().toString();
                                String date = d.getText().toString();
                                int quantite = Integer.parseInt(quantites.getText().toString());
                                String clien = client.getText().toString();
                                Double montan = Double.parseDouble(montant.getText().toString());
                                String stat = statut.getText().toString();


                         //       vente.setId(position + 1);
                                vente.setDate(date);
                                vente.setDescription(newdesc);
                                vente.setQuantite(quantite);
                                vente.setMontant(montan);
                                vente.setClient(clien);
                                vente.setStatut(stat);

                                VenteManager.updateVente(vente, ctx);
                                venteAdapter.notifyDataSetChanged();
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
                        int id = vente.getId();
                        VenteManager.deleteVente(id, ctx);
                        venteAdapter.remove(vente);
                        ad.dismiss();
                        venteAdapter.notifyDataSetChanged();
                        total_vente.setText("" + VenteManager.calculMontantTotal(ctx));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        venteAdapter = new VenteAdapter(this, R.layout.row_vente, VenteManager.getAll(this));
        lv.setAdapter(venteAdapter);
        venteAdapter.notifyDataSetChanged();
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
