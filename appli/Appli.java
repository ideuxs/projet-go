package appli;
import joueur.Joueur;
import structure.Terrain;

import java.util.Scanner;

public class Appli {
    public static void main(String [] args){
        Terrain t = new Terrain(9);

        Scanner sc = new Scanner(System.in);

        t.affichageTerrain();

        Joueur j = new Joueur("blanc");

        System.out.println("Saisir ce que tu souhaites");

    }
}
