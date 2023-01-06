package carcassonne;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    public EmplacementTuile(int xgrille, int ygrille, int xorigin, int yorigin) {
        this();
        this.xgrille = xgrille;
        this.ygrille = ygrille;
        this.xorigin = xorigin;
        this.yorigin = yorigin;
        
    }

    private EmplacementTuile() {
        setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
    }

    /**
     * remplit l'emplacement avec img
     * @param img l'image
     */
    public void fill(BufferedImage img) {
        Image img2 = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        this.add(new JLabel(new ImageIcon(img2)), BorderLayout.WEST);
        
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
