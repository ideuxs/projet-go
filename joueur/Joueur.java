package joueur;

public class Joueur {
    private boolean aPasser;
    private int nbPoints;
    private String c;
    private String couleur;

    public Joueur(String couleur){
        aPasser = false;
        nbPoints = 0;
        this.couleur = couleur;

        this.c = couleur.equals("blanc") ? "O" : "X";
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

    public void placerPoint(String[][] t, int i, int j){
        assert(t != null);
        t[i][j] = c + "  ";
    }
    private boolean verifPlacement(String[][] t, int i, int j){
        if(t[i-1][j] == null){
            return false;
        } else if (t[i][j-1]==null) {
            return false;
        } else if (t[i+1][j] == null) {
            return false;
        } else if (t[i][j+1] == null) {
            return false;
        }
        return true;
    }
    public void nePassePlus(){
        this.aPasser = false;
    }
    public void quite(){
        String couleur = (this.couleur.equals("O") ? "blanc" : "noir");
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



}
