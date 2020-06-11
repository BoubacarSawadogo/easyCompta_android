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
import com.example.easycompta.entities.Depense;
import com.example.easycompta.entities.Vente;

import java.util.List;

public class DepenseAdapter extends ArrayAdapter<Depense> {

    int layout;

    public DepenseAdapter(@NonNull Context context, int resource, @NonNull List<Depense> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Depense depense = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layout, null);
        } else {
//            convertView.setBackgroundColor(Color.RED);
        }
        TextView date_row_dep = convertView.findViewById(R.id.date_row_dep);
        TextView description_row_dep = convertView.findViewById(R.id.description_row_dep);
        TextView quantite_row_dep = convertView.findViewById(R.id.quantite_row_dep);
        TextView montant_row_dep = convertView.findViewById(R.id.montant_row_dep);
        TextView statut_row_dep = convertView.findViewById(R.id.statut_row_dep);


        date_row_dep.setText(depense.getDate());
        description_row_dep.setText(depense.getDescription());
        quantite_row_dep.setText(""+depense.getQuantite());
        montant_row_dep.setText(""+depense.getMontant()+"$");
        statut_row_dep.setText(depense.getStatut());

        return convertView;
    }
}
