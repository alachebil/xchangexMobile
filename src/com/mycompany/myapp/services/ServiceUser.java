package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUser {
    private static ServiceUser instance;
    private ConnectionRequest req;
    
    private ServiceUser() {
        req = new ConnectionRequest();
    }
    
    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
        
    public boolean addUser(User u) {       
        String url = Statics.BASE_URL + "/addUserJSON/new?nom=" + u.getNom() + "&prenom=" + u.getPrenom() + "&numtel=" + u.getNumTel()+ "&adresse=" + u.getAdresse() + "&email=" + u.getEmail() + "&role=\"[\"ROLE_USER\"]" + "&password=" + u.getPassword();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
    public ArrayList<User> parseUsers(String jsonText) throws ParseException {
        System.out.println("Début parsing");
        ArrayList<User> Users = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) UserListJson.get("root");
            for (Map<String, Object> obj : list) {
                User a = new User();
                
                int id =(int) Float.parseFloat(obj.get("id").toString());
                //int numtel = (int) Float.parseFloat(obj.get("numtel").toString());
                //System.out.println(numtel);
                a.setId(id);
                a.setNom(obj.get("nom").toString());
                a.setPrenom(obj.get("prenom").toString());
                System.out.println(obj.get("prenom").toString());
                a.setAdresse(obj.get("adresse").toString());
                /*a.setPassword(obj.get("password").toString());*/
                //a.setNumTel(numtel);
                a.setEmail(obj.get("email").toString());
                Users.add(a);
            }
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(Users);
        return Users;
    }
    
    public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL + "/Allusers";
        System.out.println(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        String response = new String(req.getResponseData());
        try {
            return parseUsers(response);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }






public boolean deleteUser(int id) {
    String url = Statics.BASE_URL + "/deleteuserJSON/" + id;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierUser(User u){
     
           String url = Statics.BASE_URL + "/updateuserJSON/"+ u.getId()+"?nom=" + u.getNom() + "&prenom=" + u.getPrenom()+ "&adresse=" + u.getAdresse() + "&email=" + u.getEmail() ;

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