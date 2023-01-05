package carcassonne;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class EmplacementTuile extends JPanel implements MouseInputListener {
    private int width = 50;
    private int height = 50;

    private int xgrille, ygrille;

    private int xorigin, yorigin;

    private boolean occupe;

    public EmplacementTuile(int xgrille, int ygrille, int xorigin, int yorigin) {
        this.xgrille = xgrille;
        this.ygrille = ygrille;
        this.xorigin = xorigin;
        this.yorigin = yorigin;
        setBorder(BorderFactory.createDashedBorder(Color.BLUE));
        setBackground(Color.YELLOW);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void fill(BufferedImage img) {
        this.add(new JLabel(new ImageIcon(img)));
        
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
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
