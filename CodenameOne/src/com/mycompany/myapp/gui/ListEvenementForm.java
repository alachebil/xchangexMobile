/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author imtinen
 */
public class ListEvenementForm extends Form {

    private Form previous;

    public ListEvenementForm(Form previous) {
        this.previous = previous;
        setTitle("Liste des evenement");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<evenement> annonces = ServiceEvenement.getInstance().getAllEvenement();

        for (evenement annonce : annonces) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

            Label nomLabel = new Label("Nom : " + annonce.getNom_event());
            Label adresseLabel = new Label("type : " + annonce.getType_event());
            Label descriptionLabel = new Label("Description : " + annonce.getDescription_event());
            Label nbpartLabel = new Label("nombre part : " + annonce.getNbrparticipants());
            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette evenement ?", "Oui", "Non")) {
                    if (ServiceEvenement.getInstance().deleteEvenement(annonce.getId_event())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'evenement", "Ok", null);
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
            container.add(nbpartLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);

            add(container);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new AddEvenementForm(this).show());
    }
    


}
