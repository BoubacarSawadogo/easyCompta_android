package com.example.easycompta.entities;

public class Depense {
    int id;
    String date;
    String description;
    double montant;
    int quantite;
    String statut;
    String fournisseur;

    public Depense() {
    }

    public Depense(int id, String date, String description, double montant, int quantite, String fournisseur, String statut) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.montant = montant;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
