package com.example.easycompta.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.easycompta.entities.Depense;
import com.example.easycompta.entities.User;
import com.example.easycompta.entities.Vente;
import com.example.easycompta.services.ConnexionBd;

import java.util.ArrayList;
import java.util.List;

public class VenteManager {


    // This method help to get all users in database
    private final static String queryGetAll = "select * from vente";

    public static List<Vente> getAll(Context ctx) {
        ArrayList<Vente> ventes = new ArrayList<>();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            ventes.add(new Vente(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6)));
        }
        return ventes;
    }


    // get ventes where statut = paye (entree banque)
    private final static String queryGetAll2 = "select * from vente where statut = 'Payé'";

    public static List<Vente> getAllVentesPayees(Context ctx) {
        ArrayList<Vente> ventespayees = new ArrayList<>();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        Cursor cursor = bd.rawQuery(queryGetAll2, null);
        while (cursor.moveToNext()) {
            ventespayees.add(new Vente(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6)));
        }
        return ventespayees;
    }




    // Add new user
    public static Boolean addVente(Vente vente, Context ctx) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
       // cv.put("id", vente.getId());
        cv.put("date", vente.getDate());
        cv.put("description", vente.getDescription());
        cv.put("montant", vente.getMontant());
        cv.put("quantite", vente.getQuantite());
        cv.put("client", vente.getClient());
        cv.put("statut", vente.getStatut());
        long resultat = bd.insert("vente", null, cv);
        ConnexionBd.closeBd();
        if(resultat ==-1) return false;
        else return true;

    }

    public static void updateVente(Vente vente, Context ctx){
        ContentValues value = new ContentValues();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        value.put("date", vente.getDate());
        value.put("description", vente.getDescription());
        value.put("montant", vente.getMontant());
        value.put("client", vente.getClient());
        value.put("statut", vente.getStatut());
        bd.update("vente",value,"id"+"=?",new String[]{String.valueOf(vente.getId())});

    }
    public static void deleteVente(int id,Context ctx){
        SQLiteDatabase bd=ConnexionBd.getBd(ctx);
        bd.delete("vente","id"+"=?",new String[]{String.valueOf(id)});
        ConnexionBd.closeBd();

    }

    public static double calculMontantTotal(Context ctx){
        ArrayList<Double> listMontant = new ArrayList<>();
        ArrayList<Vente>ventes = (ArrayList<Vente>) getAll(ctx);
        double montantTotal = 0.0;

        for(int i=0; i<ventes.size(); i++){
            listMontant.add(ventes.get(i).getMontant());
            montantTotal += listMontant.get(i);
        }

        return montantTotal;

    }

    public static double calculMontantTotalPayeParClients(Context ctx){
        ArrayList<Double> listMontant = new ArrayList<>();
        ArrayList<Vente>ventes = (ArrayList<Vente>) getAllVentesPayees(ctx);
        double montantTotal = 0.0;

        for(int i=0; i<ventes.size(); i++){
            listMontant.add(ventes.get(i).getMontant());
            montantTotal += listMontant.get(i);
        }
        return montantTotal;
    }

    public static int calculQuantiteTotaleVendu(Context ctx){
        ArrayList<Integer> listQuantite = new ArrayList<>();
        ArrayList<Vente>ventes = (ArrayList<Vente>) getAll(ctx);
        int quantiteTotal = 0;
        for(int i=0; i<ventes.size(); i++){
            listQuantite.add(ventes.get(i).getQuantite());
            quantiteTotal += listQuantite.get(i);
        }
        return quantiteTotal;
    }
}
