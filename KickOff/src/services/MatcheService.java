/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Matche;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;


/**
 *
 * @author omare
 */
public class MatcheService {
    ConnectionRequest req;
    static MatcheService instance = null;
    
    boolean resultOK = false;
    List<Matche> matches = new ArrayList<>();
    
    private MatcheService(){
        req = new ConnectionRequest();
    }
    
    public static MatcheService getInstance() {
        if (instance == null) {
            instance = new MatcheService();
        }
        return instance;
    }
    
    public List<Matche> fetchMatches(){
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getMatches";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matches = parseMatches(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matches;
    }
    
    public List<Matche> parseMatches(String jsonText){
        matches = new ArrayList<>();
        
        JSONParser jp = new JSONParser();
        try {
            Map<String, Object> MatchesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) MatchesListJSON.get("root");
            
            for (Map<String, Object> item : list){
                Matche m = new Matche();
                m.setId((double) item.get("id"));
                m.setName((String) item.get("name"));
                m.setJmax((double) item.get("Jmax"));
                m.setDate((String) item.get("date"));
                m.setTime((String) item.get("time"));
                m.setEtat((String) item.get("etat"));
                
                matches.add(m);
            }
        }catch (IOException ex) {   
        }
        return matches;
    }
    
    public boolean addMatche (Matche m) {
        
        String addURL = Statics.BASE_URL + "/addMatche";
        
        req.setUrl(addURL);
        req.setPost(false);
        
        req.addArgument("name", m.getName());
        req.addArgument("Jmax", String.valueOf(m.getJmax()));
        req.addArgument("date", m.getDate());
        req.addArgument("time", m.getTime());
        req.addArgument("etat", m.getEtat());
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public List<Matche> showMatche (int id) {
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/showMatche";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addArgument("id","" + id);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matches = parseMatche(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matches;
    }
    
     public List<Matche> parseMatche(String jsonText){

        matches = new ArrayList<>();
        JSONParser jp = new JSONParser();
        try {
            Map<String, Object> MatchesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) MatchesListJSON.get("root");
            
            for (Map<String, Object> item : list){
                Matche m = new Matche();
                m.setId((double) item.get("id"));
                m.setName((String) item.get("name"));
                m.setJmax((double) item.get("Jmax"));
                m.setDate((String) item.get("date"));
                m.setTime((String) item.get("time"));
                m.setEtat((String) item.get("etat"));
                List<String> team1 = new ArrayList<>();
                for (Object o : (List<Object>) item.get("team1")) {
                    team1.add((String) o);
                }
                m.setTeam1(team1);

                List<String> team2 = new ArrayList<>();
                for (Object o : (List<Object>) item.get("team2")) {
                    team2.add((String) o);
                }
                m.setTeam2(team2);

                matches.add(m);
                }
        }catch (IOException ex) {   
        }
        return matches;
    }
}
