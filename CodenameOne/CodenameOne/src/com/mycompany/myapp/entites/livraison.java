/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author imtinen
 */
public class livraison {
    int id,idu;
    String type_liv,adress_liv,produit;
    
    
    public livraison(){}

    public livraison(int id, String type_liv) {
        this.id = id;
        this.type_liv = type_liv;
    }
    
   
    public livraison(int id ,String type_liv, String adress_liv, String produit) {
        this.id = id;
        this.type_liv = type_liv;
        this.adress_liv = adress_liv;
        this.produit = produit;
       
  
    }

 public livraison(String type_liv, String adress_liv, String produit) {
        this.type_liv = type_liv;
        this.adress_liv = adress_liv;
        this.produit = produit;
       

        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_liv() {
        return type_liv;
    }

    public void setType_liv(String type_liv) {
        this.type_liv = type_liv;
    }

    public String getAdress_liv() {
        return adress_liv;
    }

    public void setAdress_liv(String adress_liv) {
        this.adress_liv = adress_liv;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    


    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIdu() {
        return idu;
    }
    @Override
    public String toString() {
        return "livraison{" + "type_liv=" + type_liv + ", adress_liv=" + adress_liv + ", produit=" + produit + '}';
    }



  

    
}
