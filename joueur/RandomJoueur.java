package joueur;

import appli.IJoueur;
import structure.Terrain;
import java.util.Random;

public class RandomJoueur implements IJoueur {
    private Random rand = new Random();
    private String couleur;

    public RandomJoueur(String couleur) {
        this.couleur = couleur;
    }


    @Override
    public boolean play(String move, Terrain terrain) {
        int dimension = terrain.getDimension();
        for (int attempts = 0; attempts < dimension * dimension; attempts++) {
            int i = rand.nextInt(dimension);
            int j = rand.nextInt(dimension);
            String position = terrain.getAlphabet()[j] + String.valueOf(i + 1);

            if (!terrain.estOccupee(i, j) && terrain.placerPion(couleur, position)) {
                return true;
            }
        }
        return false;
    }
}
