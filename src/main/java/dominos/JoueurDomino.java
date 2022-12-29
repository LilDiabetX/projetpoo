package dominos;

import common.*;

public class JoueurDomino extends Joueur {

    /**
     * La tuile dans les mains du joueur
     */
    private TuileDomino tuile = null;

    private PlateauDomino plateau;

    /**
     * Constructeur
     * @param p le plateau de jeu
     * @param humain définit si le joueur est un humain ou une IA
     */
    JoueurDomino(PlateauDomino p, boolean humain) {
        this.plateau = p;
        super.humain = humain;
    }
    
    @Override
    public boolean placerTuile(int x, int y) {
        return plateau.placer(x, y, tuile);
    }

    @Override
    public void defausser() {
        tuile = null;
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

    /**
     * Méthode qui n'agit que si le joueur est une IA. Dans ce cas, elle vérifie que la tuile du joueur est plaçable quelque part sur le plateau et si oui la place.
     * @return renvoie vrai si la tuile a été placée et faux sinon
     */
    public boolean placerIA(){
        if(!humain){
            return plateau.placableIA(tuile);
        }
        return false;
    }
    
}
