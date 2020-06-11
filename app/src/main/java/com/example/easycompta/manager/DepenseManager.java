package com.example.easycompta.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.easycompta.entities.Depense;
import com.example.easycompta.services.ConnexionBd;

import java.util.ArrayList;
import java.util.List;

public class DepenseManager {

    // requete pour recuperer toutes les depenses
    private final static String queryGetAll = "select * from depense";

    // recuperation de toutes les depenses depuis la bd
    public static List<Depense> getAll(Context ctx) {
        ArrayList<Depense> depenses = new ArrayList<>();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            depenses.add(new Depense(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6)));
        }
        return depenses;
    }

    // requete pour avoir les depenses payees
    private final static String queryGetAll2 = "select * from depense where statut = 'Payé'";

    // recuperation de toutes les depenses payees depuis la bd
    public static List<Depense> getDepensesPayeeAuxFournisseurs(Context ctx) {
        ArrayList<Depense> depenses = new ArrayList<>();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        Cursor cursor = bd.rawQuery(queryGetAll2, null);
        while (cursor.moveToNext()) {
            depenses.add(new Depense(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6)));
        }
        return depenses;
    }

    // Add new depense
    public static Boolean addDepense(Depense depense, Context ctx) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // cv.put("id", vente.getId());
        cv.put("date", depense.getDate());
        cv.put("description", depense.getDescription());
        cv.put("montant", depense.getMontant());
        cv.put("quantite", depense.getQuantite());
        cv.put("fournisseur", depense.getFournisseur());
        cv.put("statut", depense.getStatut());
        long resultat = bd.insert("depense", null, cv);
        ConnexionBd.closeBd();
        if(resultat ==-1) return false;
        else return true;

    }

    // modification d'une depense
    public static void updateDepense(Depense depense, Context ctx){
        ContentValues value = new ContentValues();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        value.put("date", depense.getDate());
        value.put("description", depense.getDescription());
        value.put("montant", depense.getMontant());
        value.put("fournisseur", depense.getFournisseur());
        value.put("statut", depense.getStatut());
        bd.update("depense",value,"id"+"=?",new String[]{String.valueOf(depense.getId())});
    }

    // suppression depense de la bd
    public static void deleteDepense(int id,Context ctx){
        SQLiteDatabase bd=ConnexionBd.getBd(ctx);
        bd.delete("depense","id"+"=?",new String[]{String.valueOf(id)});
        ConnexionBd.closeBd();

    }

    // calcul depenses totales payees et non payees
    public static double calculMontantTotal(Context ctx){
        ArrayList<Double> listMontant = new ArrayList<>();
        ArrayList<Depense>depenses = (ArrayList<Depense>) getAll(ctx);
        double montantTotal = 0.0;
        for(int i=0; i<depenses.size(); i++){
            listMontant.add(depenses.get(i).getMontant());
            montantTotal += listMontant.get(i);
        }
        return montantTotal;
    }

    // calcul depenses totales payees
    public static double calculDepensesTotalesPayees(Context ctx){
        ArrayList<Double> listMontant = new ArrayList<>();
        ArrayList<Depense>depenses = (ArrayList<Depense>) getDepensesPayeeAuxFournisseurs(ctx);
        double montantTotal = 0.0;
        for(int i=0; i<depenses.size(); i++){
            listMontant.add(depenses.get(i).getMontant());
            montantTotal += listMontant.get(i);
        }
        return montantTotal;
    }

    public static int calculQuantiteTotaleAchete(Context ctx){
        ArrayList<Integer> listQuantite = new ArrayList<>();
        ArrayList<Depense>depenses = (ArrayList<Depense>) getAll(ctx);
        int quantiteTotal = 0;
        for(int i=0; i<depenses.size(); i++){
            listQuantite.add(depenses.get(i).getQuantite());
            quantiteTotal += listQuantite.get(i);
        }
        return quantiteTotal;
    }
}
