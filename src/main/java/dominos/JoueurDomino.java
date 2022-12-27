package dominos;

import common.*;

public class JoueurDomino extends Joueur {
    /**
     * le score du joueur
     */
    private int score = 0;

    /**
     * Constructeur
     * @param p le plateau de jeu
     */
    JoueurDomino(Plateau p) {
        super.plateau = p;
    }
    
    @Override
    public void placerTuile(int x, int y) {
        plateau.placer(x, y, tuile);
        score += ((PlateauDomino) plateau).sommeCotesAdja(tuile);
    }

    @Override
    public void defausser() {
        plateau = null;
    }

    /**
     * getter
     * @return le score du joueur
     */
    public int getScore() {
        return score;
    }

    //ajouter IA et humain (classes internes je pense)
    
}
