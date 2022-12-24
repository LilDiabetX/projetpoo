package common;

public abstract class Model {
    /**
     * le plateau de jeu
     */
    protected Plateau plateau;

    /**
     * le nombre de joueurs
     */
    protected int nbJoueurs;
    /**
     * les joueurs
     */
    protected Joueur[] tabJoueurs;
    /**
     * le joueur dont c'est le tour
     */
    protected Joueur actuel;
    /**
     * le numéro du tour de jeu
     */
    protected int tourDeJeu = 0;

    /**
     * le sac contenant les tuiles du jeu
     */
    protected Sac sac;

    
    /**
     * fait piocher au joueur une tuile dans le sac
     * @param joueur le joueur à qui c'est le tour
     */
    public abstract void piocher(Joueur joueur);

    /**
     * fait placer sa tuile au joueur sur le plateau
     * @param joueur le joueur à qui c'est le tour
     * @param x l'abscisse relative à la tuile d'origine
     * @param y l'ordonnée relative à la tuile d'origine
     */
    public abstract void placer(Joueur joueur, int x, int y);

    /**
     * lance le jeu
     */
    public abstract void play();
}
