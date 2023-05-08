/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Imtinen
 */
public class HomeForm extends Form {

    Resources res;
    Form current;

    public HomeForm() {
        current = this;
        if (MyApplication.theme == null) {
            System.out.println("MyApplication.theme est null");
        } else if (MyApplication.theme.getImage("1.jpg") == null) {
            System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
        } else {
            getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
        }

        setTitle("Mon application");
        setLayout(BoxLayout.y());

        setTitle("Gestion des evenements");
        setLayout(BoxLayout.y());

        add(new Label("choisir une option"));
        Button btnAddevenement = new Button("Add evenement");
        Button btnListevenement = new Button("List evenement");
        
         Button btnAddLivraison = new Button("Add livraison");
        Button btnListLivraison = new Button("List livraison");
        
        Button btnAddService = new Button("Add service");
        Button btnListService = new Button("List service");
        
        Button btnAddEchange = new Button("Add echange");
        Button btnListEchange = new Button("List echange");
        
        Button btnAddProduit = new Button("Add Produit");
        Button btnListProduit = new Button("List Produit");
        
        Button btnAdduser = new Button("Add user");
        Button btnListUser = new Button("List user");
        
        
//        Button btnModifierAnnoncef = new Button("Modifier");
        btnAddevenement.addActionListener(e -> new AddEvenementForm(current).show());
        btnListevenement.addActionListener(e -> new ListEvenementForm(current).show());
        
        btnAddLivraison.addActionListener(e -> new AddlivraisonForm(current).show());
        btnListLivraison.addActionListener(e -> new ListlivraisonForm(current).show());
        
        btnAddService.addActionListener(e -> new AddServiceForm(current).show());
        btnListService.addActionListener(e -> new ListServiceForm(current).show());
        
        btnAddEchange.addActionListener(e -> new AddEchangeForm(current).show());
        btnListEchange.addActionListener(e -> new ListEchangeForm(current).show());
        
        btnAddProduit.addActionListener(e -> new AddProduitForm(current).show());
        btnListProduit.addActionListener(e -> new ListProduittForm(current).show());
        
               btnAdduser.addActionListener(e -> new AddUserForm(current).show());
        btnListUser.addActionListener(e -> new ListUserform(current).show());
        
        //  btnModifierAnnoncef.addActionListener(e->new ModiferAnnoncefForm(current).show());
//        Toolbar tb = getToolbar();
//       tb.addMaterialCommandToSideMenu("Map", FontImage.MATERIAL_EXIT_TO_APP, e -> new MapForm());
        Button btnmap = new Button("voir position");
        btnmap.addActionListener(e -> new MapForm());

        addAll(btnAddevenement, btnListevenement,btnAddLivraison,btnListLivraison,btnAddService,btnListService,btnAddEchange,btnListEchange,btnAddProduit,btnListProduit,btnAdduser,btnListUser,btnmap);


    }

}
