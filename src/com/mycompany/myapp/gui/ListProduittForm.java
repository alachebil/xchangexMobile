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
import com.mycompany.myapp.entites.Produit;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author imtinen
 */
public class ListProduittForm extends Form {
private Form previous;
public ListProduittForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des Produit");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<Produit> annonces = ServiceProduit.getInstance().getAllEvenement();
    
    for (Produit annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label nomLabel = new Label("Nom : " + annonce.getNom_produit());
        Label adresseLabel = new Label(" path image : " + annonce.getImage_produit());
        Label descriptionLabel = new Label("Description : " + annonce.getDescription_produit());

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette Produit ?", "Oui", "Non")) {
        if (ServiceProduit.getInstance().deleteEvenement(annonce.getId_produit())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'Produit", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierAnnoncefForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(adresseLabel);
        
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddProduitForm(this).show());
}
}