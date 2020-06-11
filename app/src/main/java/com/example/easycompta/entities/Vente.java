package com.example.easycompta.entities;

public class Vente {
    int id;
    String date;
    String description;
    double montant;
    int quantite;
    String statut;
    String client;

    public Vente() {
    }

    public Vente(int id, String date, String description, double montant, int quantite, String client, String statut) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.montant = montant;
        this.quantite = quantite;
        this.client = client;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
