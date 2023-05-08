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

import com.mycompany.myapp.entites.service;
import com.mycompany.myapp.services.ServiceService;

public class AddServiceForm extends Form {
   private Form previous;

    public AddServiceForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new service");

        TextField tftitre = new TextField("", "service name");
        TextField tftype = new TextField("", "type");
        TextField tflieu = new TextField("", "lieu");
        TextField tfDesc = new TextField("", "Description");
        Button addAnnonceButton = new Button("Add service");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tftitre.getText().isEmpty() || tftype.getText().isEmpty() ||
                    tflieu.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        service  annonce = new service(tftitre.getText(), tftype.getText(),
                            tflieu.getText(), tfDesc.getText());
                        if (ServiceService.getInstance().addService(annonce)) {
                            Dialog.show("Success", "servie added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tftitre, tftype, tflieu, tfDesc, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      
