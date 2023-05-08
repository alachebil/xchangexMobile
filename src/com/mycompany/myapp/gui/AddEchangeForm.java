/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.echange;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.Serviceechange;

/**
 *
 * @author msi
 */
public class AddEchangeForm extends Form {
   private Form previous;

    public AddEchangeForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");

        TextField tfNom = new TextField("", "Username");
        TextField tfDate = new TextField("", "DateEchange");
        TextField tfLieu = new TextField("", "LieuEchange");
        TextField tfType = new TextField("", "TypeEchange");
        Button addEchangeButton = new Button("Add Echange");

        addEchangeButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfDate.getText().isEmpty() ||
                    tfLieu.getText().isEmpty() || tfType.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        echange annonce = new echange(tfNom.getText(), tfDate.getText(),
                            tfLieu.getText(), tfType.getText());
                        if (Serviceechange.getInstance().addEchange(annonce)) {
                            Dialog.show("Success", "Echange ajouté", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tfDate, tfLieu, tfType, addEchangeButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
   
    
}
