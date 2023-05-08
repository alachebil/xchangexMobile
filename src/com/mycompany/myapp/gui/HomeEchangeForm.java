/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author msi
 */
public class HomeEchangeForm extends Form {
    Resources res;
    Form current;

 
    public HomeEchangeForm(){
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
       
       
       setTitle("Gestion des Echanges des Clients");
       setLayout(BoxLayout.y());
       
       add(new Label("choisir une option"));
       Button btnAddechange = new Button("Add Echange");
       Button btnListechange = new Button("List Echange") ;
       Button btnModifierechange = new Button ("Modifier");
         btnAddechange.addActionListener(e-> new AddEchangeForm(current).show());
        btnListechange.addActionListener(e->new ListEchangeForm(current).show());
       //  btnModifierechange.addActionListener(e->new ModiferechangeForm(current).show());
         addAll(btnAddechange,btnListechange);
       
       
    }

 
      
}
