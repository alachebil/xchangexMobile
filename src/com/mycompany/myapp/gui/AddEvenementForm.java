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
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class AddEvenementForm extends Form {
   private Form previous;
   
    public static final String ACCOUNT_SID ="AC3b79629f5713befa3d9d21934642db85";
    public static final String AUTH_TOKEN ="46e52816db547f848650f9269b7aa939";

    public AddEvenementForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new event");
        
        
//        
//        TextField titre = new TextField("","nomEv");
//        titre.setUIID("TextFieldBlack");
//
//        TextField typeF = new TextField("","type ");
//        typeF.setUIID("TextFieldBlack");
//        
//         TextField descriptionF = new TextField("","description ");
//         descriptionF.setUIID("TextFieldBlack");

            

        TextField tfNom = new TextField("", "event name");
        TextField tftype = new TextField("", "type");
        TextField tfdescription = new TextField("", "description");
        TextField tfnbPart = new TextField("", "nb part");
        Button addAnnonceButton = new Button("Add announce");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tftype.getText().isEmpty() ||
                    tfdescription.getText().isEmpty()||tfnbPart.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        int numeros = Integer.parseInt(tfnbPart.getText().trim());
                        evenement evenement = new evenement(tfNom.getText(), tftype.getText(),
                            tfdescription.getText(),numeros);
                        if (ServiceEvenement.getInstance().addEvenement(evenement)) {
                            Dialog.show("Success", "Announcement added", new Command("OK"));
//                            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+21696212001"),
//                new com.twilio.type.PhoneNumber("+13184966588"),
//                "salut , l evenement avec nom "+tfNom.getText()+"est ajouté avec succée")
//            .create();
//
//        System.out.println(message.getSid());
                            
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tftype, tfdescription,tfnbPart,addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      

