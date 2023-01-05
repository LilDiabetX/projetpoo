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
     * place un pion sur une tuile du plateau
     * @param x abscisse relative à la tuile de départ du plateau
     * @param y ordonnée relative à la tuile de départ du plateau
     * @return renvoie vrai si un pion a bien été posé
     */
    public boolean placerPion(int x, int y){
        if(pions>0&&plateau.placerPion(x,y,this)){
            pions--;
            return true;
        }
        else if(pions<=0){
            System.out.println("Plus de pion à placer.");
        }
        return false;
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

}