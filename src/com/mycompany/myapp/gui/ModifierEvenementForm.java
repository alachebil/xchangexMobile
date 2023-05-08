package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.gui.ListEvenementForm;
import com.mycompany.myapp.services.ServiceEvenement;

public class ModifierEvenementForm extends Form {
    private Form previous;
    private evenement annoncef;

    private TextField tfnom;
    private TextField tftype;
    private TextField tfdescription;
    private TextField tfnbpart;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierEvenementForm(Form previous, evenement annoncef) {
        super("Modifier evenement", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tfnom = new TextField(annoncef.getNom_event(), "Nom du evenement");
        tftype = new TextField(annoncef.getType_event(), "type du evenement");
       
        tfdescription = new TextField(String.valueOf(annoncef.getDescription_event()), "Description du evenement");
        tfnbpart = new TextField(String.valueOf(annoncef.getNbrparticipants()), "nombre participant");

       
        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setNom_event(tfnom.getText());
            annoncef.setType_event(tftype.getText());
            annoncef.setDescription_event(tfdescription.getText());
            annoncef.setNbrparticipants(Integer.parseInt(tfnbpart.getText()));
            if (ServiceEvenement.getInstance().modifierEvenement(annoncef)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListEvenementForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListEvenementForm(previous).show());
        addAll(tfnom, tftype,tfdescription,tfnbpart,btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

