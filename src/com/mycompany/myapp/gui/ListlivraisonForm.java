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
import com.mycompany.myapp.entites.livraison;
import com.mycompany.myapp.services.Servicelivraison;
import java.util.ArrayList;

/**
 *
 * @author imtinen
 */
public class ListlivraisonForm extends Form {
private Form previous;
public ListlivraisonForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des livraison");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<livraison> livraisons = Servicelivraison.getInstance().getAllLivraisons();
    
    for (livraison livraison : livraisons) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label type_livLabel = new Label("Type_liv : " + livraison.getType_liv());
        Label adress_livLabel = new Label("Adress_liv : " + livraison.getAdress_liv());
        Label produitLabel = new Label("Produit : " + livraison.getProduit());
     

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette livraison ?", "Oui", "Non")) {
        if (Servicelivraison.getInstance().deletelivraison(livraison.getId())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de la livraison", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierlivraisonForm(this, livraison).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(type_livLabel);
        container.add(adress_livLabel);
        container.add(produitLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddlivraisonForm(this).show());
}
}