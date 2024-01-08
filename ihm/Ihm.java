package ihm;

import appli.IJoueur;
import joueur.*;
import structure.Terrain;
import java.util.Scanner;
import static java.lang.System.exit;

public class Ihm {
    private Terrain terrain;
    private Joueur joueur1;
    private Joueur joueur2;

    public Ihm(Terrain terrain) {
        this.terrain = terrain;
        // Par défaut, initialise les joueurs comme des ConsolePlayer
        joueur1 = new Joueur("black", new ConsoleJoueur("black"));
        joueur2 = new Joueur("white", new ConsoleJoueur("white"));
    }

    public void configurerJoueur(String couleur, String type) {
        IJoueur player;
        if (type.equals("console")) {
            player = new ConsoleJoueur(couleur);
        } else if (type.equals("random")) {
            player = new RandomJoueur(couleur);
        } else {
            throw new IllegalArgumentException("Type de joueur non reconnu");
        }

        if (couleur.equals("black")) {
            joueur1 = new Joueur(couleur, player);
        } else if (couleur.equals("white")) {
            joueur2 = new Joueur(couleur, player);
        } else {
            throw new IllegalArgumentException("Couleur de joueur non reconnue");
        }

        terrain.ajouterJoueur(joueur1, joueur2);
    }

    public void saisie() {
        Scanner sc = new Scanner(System.in);
        String commande;
        String[] mots;

        while (!terrain.partieFinie()) {
            commande = sc.nextLine();
            mots = commande.split("\\s+");

            if (mots[0].equals("player") && mots.length == 3) {
                configurerJoueur(mots[1], mots[2]);
            } else if(mots[0].equals("boardsize") && mots.length == 2) {
                int taille = Integer.parseInt(mots[1]);
                boolean check = terrain.boardSize(taille);
                System.out.println("=\n");
            } else if(mots.length == 3 && mots[1].equals("boardsize")) {
                int taille = Integer.parseInt(mots[2]);
                boolean check = terrain.boardSize(taille);
                System.out.println("= " + mots[0] + "\n");
            } else if(mots[0].equals("showboard")) {
                System.out.println("= \n");
                terrain.showBoard();
            } else if(mots.length == 2 && mots[1].equals("showboard")) {
                System.out.println("= " + mots[0] + "\n");
                terrain.showBoard();
            } else if(mots.length == 3 && mots[0].equals("play")) {
                Joueur joueurActuel = mots[1].equals("black") ? joueur1 : joueur2;
                String move = mots[2];
                boolean moveSuccess = joueurActuel.jouer(move, terrain);
                if (moveSuccess) {
                    System.out.println("=\n");
                } else {
                    System.out.println("Impossible de placer le pion");
                }
            } else if(mots[0].equals("quit")) {
                exit(0);
            } else if(mots.length == 2 && mots[1].equals("quit")) {
                System.out.println("= " + mots[0] + "\n");
                exit(0);
            } else {
                System.out.println("? Unknown command");
            }
        }
    }
}
