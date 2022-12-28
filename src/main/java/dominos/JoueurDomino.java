package dominos;

import common.*;

public class JoueurDomino extends Joueur {

    /**
     * La tuile dans les mains du joueur
     */
    private TuileDomino tuile = null;

    /**
     * Constructeur
     * @param p le plateau de jeu
     * @param humain définit si le joueur est un humain ou une IA
     */
    JoueurDomino(Plateau p, boolean humain) {
        super.plateau = p;
        super.humain = humain;
    }
    
    @Override
    public boolean placerTuile(int x, int y) {
        return plateau.placer(x, y, tuile);
    }

    @Override
    public void defausser() {
        plateau = null;
    }

    /**
     * Setter
     * @param t la nouvelle tuile
     */
    public void setTuile(TuileDomino t) {
        this.tuile = t;
    }

    /**
     * getter
     * @return renvoie la tuile dans la main du joueur
     */
    public TuileDomino getTuile(){
        return tuile;
    }
    
}
