package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceEvenement {
    private static ServiceEvenement instance;
    private ConnectionRequest req;
    
    private ServiceEvenement() {
        req = new ConnectionRequest();
    }
    
    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
        
    public boolean addEvenement(evenement e) {
        String url = Statics.BASE_URL +"/evenement/Addjson?" + "nomEvent=" + e.getNom_event()+ "&typeEvent=" + e.getType_event() + "&descriptionEvent=" + e.getDescription_event()+ "&nbParticipants=" + e.getNbrparticipants();
        req.setUrl(url); 
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<evenement> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<evenement> evenements = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            evenement a = new evenement();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idf = (int) Float.parseFloat(obj.get("id").toString());
            a.setId_event(idf);
            a.setNom_event(obj.get("nomEvent").toString());
            a.setType_event(obj.get("typeEvent").toString());
            a.setDescription_event(obj.get("descriptionEvent").toString());
            System.out.println(obj.get("nbParticipants"));
            int nb = (int) Float.parseFloat(obj.get("nbParticipants").toString());
            System.out.println(nb);
            a.setNbrparticipants(nb);

            
            evenements.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(evenements);
    return evenements;
}
public ArrayList<evenement> getAllEvenement() {
    String url = Statics.BASE_URL +"/evenement/AllEvenement";
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
    String url = Statics.BASE_URL +"/evenement/deleteEventJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierEvenement(evenement e){
//     "/annonces/updateannoncesJSON/" + l.getIds() + "?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
String url = Statics.BASE_URL + "/evenement/updateEventJSON/" + e.getId_event()+ "?nomEvent=" + e.getNom_event()+ "&typeEvent=" + e.getType_event() + "&descriptionEvent=" + e.getDescription_event()+ "&nbParticipants=" + e.getNbrparticipants() ;
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