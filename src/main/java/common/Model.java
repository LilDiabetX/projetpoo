package common;

import java.util.ArrayList;

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
     * lance le jeu
     */
    public abstract void play();
}
