/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
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
//import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;
import java.util.Date;

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
            buttonModifier.addActionListener(e -> new ModifierEvenementForm(this, annonce).show());

            
             Button pdf=new Button("obtenir fiche pdf");
            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdf.getUnselectedStyle()));
           pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp/pdff/evenement" + annonce.getId_event() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        //        // Ajouter le logo  C:\xampp\htdocs
//       String logoPath = "file:///C:/xampp/htdocs/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
    

 DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String currentDate = dateFormat.format(new Date());

        
//        //        // Ajouter le logo
//       String logoPath = "file:///C:/xampp/htdocs/logo.png"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
//        
//       String imagePath = "file:///C:/xampp/htdocs/logo.png";
//    EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(imagePath), false);
//    Image logo = encodedImage.scaledEncoded(70, 70);

        document.open();
        document.add(new Paragraph("Date : " + currentDate));
        document.add(new Paragraph("Fiche de evenement"));
        document.add(new Paragraph("Nom :" + annonce.getNom_event()));
        document.add(new Paragraph("Type :" + annonce.getType_event()));
        document.add(new Paragraph("Description :" + annonce.getDescription_event()));
        document.add(new Paragraph("Nombre de participants :" + annonce.getNbrparticipants()));
        
        
        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
            
            
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
            container.add(pdf);

            add(container);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new AddEvenementForm(this).show());
    }
    


}
