/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Matche;
import entities.Terrain;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author omare
 */
public class TerrainService {
    ConnectionRequest req;
    static TerrainService instance = null;
    
    boolean resultOK = false;
    List<Terrain> terrains = new ArrayList<>();
    
    private TerrainService(){
        req = new ConnectionRequest();
    }
    
    public static TerrainService getInstance() {
        if (instance == null) {
            instance = new TerrainService();
        }
        return instance;
    }
    
    public List<Terrain> fetchTerrains(){
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getTerrains";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt){
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains;
    }
    
    public List<Terrain> parseTerrains(String jsonText){
        terrains = new ArrayList<>();
        
        JSONParser jp = new JSONParser();
        try{
            Map<String, Object> terrainsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) terrainsListJSON.get("root");
            
            for(Map<String, Object> item : list){
                Terrain t = new Terrain();
                t.setId((double) item.get("id"));
                t.setNom((String) item.get("name"));
                t.setNbrJoueur((double) item.get("Jmax"));
                t.setLargeur((double) item.get("largeur"));
                t.setLongeur((double) item.get("longeur"));
                t.setPrix((double) item.get("prix"));
                t.setImgName((String) item.get("img"));
                t.setLocalisation((String) item.get("localisation"));
                t.getAddressFromCoordinates();
                
                terrains.add(t);
            }
        }catch(IOException ex){
            System.out.println("err");
        }
        return terrains;
    }
    
    public Terrain fetchTerrain(double id) {
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getTerrain";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addArgument("id","" + id);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains.get(0);
    }
   
    
}
