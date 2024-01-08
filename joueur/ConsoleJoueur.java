package joueur;

import structure.Terrain;
import appli.IJoueur;

public class ConsoleJoueur implements IJoueur {
    private String couleur;

    public ConsoleJoueur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public boolean play(String move, Terrain terrain) {
        return terrain.placerPion(couleur, move);
    }
}
