package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.livraison;
import com.mycompany.myapp.services.Servicelivraison;

public class AddlivraisonForm extends Form {
   private Form previous;

    public AddlivraisonForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");

        TextField tfType_liv = new TextField("", "Livraison Type_liv");
        TextField tfAdress_liv = new TextField("", "Adress_liv");
        TextField tfProduit = new TextField("", "Produit");
        Button addAnnonceButton = new Button("Add livraison");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfType_liv.getText().isEmpty() || tfAdress_liv.getText().isEmpty() ||
                    tfProduit.getText().isEmpty() ) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        livraison livraison = new livraison(tfType_liv.getText(), tfAdress_liv.getText(),
                            tfProduit.getText());
                        if (Servicelivraison.getInstance().addlivraison(livraison)) {
                            Dialog.show("Success", "Livraison added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfType_liv, tfAdress_liv, tfProduit,  addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      
