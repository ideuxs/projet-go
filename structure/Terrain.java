package structure;

import joueur.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terrain {

    private int dimension;
    private boolean start;
    private String[][] terrain;
    private char[] alphabet;
    private Joueur[] joueurs;

    public Terrain() {
        dimension = 19;
        boardSize(dimension);
    }

    public void ajouterJoueur(Joueur j, Joueur j2){
        joueurs = new Joueur[2];
        joueurs[0] = j;
        joueurs[1] = j2;
    }

    public boolean boardSize(int dim){
        if (!(dim > 4 && dim <= 19)) {
            return false;
        }
        if(!start){
            terrain = new String[dim][dim];
            for(int i = dim - 1; i >= 0; i--){
                for(int j = dim - 1; j >= 0; j--) {
                    terrain[i][j] = ".";
                }
            }
            dimension = dim;
            alphabet = new char[dimension];
            int tmp =0;
            for (char a = 'A'; tmp < dim; a++) {
                alphabet[tmp] = a;
                ++tmp;
            }
            return true;
        }
        return false;
    }



    public void showBoard(){
        int tmp = dimension;
        System.out.print("  ");
        for (Character a : alphabet) {
            System.out.print(" " +a);
        }
        System.out.println();
        for(int i = dimension -1 ; i >= 0; i--){
            if (tmp >= 10)
                System.out.print(tmp + " ");
            else
                System.out.print(tmp + "  ");

            for(int j = 0; j < dimension; j++) {
                System.out.print(terrain[i][j] + " ") ;
            }
            System.out.print(tmp);
            if(i==2){
                System.out.print("         WHITE (O) has captured " + joueurs[0].getNbPoints() +" stones");
            } if(i==1){
                System.out.println("         BLACK (X) has captured " + joueurs[1].getNbPoints() +" stones");
            }
            else{
                System.out.println();
            }

            tmp--;
        }
        System.out.print("  ");
        for (Character a : alphabet) {
            System.out.print(" " +a);
        }
        System.out.println();
    }




    /*
     *  Retourne l'indice du caractère saisi
     *  par un des 2 joueurs, par exemple F3
     *  elle retournera 6 - 1 (car l'indice commence à 0)
     */
    private int getIndice(char s){
        for (int i = 0; i < alphabet.length; i++) {
            if(alphabet[i] == s){
                return i;
            }
        }
        return -1;
    }

    private Joueur getJoueur(String s){
        for(Joueur j : joueurs){
            if(j.getCouleur().equals(s)){
                return j;
            }
        }
        return null;
    }

    /*
     * Verifier si les cases voisines entourent un pion
     * Puis verifier si un groupe entoure un pion
     * */
    private boolean estEntourer(int i, int j, String couleur){

        return false;
    }

    /*Savoir si un pion est dans une bordure
     * afin de faciliter son
     */
    private boolean estDansBordure(int i, int j){
        int taille_min = 0;
        if(i-1 < taille_min && j-1 < taille_min){return true;} //en bas a gauche
        if(i+1 > dimension-1 && j+1 > dimension-1){return true;} //en haut a droite
        if(i+1 > dimension-1 && j-1 < taille_min){return true;} //en haut a gauche
        if(i-1 < taille_min && j+1 > dimension-1){return true;} // en bas a droite
        if(i+1>dimension){return true;}
        if(i-1 < taille_min){return true;}
        if(j-1 < taille_min){return true;}
        return j + 1 > dimension;
    }

    /* Placement des pions dans la grille
     * par joueur successivement
     */
    public boolean placerPion(String name, String chaine){
        start = true;
        Joueur j = getJoueur(name);
        char ch = chaine.charAt(0);
        if(Character.isUpperCase(ch) && chaine.length() >1){
            int ab = getIndice(ch);  // indices des lignes

            int a = 0;
            if(chaine.length() >2){
                String sousChaine = chaine.substring(1, chaine.length());
                a = Integer.parseInt(sousChaine)-1;
            }else{
                a = Character.getNumericValue(chaine.charAt(1))-1 ;  // indices des colonnes
            }
            boolean check = estOccupee(a,ab);
            if(!check) {
                j.placerPoint(terrain, a, ab);
                recupererPion();
            }
        }else{
            return false;
        }
        return true;
    }

    private void ajoutPoints(String couleur){
        if(couleur.equals(joueurs[0].getCouleur())){ joueurs[0].ajouterPoint(); }
        else{ joueurs[1].ajouterPoint(); }
    }

    /* Récuperer pion qui n'est pas en bordure de grille
     *  et si le pion est en bordure alors appel de la méthode
     *  recupererPionBordure()
     * */
    public void recupererPion(){
        for (int i =0; i < dimension - 1; ++i){
            for (int j = 0; j < dimension-1; j++) {
                if(!estDansBordure(i,j)){
                    for (Joueur player: joueurs) {
                        if (terrain[i][j].equals(player.getChar())) {
                            if(!terrain[i-1][j].equals(player.getChar()) && !terrain[i][j-1].equals(player.getChar()) &&!terrain[i-1][j].equals(".") && !terrain[i][j-1].equals("."))
                                if(!terrain[i+1][j].equals(".") && !terrain[i][j+1].equals(".") && !terrain[i-1][j].equals(player.getChar()) && !terrain[i][j-1].equals(player.getChar())) {
                                    terrain[i][j] = ".";

                                    //mettre dans le tableau de boolean les coordonnees du pion qui ont ete retiré
                                    System.out.println("Pion retiré");
                                    ajoutPoints(player.getCouleur());
                                }


                        }
                    }
                }
                else {
                    recupererPionBordure();
                }
            }
        }

    }


    private void recupererPionBordure() {
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; j++) {

            }
        }
    }

//|| j-1 < size_min || i+1 > dimension-1 || j+1 > dimension-1 || i-1 < size_min && j-1 < size_min || i+1 > dimension-1 && j+1 > dimension-1){


    private boolean estOccupee(int i, int j){
        return !(terrain[i][j].equals("."));
    }


    /*
     * Si les deux joueurs abandonnent successivement
     * alors il y'a fin de partie.
     */
    public boolean partieFinie(){
        return joueurs[0].getaPasser() && joueurs[1].getaPasser();
    }



}
