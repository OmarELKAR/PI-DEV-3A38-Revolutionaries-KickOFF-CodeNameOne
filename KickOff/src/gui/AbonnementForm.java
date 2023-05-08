/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.Abonnement;
import entities.Type;
import services.serviceAbonnement;

/**
 *
 * @author Nadoura kaaboura
 */
public class AbonnementForm extends Form {
    public AbonnementForm (int id,int price){ Form form = new Form("Attributs", BoxLayout.y());

// Récupérer les attributs de l'objet Abonnement existant   

    
// Créer des champs de saisie pour chaque attribut
TextField terrainField = new TextField("","Terrain", 20, TextField.ANY);
TextField playerField = new TextField("", "Joueur", 20, TextField.ANY);
TextField startDateField = new TextField("", "Date de début", 20, TextField.ANY);
TextField endDateField = new TextField("", "Date de fin", 20, TextField.ANY);
TextField couponField = new TextField("", "Coupon", 20, TextField.ANY);


// Créer un bouton
Button abonnementButton = new Button("S'abonner");
serviceAbonnement SA=serviceAbonnement.getInstance();

// Ajouter un ActionListener pour appeler la fonction abonnement
abonnementButton.addActionListener(e -> {
    // Appeler la fonction abonnement avec les valeurs des champs de saisie
    String terrain = terrainField.getText();
    String player = playerField.getText();//Baddalnie
    String startDate = startDateField.getText();
    String endDate = endDateField.getText();
    String coupon = couponField.getText();
    if (coupon.isEmpty()){
    Abonnement abonnement = new Abonnement (terrain, player,price,startDate,endDate);

   
    SA.ajouterAbonnement(abonnement,id,"");
    Dialog.show("Success", "abonnement ajouté avec succés", "ok","cancel");}
    else {
            Abonnement abonnement = new Abonnement (terrain, player,price,startDate,endDate);
            SA.ajouterAbonnement(abonnement,id,coupon);
                Dialog.show("Success", "abonnement ajouté avec succés", "ok","cancel");

            

    }
    
    
});

// Ajouter le bouton à la forme
form.add(terrainField);
form.add(playerField);  
form.add(startDateField);
form.add(endDateField);
form.add(couponField);
form.add(abonnementButton);


form.show();

}


    
    }    
    

