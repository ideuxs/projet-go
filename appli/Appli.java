package appli;

import ihm.Ihm;
import joueur.ConsoleJoueur;
import joueur.Joueur;
import joueur.RandomJoueur;
import structure.Terrain;

public class Appli {
    public static void main(String[] args) {
        Terrain terrain = new Terrain();
        Ihm ihm = new Ihm(terrain);

        // Initialisation des joueurs
        // Vous pouvez les initialiser ici ou permettre à IHM de les configurer
        // basé sur les commandes de l'utilisateur
        Joueur joueur1 = new Joueur("black", new ConsoleJoueur("black"));
        Joueur joueur2 = new Joueur("white", new RandomJoueur("white"));
        terrain.ajouterJoueur(joueur1, joueur2);

        // Démarrer l'interface de l'utilisateur
        ihm.saisie();
    }
}