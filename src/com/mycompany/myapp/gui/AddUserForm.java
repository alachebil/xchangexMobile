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
import com.mycompany.myapp.entites.Role;
import com.mycompany.myapp.entites.User;
import com.mycompany.myapp.services.ServiceUser;


public class AddUserForm extends Form {
    private Form previous;

    public AddUserForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new User");

        TextField tfNom = new TextField("", "Name");
        TextField tfPrenom = new TextField("", "Prenom");
        TextField tfAdresse = new TextField("", "Address");
        TextField tfEmail = new TextField("", "Email");
        TextField tfnumtel = new TextField("", "numtel");
        TextField tfpassword = new TextField("", "password");

        Button addUserButton = new Button("Sign up");

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfAdresse.getText().isEmpty()
                        || tfEmail.getText().isEmpty() || tfpassword.getText().isEmpty()
                        || tfpassword.getText().isEmpty() || tfnumtel.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        
                        User user = new User();
                        String numTelText = tfnumtel.getText();
                        Role role= Role.ROLE_USER;
                        int numTel = Integer.parseInt(numTelText);
                        user.setNom(tfNom.getText());
                        user.setPrenom(tfPrenom.getText());
                        user.setAdresse(tfAdresse.getText());
                        user.setEmail(tfEmail.getText());
                        user.setNumTel(numTel);
                        user.setPassword(tfpassword.getText());
                        user.setRole(role);
                        System.out.println(user);
                        if (ServiceUser.getInstance().addUser(user)) {
                            Dialog.show("Success", "User added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tfPrenom, tfAdresse, tfEmail, tfnumtel, tfpassword, addUserButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
}
