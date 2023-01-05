package carcassonne;

import java.util.ArrayList;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlateauVue extends JPanel {
    private int hauteur, largeur;
    
    //private ArrayList<ArrayList<EmplacementTuile>> plateau;

    public PlateauVue(TuileCarcassonne[][] tab) {
        setLayout(new GridLayout(5,5));
        updatePlateau(tab);

        /*plateau = new ArrayList<ArrayList<EmplacementTuile>>();
        int k = 0;
        for (int i = pCarcassonne.getHauteur()-1; i >= 0; i--) {
            plateau.add(new ArrayList<EmplacementTuile>());
            for (int j=0; j < pCarcassonne.getLargeur(); j++) {
                EmplacementTuile emp = new EmplacementTuile(j, k, pCarcassonne.getX0() + j, pCarcassonne.getY0() + k);
                plateau.get(k).add(emp);
                if (pCarcassonne.getGrille(i, j) != null) {
                    emp.fill(pCarcassonne.getGrille(i, j).getImage());
                } 
                this.add(emp);
            }
            k++;
        }
        hauteur = pCarcassonne.getHauteur();
        largeur = pCarcassonne.getLargeur();*/
        
    }

    public void updatePlateau(TuileCarcassonne[][] tab) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                add(new EmplacementTuile());
            }
        }
    }

    
    
}
