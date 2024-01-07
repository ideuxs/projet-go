package structure;

import joueur.Joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Terrain {

    private int dimension;
    private boolean start;
    private String[][] terrain;
    private boolean[][] copieterrain;
    private char[] alphabet;
    private Joueur[] joueurs;
    private HashSet<Coord> visitees;
    public record Coord(int x, int y){};

    private int cpt;

    public Terrain() {
        dimension = 19;
        boardSize(dimension);

        visitees = new HashSet<>();
        cpt =0;
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
            copieterrain = new boolean[dimension][dimension];
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
                System.out.print("         BLACK (X) has captured " + joueurs[0].getNbPoints() +" stones");
            } if(i==1){
                System.out.println("         WHITE (O) has captured " + joueurs[1].getNbPoints() +" stones");
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
                recupererPion(j);
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
    public int recupererPion(Joueur joueur) {
        ArrayList<Coord> blackEntouree = new ArrayList<>();
        ArrayList<Coord> whiteEntouree = new ArrayList<>();

        // Step 1: Count liberties without removing stones
        int[][] libertiesCount = new int[dimension][dimension];
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; j++) {
                libertiesCount[i][j] = countLiberties(terrain, i, j);
            }
        }
        // Step 2: Remove surrounded stones and update player points
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; j++) {
                if (libertiesCount[i][j] == 0) {
                    if (terrain[i][j].equals("O")) {
                        whiteEntouree.add(new Coord(i, j));
                    } else if (terrain[i][j].equals("X")) {
                        blackEntouree.add(new Coord(i, j));
                    }

                    terrain[i][j] = ".";
                    System.out.println("Pion retiré");
                    //ajoutPoints(joueur.getCouleur());
                }
            }
        }
        // Adjust points based on surrounded stones
        if (joueur.getCouleur().equals("black")) {
            for (Coord c : whiteEntouree) {
                joueur.ajouterPoint();
            }
        } else if (joueur.getCouleur().equals("white")) {
            for (Coord c : blackEntouree) {
                joueur.ajouterPoint();
            }
        }
        return 0;
    }

    public static int countLiberties(String[][] board, int row, int col) {
        Set<String> visited = new HashSet<>();
        return countLibertiesRecursive(board, row, col, board[row][col], visited);
    }

    private static int countLibertiesRecursive(String[][] board, int row, int col, String group, Set<String> visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited.contains(row + "," + col)) {
            return 0;
        }

        visited.add(row + "," + col);
        if (board[row][col].equals(".")) {
            return 1;
        }
        if (!board[row][col].equals(group)) {
            return 0;
        }
        int liberties = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            liberties += countLibertiesRecursive(board, newRow, newCol, group, visited);
        }
        return liberties;
    }
    public int compteCaseVides(int i, int j){
       return compteCaseVoisines(i,j,".");
    }

    public int compteCaseVoisines(int i, int j, String s){
        int compteur = 0;
        if(terrain[i-1][j].equals(s)){
            compteur +=1;
        }
        if(terrain[i+1][j].equals(s)){
            compteur +=1;
        }
        if(terrain[i][j+1].equals(s)){
            compteur +=1;
        }if(terrain[i][j-1].equals(s)){
            compteur +=1;
        }
        return compteur;
    }


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

    public void auTourDe(){

    }
}
