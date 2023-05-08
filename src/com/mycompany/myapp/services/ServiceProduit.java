package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.entites.Produit;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceProduit {
    private static ServiceProduit instance;
    private ConnectionRequest req;
    
    private ServiceProduit() {
        req = new ConnectionRequest();
    }
    
    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
        
    public boolean addEvenement(Produit  e) {
        String url = Statics.BASE_URL +"/addStudentJSON/new?" + "nom=" + e.getNom_produit()+ "&imageName=" + e.getImage_produit()+ "&decription=" + e.getDescription_produit();
        req.setUrl(url); 
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Produit> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Produit> evenements = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Produit a = new Produit();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idf = (int) Float.parseFloat(obj.get("id").toString());
            a.setId_produit(idf);
            a.setNom_produit(obj.get("nom").toString());
          
            a.setImage_produit(obj.get("imageName").toString());
            a.setDescription_produit(obj.get("decription").toString());
          
            
            evenements.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(evenements);
    return evenements;
}
public ArrayList<Produit> getAllEvenement() {
    String url = Statics.BASE_URL +"/AllStudents";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parseAnnonces(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deleteEvenement(int idf) {
    String url = Statics.BASE_URL +  "/deleteStudentJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierEvenement(Produit e){
//     "/annonces/updateannoncesJSON/" + l.getIds() + "?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
String url = Statics.BASE_URL + "/updateStudentJSON/" + e.getId_produit()+ "?nom=" + e.getNom_produit()+ "&imageName=" + e.getImage_produit()+ "&decription=" + e.getDescription_produit();
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}