package carcassonne;

import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class PlateauVue extends JPanel {
    

    private ControleurCarcassonne control;

    public PlateauVue(ControleurCarcassonne control, TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        this.control = control;
        GridLayout grid = new GridLayout(5, 5);
        setLayout(grid);
        updatePlateau(tab, pCarcassonne);

        
        
    }

    public void updatePlateau(TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        removeAll();
        TuileCarcassonne centre = tab[2][2];
        if (centre != null) {
            
            int[] centreXY = pCarcassonne.trouverTuile(centre.getId());

            int centreX = centreXY[0];
            int centreY = centreXY[1];

            int centreXrelatif = pCarcassonne.getXRelatif();
            int centreYrelatif = pCarcassonne.getYRelatif();

            //REFLEXIONS MATHEMATIQUES (ça m'a pas mené bien loin)
            //centreX = centreXrelatif + pCarcassonne.getX0();
            //emp.xgrille
            //emp.xorigin
            //emp.xcentre
            //emp.xgrille = centreX + emp.xcentre
            //emp.xorigin = emp.xgrille - centreX + centreXrelatif
        }
        



        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                EmplacementTuile emp = new EmplacementTuile(control, j, i, pCarcassonne.getXRelatif() + j - 2, -(pCarcassonne.getYRelatif() + i - 2));
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
