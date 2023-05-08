package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.service;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceService {
    private static ServiceService instance;
    private ConnectionRequest req;
    
    private ServiceService() {
        req = new ConnectionRequest();
    }
    
    public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceService();
        }
        return instance;
    }
        
    public boolean addService(service l) {
        String url = Statics.BASE_URL + "/service/Addjson?titreService=" + l.getTitre_service() + "&typeService=" + l.getType_service() + "&lieuService=" + l.getLieu_service() + "&descriptionService=" + l.getDescription_service();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<service> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<service> annonces = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            service a = new service();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idf = (int) Float.parseFloat(obj.get("id").toString());
            a.setId_service(idf);
            a.setTitre_service(obj.get("titreService").toString());
            a.setType_service(obj.get("typeService").toString());
            a.setLieu_service(obj.get("lieuService").toString());
            a.setDescription_service(obj.get("descriptionService").toString());
            
            annonces.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(annonces);
    return annonces;
}
public ArrayList<service> getAllService() {
    String url = Statics.BASE_URL + "/service/AllService";
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


public boolean deleteService(int idf) {
    String url = Statics.BASE_URL + "/service/deleteServiceJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierService(service l){
     
String url = Statics.BASE_URL + "/service/updateServiceJSON/" + l.getId_service() + "?titreService=" + l.getTitre_service() + "&typeService=" + l.getType_service() + "&lieuService=" + l.getLieu_service() + "&descriptionService=" + l.getDescription_service();
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