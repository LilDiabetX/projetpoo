package carcassonne;

import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class PlateauVue extends JPanel {
    private int hauteur, largeur;
    

    private ControleurCarcassonne control;

    public PlateauVue(ControleurCarcassonne control, TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        this.control = control;
        GridLayout grid = new GridLayout(5, 5);
        setLayout(grid);
        updatePlateau(tab, pCarcassonne);

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

    public void updatePlateau(TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        removeAll();
        TuileCarcassonne centre = tab[2][2];
        //if (centre != null) {
            
            int[] centreXY = pCarcassonne.trouverTuile(centre.getId());

            int centreX = centreXY[0];
            int centreY = centreXY[1];

            int centreXrelatif = centreX - pCarcassonne.getX0();
            int centreYrelatif = centreY - pCarcassonne.getY0();
            //centreX = centreXrelatif + pCarcassonne.getX0();

            //emp.xgrille
            //emp.xorigin
            //emp.xcentre
            //emp.xgrille = centreX + emp.xcentre
            //emp.xorigin = emp.xgrille - centreX + centreXrelatif
        //}
        



        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                EmplacementTuile emp = new EmplacementTuile(control, j, i, j - centreX + centreXrelatif, i - centreY +centreYrelatif);
                if (tab[i][j] != null) {
                    emp.fill(tab[i][j].getImage());
                }
                emp.addMouseListener(new MouseInputListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        control.placerTuile(emp.getXOrigin(), emp.getYOrigin());
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // Pas utilisé
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // Pas utilisé
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Pas utilisé
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Pas utilisé
                        
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        // Pas utilisé
                        
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {
                        // Pas utilisé
                        
                    }
                    
                });
                add(emp);
            }
        }
        
    }

    
    
}
