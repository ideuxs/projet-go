package ihm;

import structure.Terrain;

import java.util.Scanner;

import static java.lang.System.exit;

public class Ihm {

    private Terrain t;
    public Ihm(Terrain t){
        this.t = t;
    }

    public void saisie(){
        Scanner sc = new Scanner(System.in);
        String commande;
        String [] mots;
        while(!t.partieFinie()){
            commande = sc.nextLine();
            mots = commande.split("\\s+");
            if(mots[0].equals("boardsize")&& mots.length == 2){
                int taille = Integer.parseInt(mots[1]);
                boolean check = t.boardSize(taille);
                System.out.println("=\n");

            }else if(mots.length== 3 &&mots[1].equals("boardsize")){
                int taille = Integer.parseInt(mots[2]);
                boolean check = t.boardSize(taille);
                System.out.println("= "+ mots[0] + "\n");
            }
            else if(mots[0].equals("showboard")){
                System.out.println("= \n");
                t.showBoard();
            } else if (mots.length == 2 && mots[1].equals("showboard") ) {
                System.out.println("= " + mots[0] + "\n");
                t.showBoard();
            } else if (mots.length == 3 && mots[0].equals("play")) {
                String name = mots[1];
                String param1 = mots[2];
                boolean check = t.placerPion(name, param1);
                System.out.println("=\n");
            } else if (mots.length == 4 && mots[1].equals("play")) {
                String name = mots[2];
                String param1 = mots[3];
                boolean check = t.placerPion(name, param1);
                System.out.println("= " + mots[0] + "\n");
            } else if(mots[0].equals("quit")){
                exit(0);
            } else if (mots.length == 2 && mots[1].equals("quit")) {
                System.out.println("= " + mots[0] + "\n");
                exit(0);
            } else{
                System.out.println("? Unknown command");
            }

        }
    }


}
