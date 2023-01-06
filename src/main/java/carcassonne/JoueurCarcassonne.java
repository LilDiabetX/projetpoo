package carcassonne;

import common.Joueur;
import java.awt.Color;

public class JoueurCarcassonne extends Joueur {

	/**
     * La tuile dans les mains du joueur
     */
    private TuileCarcassonne tuile = null;

    /**
     * plateau sur lequel joue le joueur
     */
    private PlateauCarcassonne plateau;

    /**
     * nombre de pion restants au joueurs
     */
    private int pions = 10;

    /**
     * Couleur des pions du joueur
     */
    private Color couleur;

    private int num;

    /**
     * Constructeur
     * @param p le plateau de jeu
     * @param humain définit si le joueur est un humain ou une IA
     */
    public JoueurCarcassonne(PlateauCarcassonne p, boolean humain) {
        this.plateau = p;
        super.humain = humain;
    }

    @Override
    public boolean placerTuile(int x, int y) {
        if(plateau.placer(x, y, tuile)){
            return true;
        }
        return false;
    }

    @Override
    public void defausser() {
        tuile = null;
    }

    /**
     * Setter
     * @param t la nouvelle tuile
     */
    public void setTuile(TuileCarcassonne t) {
        this.tuile = t;
    }

    /**
     * getter
     * @return renvoie la tuile dans la main du joueur
     */
    public TuileCarcassonne getTuile(){
        return tuile;
    }

	/**
     * Méthode qui n'agit que si le joueur est une IA. Dans ce cas, elle vérifie que la tuile du joueur est plaçable quelque part sur le plateau et si oui la place.
     * @return renvoie vrai si la tuile a été placée et faux sinon
     */
    public boolean placerIA(){
        if(!humain){
            if(plateau.placableIA(tuile)){
                return true;
            }
            
        }
        return false;
    }

    /**
     * enlève un pion au joueur
     */
    public void placerPion(){
        pions--;
    }


    /**
     * définit la couleur des pions du joueur
     * @param c couleur des pions
     */
    public void setCouleur(Color c){
        couleur = c;
    }

    /**
     * getter
     * @return renvoie la couleur des pions du joueur
     */
    public Color getCouleur(){
        return couleur;
    }

    /**
     * getter
     * @return renvoie le nombre de pions restant au joueur
     */
    public int getPions(){
        return pions;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public int getNum() {
        return num;
    }

}