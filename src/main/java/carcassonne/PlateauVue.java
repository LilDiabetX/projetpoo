package carcassonne;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlateauVue extends JPanel {
    private int hauteur, largeur;
    
    private ArrayList<ArrayList<EmplacementTuile>> plateau;

    public PlateauVue(PlateauCarcassonne pCarcassonne) {
        plateau = new ArrayList<ArrayList<EmplacementTuile>>();
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
        largeur = pCarcassonne.getLargeur();
    }

    
    
}
