package joueur;

import appli.IJoueur;
import structure.Terrain;


public class Joueur {
    private boolean aPasser;
    private int nbPoints;
    private String c;
    private String couleur;
    private IJoueur player;  // Ajout de la référence à PlayerInterface

    // Modification du constructeur pour inclure PlayerInterface
    public Joueur(String couleur, IJoueur player) {
        this.aPasser = false;
        this.nbPoints = 0;
        this.couleur = couleur;
        this.c = couleur.equals("white") ? "O" : "X";
        this.player = player; // Initialisation de PlayerInterface
    }

    public boolean jouer(String move, Terrain terrain) {
        return this.player.play(move, terrain);
    }

    public int getNbPoints(){
        return this.nbPoints;
    }

    public void setaPasser(){
        this.aPasser = true;
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void placerPoint(String[][] t, int i, int j) {
        assert(t != null);
        if (peutPlacer(t, i, j)) {
            t[i][j] = c; // Place le pion si la position est libre
        } else {
            System.out.println("pion adverse deja present");
        }
    }
    public void nePassePlus(){
        this.aPasser = false;
    }
    public void quite(){
        String couleur = (this.couleur.equals("O") ? "white" : "black");
        System.out.println("Abandon de la partie du joueur " + couleur);
        System.exit(0);
    }

    public void ajouterPoint(){
        this.nbPoints += 1;
    }

    public String getChar(){
        return c;
    }

    public boolean getaPasser() {
        return aPasser;
    }

    public boolean peutPlacer(String[][] t, int i, int j) {
        // Vérifie si la position ciblée est dans les limites du plateau et est libre
        return i >= 0 && i < t.length && j >= 0 && j < t[i].length && t[i][j].equals(".");
    }

}