package carcassonne;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class EmplacementTuile extends JPanel {
    private int width = 100;
    private int height = 100;

    /**
     * les coordonnées de l'emplacement sur la mini-grille
     */
    private int xgrille, ygrille;

    /**
     * censé être les coordonnées par rapport à la tuile d'origine, mais je n'arrive pas à les construire
     */
    private int xorigin, yorigin;

    private boolean occupe;
    private ControleurCarcassonne control;

    public EmplacementTuile(ControleurCarcassonne control, int xgrille, int ygrille, int xorigin, int yorigin) {
        this(control);
        this.xgrille = xgrille;
        this.ygrille = ygrille;
        this.xorigin = xorigin;
        this.yorigin = yorigin;
        
    }

    private EmplacementTuile(ControleurCarcassonne control) {
        this.control = control;
        setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void fill(BufferedImage img) {
        Image img2 = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img2);
        JLabel label = new JLabel(resizedIcon);
        label.setAlignmentX(0.0f);
        this.add(label);
        occupe = true;
    }
    





    public int getWidth() {
        return width;
    }




    public int getHeight() {
        return height;
    }




    public int getXGrille() {
        return xgrille;
    }




    public int getYGrille() {
        return ygrille;
    }

    public int getXOrigin() {
        return xorigin;
    }

    public int getYOrigin() {
        return yorigin;
    }
    



    public boolean estOccupe() {
        return occupe;
    }
    
}
