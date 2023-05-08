package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.livraison;
import com.mycompany.myapp.gui.ListlivraisonForm;
import com.mycompany.myapp.services.Servicelivraison;

public class ModifierlivraisonForm extends Form {
    private Form previous;
    private livraison livraison;

    private TextField tftype_liv;
    private TextField tfadress_liv;
    private TextField tfproduit;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierlivraisonForm(Form previous, livraison livraison) {
        super("Modifier livraison", BoxLayout.y());
        this.previous = previous;
        this.livraison = livraison;

        tftype_liv = new TextField(livraison.getType_liv(), "Nom du livraison");
        tfadress_liv = new TextField(livraison.getAdress_liv(), "Adress_liv du livraison");
        tfproduit = new TextField(String.valueOf(livraison.getProduit()), "Produit du livraison");
        

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            livraison.setType_liv(tftype_liv.getText());
            livraison.setAdress_liv(tfadress_liv.getText());
            livraison.setProduit(tfproduit.getText());
           

            if (Servicelivraison.getInstance().modifierlivraison(livraison)) {
                Dialog.show("livraison modifiée", "Votre livraison a été modifiée avec succès", "OK", null);
                new ListlivraisonForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListlivraisonForm(previous).show());
        addAll(tftype_liv, tfadress_liv, tfproduit,  btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}
