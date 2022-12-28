package common;

public abstract class Joueur {
    
    /**
     * Le plateau de jeu
     */
    protected Plateau plateau;

    /**
     * détermine si le joueur a abandonné ou pas 
     */
    private boolean abandon = false;

    /**
     * détermine si le joueur est humain (true) ou si c'est une IA (false)
     */
    protected boolean humain;

    
    /**
     * Place la tuile sur le plateau à l'emplacement relatif à la tuile de départ
     * @param x l'abscisse relative à la tuile de départ
     * @param y l'ordonnée relative à la tuile de départ
     * @return renvoie vrai si la tuile a été placée et faux sinon
     */
    public abstract boolean placerTuile(int x, int y);

    /**
     * retire la tuile de la main du joueur
     */
    public abstract void defausser();

    /**
     * le joueur quitte la partie
     */
    public void abandonner() {
        abandon = true;
    }

    

    /**
     * getter
     * @return le booleen abandon
     */
    public boolean getAbandon() {
        return this.abandon;
    }

    /**
     * getter
     * @return renvoie le booleen déterminant si le joueur est humain ou une IA
     */
    public boolean getHumain(){
        return humain;
    }
}
