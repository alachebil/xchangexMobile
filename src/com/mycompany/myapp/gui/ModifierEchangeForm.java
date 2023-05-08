/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.echange;
import com.mycompany.myapp.services.Serviceechange;

/**
 *
 * @author msi
 */
public class ModifierEchangeForm extends Form {
    private Form previous;
    private echange annoncef;

    private TextField tfnom;
    private TextField tftype;
    private TextField tfdate;
    private TextField tflieu;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierEchangeForm(Form previous, echange annoncef) {
        super("Modifier echange", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tfnom = new TextField(annoncef.getUsername(), "Nom du client");
        tfdate = new TextField(annoncef.getDate_echange(), "Date de l'echange");
        tflieu = new TextField(String.valueOf(annoncef.getLieu_echange()), "Lieu de l'echange");
        tftype = new TextField(annoncef.getType_echange(), "Type de l'echange");
        

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setUsername(tfnom.getText());
            annoncef.setDate_echange(tfdate.getText());
            annoncef.setLieu_echange(tflieu.getText());
            annoncef.setType_echange(tftype.getText());           
            

            if (Serviceechange.getInstance().modifierEchange(annoncef)) {
                Dialog.show("Echange modifiée", "Votre Echange a été modifiée avec succès", "OK", null);
                new ListEchangeForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListEchangeForm(previous).show());
        addAll(tfnom, tfdate,tflieu,tftype, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}
