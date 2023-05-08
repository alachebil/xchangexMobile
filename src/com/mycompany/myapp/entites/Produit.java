/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author sbekr
 */
public class Produit {
    private int id_produit ;
    private String nom_produit,image_produit,description_produit;
    private float prix_produit;
    

    public Produit() {
    }

    public Produit(String nom_produit, String image_produit, String description_produit) {
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.description_produit = description_produit;
    }
    

    public Produit(int id_produit, String nom_produit, String image_produit, String description_produit, float prix_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.description_produit = description_produit;
        this.prix_produit = prix_produit;
      
    }
    

    public Produit(String nom_produit, String image_produit, String description_produit, float prix_produit) {
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.description_produit = description_produit;
        this.prix_produit = prix_produit;
       
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public String getDescription_produit() {
        return description_produit;
    }

    public float getPrix_produit() {
        return prix_produit;
    }



    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }



    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", image_produit=" + image_produit + ", description_produit=" + description_produit + ", prix_produit=" + prix_produit +  '}';
    }
    
}