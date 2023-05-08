package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

public class ListUserform extends Form {
    private Form previous;

    public ListUserform(Form previous) {
        this.previous = previous;
        setTitle("Liste des Utilisateurs");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();

        for (User user : users) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label nomLabel = new Label("Nom: " + user.getNom());
            Label prenomLabel = new Label("Prénom: " + user.getPrenom());
            Label emailLabel = new Label("Email: " + user.getEmail());
            Label adresseLabel = new Label("Adresse: " + user.getAdresse());
            Label numTelLabel = new Label("Numéro de téléphone: " + user.getNumTel());
            Label passwordLabel = new Label("Adresse: " + user.getPassword());
            

            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cet utilisateur ?", "Oui", "Non")) {
                    if (ServiceUser.getInstance().deleteUser(user.getId())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'utilisateur", "Ok", null);
                    }
                }
            });

            Button buttonModifier = new Button("Modifier");
            buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
            buttonModifier.addActionListener(e -> new ModifierUserForm(ListUserform.this, user).show());


            container.add(nomLabel);
            container.add(prenomLabel);
            container.add(emailLabel);
            container.add(adresseLabel);
            container.add(numTelLabel);
            container.add(passwordLabel);

            
            container.add(buttonSupprimer);
            container.add(buttonModifier);

            add(container);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new AddUserForm(this).show());
    }
}
