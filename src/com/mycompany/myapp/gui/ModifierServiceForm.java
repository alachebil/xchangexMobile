/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.service;
import com.mycompany.myapp.services.ServiceService;

/**
 *
 * @author asus
 */
public class ModifierServiceForm extends Form{
    
    
     private Form previous;
    private service annoncef;

    private TextField tftitre;
    private TextField tftype;
    private TextField tfdescription;
    private TextField tflieu;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierServiceForm(Form previous, service annoncef) {
        super("Modifier annoncef", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tftitre = new TextField(annoncef.getTitre_service(), "Nom du service");
        tftype = new TextField(annoncef.getType_service(), "type du service");
        tflieu = new TextField(String.valueOf(annoncef.getLieu_service()), "lieu du service");
        tfdescription = new TextField(String.valueOf(annoncef.getDescription_service()), "description du service");
       

       
        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setTitre_service(tftitre.getText());
            annoncef.setType_service(tftype.getText());
            annoncef.setLieu_service(tflieu.getText());
            annoncef.setDescription_service(tfdescription.getText());
           
            if (ServiceService.getInstance().modifierService(annoncef)) {
                Dialog.show("Annonce modifiée", "Votre service a été modifiée avec succès", "OK", null);
                new ListServiceForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListServiceForm(previous).show());
        addAll(tftitre, tftype,tflieu,tfdescription,btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
    
    
    
    
    
}
