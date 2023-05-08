/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import com.mycompany.myapp.entites.service;
import com.mycompany.myapp.services.ServiceService;
import java.util.ArrayList;

/**
 *
 * @author imtinen
 */
public class ListServiceForm extends Form {
private Form previous;
public ListServiceForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des services");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<service> annonces = ServiceService.getInstance().getAllService();
    
    for (service annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label titreLabel = new Label("titre : " + annonce.getTitre_service());
        Label typeLabel = new Label("type : " + annonce.getType_service());
        Label lieuLabel = new Label("lieu : " + annonce.getLieu_service());
        Label descriptionLabel = new Label("Description : " + annonce.getDescription_service());

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette service ?", "Oui", "Non")) {
        if (ServiceService.getInstance().deleteService(annonce.getId_service())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'service", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierServiceForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(titreLabel);
        container.add(typeLabel);
        container.add(lieuLabel);
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddServiceForm(this).show());
}
}