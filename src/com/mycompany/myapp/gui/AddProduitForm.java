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
import com.mycompany.myapp.entites.Produit;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.services.ServiceProduit;

public class AddProduitForm extends Form {
   private Form previous;

    public AddProduitForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new Produit");
        
        
//        
//        TextField titre = new TextField("","nomEv");
//        titre.setUIID("TextFieldBlack");
//
//        TextField typeF = new TextField("","type ");
//        typeF.setUIID("TextFieldBlack");
//        
//         TextField descriptionF = new TextField("","description ");
//         descriptionF.setUIID("TextFieldBlack");

            

        TextField tfNom = new TextField("", "Produit name");
        TextField tftype = new TextField("", "image");
        TextField tfdescription = new TextField("", "description");
        TextField tfnbPart = new TextField("", "nb part");
        Button addAnnonceButton = new Button("Add produit");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tftype.getText().isEmpty() ||
                    tfdescription.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Produit evenement = new Produit(tfNom.getText(), tftype.getText(),
                            tfdescription.getText());
                        if (ServiceProduit.getInstance().addEvenement(evenement)) {
                            Dialog.show("Success", "Produit added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tftype, tfdescription, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      
