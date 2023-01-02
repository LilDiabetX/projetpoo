package carcassonne;

import common.Model;
import java.util.ArrayList;

public class ModelCarcassonne extends Model{
    
    /**
     * plateau de jeu
     */
    private PlateauCarcassonne plateau;

    /**
     * les joueurs
     */
    private ArrayList<JoueurCarcassonne> tabJoueurs;

    /**
     * le joueur dont c'est le tour
     */
    private JoueurCarcassonne actuel;

    /**
     * le sac contenant les tuiles du jeu
     */
    private SacCarcassonne sac;

    /**
     * Vue du jeu
     */
    // TODO ajouter une vue


    /**
     * getter
     * @return renvoie le tableau de joueurs
     */
    public ArrayList<JoueurCarcassonne> getTabJoueur(){
        return tabJoueurs;
    }

    /**
     * getter
     * @return renvoie le plateau de jeu
     */
    public PlateauCarcassonne getPlateau(){
        return plateau;
    }


    /**
     * fonction qui lance et joue une partie de Carcassonne
     */
    public void play(){

    }
}
