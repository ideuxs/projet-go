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

        this.c = couleur.equals("white") ? "O" : "X";
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
        if(peutPlacer(t,i,j))
            t[i][j] = c;
        else
            System.out.println("pion adverse deja present");
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

    public boolean peutPlacer(String[][] t, int i, int j){
        return t[i-1][j] =="." || t[i][j-1] =="." || t[i+1][j] =="." || t[i][j+1] ==".";
    }

}
