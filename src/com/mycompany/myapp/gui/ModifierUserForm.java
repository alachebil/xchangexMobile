package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.myapp.entites.User;
import com.mycompany.myapp.entites.evenement;

import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.gui.ListUserform;

public class ModifierUserForm extends Form {
    private Form previous;
    private User user;

    private TextField tfnom;
    private TextField tfprenom;
    private TextField tfadresse;
    private TextField tfemail;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierUserForm(Form previous, User user) {
        super("Modifier User", BoxLayout.y());
        this.previous = previous;
        this.user = user;

        tfnom = new TextField(user.getNom(), "nom user");
        tfprenom = new TextField(user.getPrenom(), "prenom user");
        tfemail = new TextField(user.getEmail(), "email user");
        tfadresse = new TextField(user.getAdresse(), "Adresse user");
       

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            user.setNom(tfnom.getText());
            user.setPrenom(tfprenom.getText());
            user.setAdresse(tfadresse.getText());
            user.setEmail(tfemail.getText());
            
           

            if (ServiceUser.getInstance().modifierUser(user)) {
                Dialog.show("User modifié", "user a  été modifiée avec succès", "OK", null);
                new ListUserform(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListUserform(previous).show());
        addAll(tfnom, tfemail,tfprenom,tfadresse , btnModifier, btnAnnuler);
     
    }

    
    public void show() {
        super.show();
    }
}
