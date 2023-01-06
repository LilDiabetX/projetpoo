package carcassonne;

import common.Model;
import java.util.ArrayList;
import java.awt.Color;

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

    

    public ModelCarcassonne() {
        tabJoueurs = new ArrayList<JoueurCarcassonne>();
        sac = new SacCarcassonne();
        plateau = new PlateauCarcassonne(sac.getSac(tourDeJeu));
        tabJoueurs.add(new JoueurCarcassonne(plateau, true));
        tabJoueurs.add(new JoueurCarcassonne(plateau, true));
        actuel = tabJoueurs.get(0);
        setCouleurs();
        tourDeJeu++;
    }


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
     * getter
     * @return renvoie le tour de jeu
     */
    public int getTour(){
        return tourDeJeu;
    }

    /**
     * pioche une tuile dans le sac
     * @param joueur joueur qui pioche la tuile
     */
    public void piocher(JoueurCarcassonne joueur) {
        joueur.setTuile(sac.getSac(tourDeJeu));
    }

    /**
     * définit le joueur à la position i modulo le nombre de joueurs dans le tableau de joueurs comme le joueur actuel 
     */
    public void setActuel(int i){
        actuel = tabJoueurs.get(i%tabJoueurs.size());
    }

    public void setCouleurs(){
        Color[] couleurs = {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW,Color.PINK};
        for(int i = 0;i<tabJoueurs.size();i++){
            tabJoueurs.get(i).setCouleur(couleurs[i]);
        }
    }

    /**
     * fait passer au tour suivant
     */
    public void incrementeTour(){
        tourDeJeu++;
    }

    public JoueurCarcassonne getActuel() {
        return actuel;
    }

    public SacCarcassonne getSac() {
        return sac;
    }

    public void setPlateau(PlateauCarcassonne plateau) {
        this.plateau = plateau;
    }

    public void setTabJoueurs(ArrayList<JoueurCarcassonne> tabJoueurs) {
        this.tabJoueurs = tabJoueurs;
    }

    public void setActuel(JoueurCarcassonne actuel) {
        this.actuel = actuel;
    }

    public void setSac(SacCarcassonne sac) {
        this.sac = sac;
    }

    public int tuilesRestantes() {
        return sac.getTuilesRestantes();
    }

    public int joueursRestants() {
        int sum = 0;
        for (JoueurCarcassonne j : tabJoueurs) {
            if (!j.getAbandon()) {
                sum++;
            }
        }
        return sum;
    }
    
}
