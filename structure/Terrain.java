package structure;

import joueur.Joueur;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Terrain {
    private int dimension;
    private boolean start;
    private String[][] terrain;
    private char[] alphabet;
    private Joueur[] joueurs;

    private ArrayList<Coord> estDisponible;
    public record Coord(int x, int y) {}

    public Terrain() {
        estDisponible = new ArrayList<>();
        dimension = 19;
        initialiserTerrain(dimension); // Initialisation initiale du terrain
    }

    public void ajouterJoueur(Joueur j, Joueur j2) {
        joueurs = new Joueur[2];
        joueurs[0] = j;
        joueurs[1] = j2;
    }

    public boolean boardSize(int dim) {
        if (!(dim > 4 && dim <= 19)) {
            return false;
        }
        initialiserTerrain(dim); // Réinitialisation du terrain avec la nouvelle dimension
        return true;
    }

    private void initialiserTerrain(int dim) {
        terrain = new String[dim][dim];
        for (int i = dim - 1; i >= 0; i--) {
            for (int j = dim - 1; j >= 0; j--) {
                terrain[i][j] = ".";
            }
        }
        dimension = dim;
        alphabet = new char[dimension];
        for (int i = 0; i < dimension; i++) {
            alphabet[i] = (char)('A' + i);
        }
        start = true; // Indique que le terrain a été initialisé
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

    public Joueur getJoueur(String s){
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

    public boolean peutPoser(int i, int j){
        for (Coord c:estDisponible) {
            if (c.x == i && c.y == j){
                System.out.println("N'a pas le droit de poser ici");
                return false;
            }
        }
        return true;
    }

    /* Placement des pions dans la grille
     * par joueur successivement
     */
    public boolean placerPion(String name, String chaine) {
        Joueur j = getJoueur(name);
        Joueur jPasSonTour = getJoueurPasSonTour(j);
        char ch = chaine.charAt(0);
        if (Character.isUpperCase(ch) && chaine.length() > 1) {
            int ab = getIndice(ch); // indices des lignes
            int a = chaine.length() > 2 ? Integer.parseInt(chaine.substring(1)) - 1
                    : Character.getNumericValue(chaine.charAt(1)) - 1; // indices des colonnes
            if (!estOccupee(a, ab) && peutPoser(a,ab)) {
                if(countLiberties(terrain,a,ab)!=0){
                    j.placerPoint(terrain, a, ab, jPasSonTour);
                    recupererPion(j);
                    return true;
                }

            }
        }
        return false;
    }

    private Joueur getJoueurPasSonTour(Joueur j) {
        if(joueurs[0].getCouleur().equals(j.getCouleur())){
            return joueurs[1];
        }

        return joueurs[0];
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
                    estDisponible.add(new Coord(i,j));
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


    public boolean estOccupee(int i, int j) {
        return !(terrain[i][j].equals("."));
    }

    /*
     * Si les deux joueurs abandonnent successivement
     * alors il y'a fin de partie.
     */
    public char[] getAlphabet() {
        return alphabet;
    }

    public boolean partieFinie() {
        return joueurs[0].getaPasser() && joueurs[1].getaPasser();
    }

    public int getDimension() {
        return dimension;
    }

    public boolean peutEncoreJouer(String couleur) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!estOccupee(i, j) && !estDansListeEstDisponible(i, j) && countLiberties(terrain, i, j) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean estDansListeEstDisponible(int i, int j) {
        return estDisponible.contains(new Coord(i, j));
    }


}