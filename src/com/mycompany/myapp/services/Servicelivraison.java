package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.livraison;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Servicelivraison {
    private static Servicelivraison instance;
    private ConnectionRequest req;
    
    private Servicelivraison() {
        req = new ConnectionRequest();
    }
    
    public static Servicelivraison getInstance() {
        if (instance == null) {
            instance = new Servicelivraison();
        }
        return instance;
    }
        
    public boolean addlivraison(livraison l) {
        String url = Statics.BASE_URL + "/livraison/Addjson?type_liv=" + l.getType_liv() + "&adress_liv=" + l.getAdress_liv() + "&produit=" + l.getProduit() ;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<livraison> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<livraison> annonces = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            livraison a = new livraison();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int id = (int) Float.parseFloat(obj.get("id").toString());
            a.setId(id);
            a.setType_liv(obj.get("typeLiv").toString());
            a.setAdress_liv(obj.get("adressLiv").toString());
            a.setProduit(obj.get("produit").toString());
           
            
            annonces.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(annonces);
    return annonces;
}
public ArrayList<livraison> getAllLivraisons() {
    String url = Statics.BASE_URL + "/livraison/AllLivraisonnn";
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


public boolean deletelivraison(int idf) {
    String url = Statics.BASE_URL + "/livraison/deletelivraisonJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierlivraison(livraison l){
     
String url = Statics.BASE_URL + "/livraison/updateEventJSON/" + l.getId() + "?typeLiv=" + l.getType_liv() + "&adressLiv=" + l.getAdress_liv() + "&produit=" + l.getProduit() ;
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