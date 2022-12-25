package dominos;

import common.*;

public class JoueurDomino extends Joueur {

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
    }

    @Override
    public void defausser() {
        plateau = null;
    }

    //ajouter IA et humain (classes internes je pense)
    
}
