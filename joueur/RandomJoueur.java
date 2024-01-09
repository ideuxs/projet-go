package joueur;

import appli.IJoueur;
import structure.Terrain;
import java.util.Random;

public class RandomJoueur implements IJoueur {

    private boolean peutJouer;
    private Random rand = new Random();
    private String couleur;

    public RandomJoueur(String couleur) {
        this.peutJouer = couleur.equals("black");
        this.couleur = couleur;
    }


    @Override
    public boolean play(String move, Terrain terrain) {

        Joueur joueurActuel = terrain.getJoueur(couleur);
        if (joueurActuel != null && joueurActuel.getPeutJouer() && terrain.peutEncoreJouer(couleur)) {

            int dimension = terrain.getDimension();

            for (int attempts = 0; attempts < dimension * dimension; attempts++) {
                int i = rand.nextInt(dimension);
                int j = rand.nextInt(dimension);
                String position = terrain.getAlphabet()[j] + String.valueOf(i + 1);

                if (!terrain.estOccupee(i, j) && terrain.placerPion(couleur, position)) {
                    terrain.showBoard();
                    return true;
                }
            }}

        return false;
    }

    @Override
    public boolean estRandomJoueur() {
        return true;
    }
}
