/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;


/**
 *
 * @author Imtinen
 */
public class HomeForm extends Form {
    Resources res;
    Form current;

 
    public HomeForm(){
        current=this;
           if (MyApplication.theme == null) {
    System.out.println("MyApplication.theme est null");
} else if (MyApplication.theme.getImage("1.jpg") == null) {
    System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
} else {
    getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
}

        setTitle("Mon application");
        setLayout(BoxLayout.y());
       
       
       setTitle("Gestion Produit");
       setLayout(BoxLayout.y());
       
       add(new Label("choisir une option"));
       Button btnAddAnnoncef = new Button("Add Produit");
       Button btnListAnnoncef = new Button("List Produit") ;
       Button btnModifierAnnoncef = new Button ("Modifier");
         btnAddAnnoncef.addActionListener(e-> new AddProduitForm(current).show());
        btnListAnnoncef.addActionListener(e->new ListProduittForm(current).show());
       //  btnModifierAnnoncef.addActionListener(e->new ModiferAnnoncefForm(current).show());
         addAll(btnAddAnnoncef,btnListAnnoncef);
       
       
    }

 
   
}
