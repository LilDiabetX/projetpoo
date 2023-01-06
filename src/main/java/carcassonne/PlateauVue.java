package carcassonne;


import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class PlateauVue extends JPanel {
    

    private ControleurCarcassonne control;

    public PlateauVue(ControleurCarcassonne control, TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        this.control = control;
        
        setLayout(new GridBagLayout());
        


        updatePlateau(tab, pCarcassonne);

        
        
    }

    public void updatePlateau(TuileCarcassonne[][] tab, PlateauCarcassonne pCarcassonne) {
        removeAll();

        Insets insets = new Insets(2,2,2,2);
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                
                EmplacementTuile emp = new EmplacementTuile(j, i, pCarcassonne.getXRelatif() + j - 3, -(pCarcassonne.getYRelatif() + i - 2));
                if (tab[i][j] != null) {
                    emp.fill(tab[i][j].getImage());
                } else {
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
                GridBagConstraints constraints =new GridBagConstraints();
                constraints.gridx = j;
                constraints.gridy = i;
                constraints.insets = insets;
                add(emp, constraints);
            }
            System.out.println();
        }
        revalidate();
        repaint();
    }

    
    
}
