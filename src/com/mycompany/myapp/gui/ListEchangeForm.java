/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.mycompany.myapp.entites.echange;
import com.mycompany.myapp.services.Serviceechange;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class ListEchangeForm extends Form {
private Form previous;
public ListEchangeForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des Echanges");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<echange> annonces = Serviceechange.getInstance().getAllEchange();
    
    for (echange annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label nomLabel = new Label("Username : " + annonce.getUsername());
        Label dateLabel = new Label("Date : " + annonce.getDate_echange());
        Label lieuLabel = new Label("LieuEchange : " + annonce.getLieu_echange());
        Label typeLabel = new Label("TypeEchange : " + annonce.getType_echange());
        

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cet echange ?", "Oui", "Non")) {
        if (Serviceechange.getInstance().deleteEchange(annonce.getId_echange())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'echange", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierEchangeForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(dateLabel);
        container.add(lieuLabel);
        container.add(typeLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddEchangeForm(this).show());
}
}
