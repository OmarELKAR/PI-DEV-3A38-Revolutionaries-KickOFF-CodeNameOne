/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import entities.Panier;
import entities.Produit;
import java.util.ArrayList;
import services.ProduitService;

/**
 *
 * @author omare
 */
public class panierForm extends Form{
    ProduitService ps = ProduitService.getInstance();
    
    public panierForm(){
        super("Panier", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
        String panier = "Panier: ";
        for(Produit p : Panier.getProduits() ){
            panier += p.getNom() + ", ";
        }
        Label panierLabel = new Label(panier);
        this.add(panierLabel);
        Label totallabel = new Label("Total: " + String.valueOf(Panier.getToatl()));
        this.add(totallabel);
        Button clearButton = new Button("Vider panier");
        clearButton.addActionListener((evt) -> {
            Panier.setProduits(new ArrayList<>());
            Panier.setToatl(0.0);
            new panierForm().showBack();
        });
        this.add(clearButton);
        
        Button confirmButton = new Button("Confirmer panier");
        confirmButton.addActionListener((evt) -> {
            System.out.println("ajouter");
        });
        this.add(confirmButton);
    }
}
