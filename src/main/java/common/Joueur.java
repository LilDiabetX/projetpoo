package common;

public abstract class Joueur {
    /**
     * La tuile dans les mains du joueur
     */
    protected Tuile tuile = null;
    /**
     * Le plateau de jeu
     */
    protected Plateau plateau;

    
    /**
     * Place la tuile sur le plateau à l'emplacement relatif à la tuile de départ
     * @param x l'abscisse relative à la tuile de départ
     * @param y l'ordonnée relative à la tuile de départ
     */
    public abstract void placerTuile(int x, int y);

    /**
     * retire la tuile de la main du joueur
     */
    public abstract void defausser();

    /**
     * quitte la partie
     */
    public void abandonner() {
        System.exit(0);
    }

    public void setTuile(Tuile t) {
        this.tuile = t;
    }
}
