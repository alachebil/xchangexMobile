package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Produit;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.gui.ListProduittForm;
import com.mycompany.myapp.services.ServiceProduit;

public class ModifierAnnoncefForm extends Form {
    private Form previous;
    private Produit annoncef;

    private TextField tfnom;
    private TextField tftype;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierAnnoncefForm(Form previous, Produit annoncef) {
        super("Modifier annoncef", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tfnom = new TextField(annoncef.getNom_produit(), "Nom du Produit");
        tftype = new TextField(annoncef.getImage_produit(), "Adresse du Produit");
       
        tfdescription = new TextField(String.valueOf(annoncef.getDescription_produit()), "Description du evenement");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setNom_produit(tfnom.getText());
            annoncef.setImage_produit(tftype.getText());
           
            annoncef.setDescription_produit(tfdescription.getText());

            if (ServiceProduit.getInstance().modifierEvenement(annoncef)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListProduittForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListProduittForm(previous).show());
        addAll(tfnom, tftype,tfdescription, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}
