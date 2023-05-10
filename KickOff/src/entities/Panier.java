/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author omare
 */
public class Panier {
    
    private static List<Produit> produits;
    private static double toatl;




    public static List<Produit> getProduits() {
        return produits;
    }

    public static void setProduits(List<Produit> produits) {
        Panier.produits = produits;
    }

    public static double getToatl() {
        return toatl;
    }

    public static void setToatl(double toatl) {
        Panier.toatl = toatl;
    }
    
    public static void ajouterProduit(Produit p){
        Panier.produits.add(p);
    }
    
    public static void addTotal(double add){
        Panier.toatl += add;
    }
    
    
    
}
