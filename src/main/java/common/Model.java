package common;

public abstract class Model {

    /**
     * le nombre de joueurs
     */
    protected int nbJoueurs;
    
    
    /**
     * le numéro du tour de jeu
     */
    protected int tourDeJeu = 0;

    /**
     * compte le nombre de tours pour les joueurs qui ont abandonné
     */
    protected int toursAbandonnes;
}
