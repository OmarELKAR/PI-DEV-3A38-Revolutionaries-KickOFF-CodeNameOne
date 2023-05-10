/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.Panier;
import entities.Produit;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import util.SessionManager;
import util.Statics;

/**
 *
 * @author omare
 */
public class servicePanier {
    ConnectionRequest req;
    static servicePanier instance = null;
    
    boolean resultOK = false;
    
    private servicePanier(){
        req = new ConnectionRequest();
    }
    
    public static servicePanier getInstance() {
        if (instance == null) {
            instance = new servicePanier();
        }
        return instance;
    }
    
    public boolean addCommande(){
        String addURL = Statics.BASE_URL + "/paniermob";
        
        req.setUrl(addURL);
        req.setPost(false);
        
        //req.addArgument("id_user", SessionManager.pref.get("id", "id"));
        req.addArgument("price", String.valueOf(Panier.getToatl()));
        int i = 0;
        for(Produit p : Panier.getProduits()){
            req.addArgument("id_"+i, String.valueOf(p.getId()));
            i++;
        }
        req.addArgument("num", String.valueOf(SessionManager.getPhonenumber()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                try {
                    Map<String,Object> Response = j.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
                    String ErrorMessage=(String)Response.get("Error");
                    if (!ErrorMessage.equals("OK")) {
                        Dialog.show("Success.",ErrorMessage,"OK",null);
                        }
                    else {
                        String succ=(String)Response.get("Response");
                        Dialog.show("Success.",succ,"OK",null);
                        
                    }
                    req.removeResponseListener(this);
                }catch(Exception exx)
                {
                System.out.println(exx.getMessage());
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
}
