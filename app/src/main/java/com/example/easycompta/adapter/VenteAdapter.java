package com.example.easycompta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easycompta.R;
import com.example.easycompta.entities.Vente;

import java.util.List;

public class VenteAdapter extends ArrayAdapter<Vente> {

    int layout;

    public VenteAdapter(@NonNull Context context, int resource, @NonNull List<Vente> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Vente vente = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layout, null);
        } else {
//            convertView.setBackgroundColor(Color.RED);
        }
        TextView date_vente = convertView.findViewById(R.id.date_vent);
        TextView description_vente = convertView.findViewById(R.id.description_vent);
        TextView quantite = convertView.findViewById(R.id.quantite);
        TextView montant = convertView.findViewById(R.id.montant_vent);
        TextView statut = convertView.findViewById(R.id.statut_vente);


        date_vente.setText(vente.getDate());
        description_vente.setText(vente.getDescription());
        quantite.setText(""+vente.getQuantite());
        montant.setText(""+vente.getMontant()+"$");
        statut.setText(vente.getStatut());

        return convertView;
    }

}
