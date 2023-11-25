package appli;
import ihm.Ihm;
import joueur.Joueur;
import structure.Terrain;

import java.util.Scanner;

public class Appli {
    public static void main(String [] args){
        Terrain t = new Terrain();
        Joueur j1 = new Joueur("noir");
        Joueur j2 = new Joueur("blanc");
        t.ajouterJoueur(j1,j2);
        Ihm ihm = new Ihm(t);
        ihm.saisie();
   }
}
