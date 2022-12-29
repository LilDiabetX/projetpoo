package common;

import java.util.ArrayList;

public abstract class Plateau {
    /**
     * La grille de jeu
     */
    protected ArrayList<ArrayList<Tuile>> grille;
    /**
     * les dimensions de la grille
     */
    protected int hauteur, largeur;

    /**
     * les coordonnées actuelles de la pièce d'origine dans le référentiel de la grille
     */
    protected int x0, y0 = 0;

    /**
     * le nombre de tuiles placées sur le plateau
     */
    protected int placees = 0;

    /**
     * id de la dernière tuile qui a été placée
     */
    protected int idDerniereTuilePlacee;

    /**
     * place une tuile aux coordonnées demandées
     * @param x l'abscisse relative à la tuile d'origine
     * @param y l'ordonnée relative à la tuile d'origine
     * @param tuile la tuile à placer
     * @return renvoie vrai si la tuile a été placée et faux sinon
     */
    public abstract boolean placer(int x, int y, Tuile tuile);

    /**
     * renvoie l'id de la dernière tuile placée
     * @return id de la dernière tuile placée
     */
    public int getDerniere(){
        return idDerniereTuilePlacee;
    }

}
