/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.echange;
import com.mycompany.myapp.utils.SMSSender;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class Serviceechange {
    private static Serviceechange instance;
    private ConnectionRequest req;
    
    private Serviceechange() {
        req = new ConnectionRequest();
    }
    
    public static Serviceechange getInstance() {
        if (instance == null) {
            instance = new Serviceechange();
        }
        return instance;
    }
        
    public boolean addEchange(echange e) {
        String url = Statics.BASE_URL +"/addEchangeJSON/new?" + "username=" + e.getUsername()+ "&dateEchange=" + e.getDate_echange() + "&lieuEchange=" + e.getLieu_echange()+ "&typeEchange=" + e.getType_echange();
        req.setUrl(url); 
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        SMSSender SS ; 
        SS = new SMSSender();
        SS.SMSSender();
        return req.getResponseCode() == 200;
        
    }
 
public ArrayList<echange> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<echange> echanges = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            echange a = new echange();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idf = (int) Float.parseFloat(obj.get("id").toString());
            a.setId_echange(idf);
            a.setUsername(obj.get("username").toString());
            a.setDate_echange(obj.get("dateEchange").toString());
            a.setLieu_echange(obj.get("lieuEchange").toString());
            a.setType_echange(obj.get("typeEchange").toString());
          
            
            echanges.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(echanges);
    return echanges;
}
public ArrayList<echange> getAllEchange() {
    String url = Statics.BASE_URL +"/AllEchanges";
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


public boolean deleteEchange(int idf) {
    String url = Statics.BASE_URL +  "/deleteEchangeJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierEchange(echange e){
//     "/annonces/updateannoncesJSON/" + l.getIds() + "?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
String url = Statics.BASE_URL + "/updateEchangeJSON/" + e.getId_echange()+ "?username=" + e.getUsername()+ "&dateEchange=" + e.getDate_echange() + "&lieuEchange=" + e.getLieu_echange()+"&typeEchange=" + e.getType_echange() ;
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
