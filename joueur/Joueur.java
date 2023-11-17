package joueur;

public class Joueur {
    private boolean aPasser;
    private int nbPoints;
    private char c;

    public Joueur(String couleur){
        aPasser = false;
        nbPoints = 0;
        c = couleur.equals("blanc") ? 'O' : 'X';
    }

    public void setaPasser(){
        this.aPasser = true;
    }

    public void nePassePlus(){
        this.aPasser = false;
    }

    public void quite(){
        String couleur = (c=='O' ? "blanc" : "noir");
        System.out.println("Abandon de la partie du joueur " + couleur);
        System.exit(0);
    }

    public boolean getaPasser() {
        return aPasser;
    }



}
