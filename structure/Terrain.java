package structure;

import joueur.Joueur;

public class Terrain {
    private int dimension;
    private String[][] terrain;
    private char[] alphabet;

    public Terrain(int t) {
        alphabet = new char[t];
        dimension = t;
        terrain = new String[t][t];

        for(int i = 0; i < dimension; i++){

            for(int j = 0; j < dimension; j++) {
                terrain[i][j] = ".  ";
            }
        }

        int tmp =0;
        for (char a = 'A'; tmp < dimension; a++) {
            alphabet[tmp] = a;
            tmp++;
        }

    }

    public void affichageTerrain(){
        int tmp = dimension;

        System.out.print(" ");
        for (Character a : alphabet) {
            System.out.print("  " +a);
        }
        System.out.println();
        for(int i = 0; i < dimension; i++){
            System.out.print(tmp + "  ");
            for(int j = 0; j < dimension; j++) {
                System.out.print(terrain[i][j]) ;
            }
            System.out.println(tmp);
            tmp--;
        }

        System.out.print(" ");

        for (Character a : alphabet) {
            System.out.print("  " +a);
        }
        System.out.println();
    }

    public void placerPion(int a, int b, Joueur j){

    }

}
