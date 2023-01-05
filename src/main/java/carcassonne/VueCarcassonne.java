package carcassonne;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;



public class VueCarcassonne extends JFrame {
    private ModelCarcassonne model = new ModelCarcassonne();
    private ControleurCarcassonne control = new ControleurCarcassonne(model, this);

    final int WIDTH = 800; //1430
    final int HEIGHT = 800; //850

    JPanel pane; 

    JPanel panneauHUD;
    
    JPanel panneauPlateau;
    JPanel panneauBoutons;
    JPanel cadrePreview;
    JPanel cadrePioche;

    JButton turnRight;
    JButton turnLeft;

    JLabel piocheImg;
    JLabel previewImg;

    

    public VueCarcassonne() {
    
        setTitle("Carcassonne™");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane = new JPanel();
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);
        panneauHUD = new JPanel(new GridLayout(1, 3));
        cadrePreview = new JPanel();
        cadrePioche = new JPanel(new GridLayout(2, 1));
        panneauBoutons = new JPanel();
        panneauPlateau = new JPanel();
        


        
        cadrePreview.setBackground(Color.GREEN);

        cadrePioche.setBackground(Color.RED);
        
        turnLeft = new JButton(new ImageIcon("src/main/ressources/icones/gauche.png"));
        turnLeft.setSize(10, 10);
        turnRight = new JButton(new ImageIcon("src/main/ressources/icones/droite.png"));
        turnRight.setSize(10, 10);

        panneauBoutons.add(turnLeft);
        panneauBoutons.add(turnRight);

        panneauBoutons.setBackground(Color.BLUE);

        BufferedImage img1 = null;
        BufferedImage imgCarre = null;
        try {
            img1 = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/cachee.png"));
            imgCarre = ImageIO.read(new File("src/main/ressources/icones/vide.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        previewImg = new JLabel(new ImageIcon(imgCarre));
        cadrePreview.add(previewImg);

        piocheImg = new JLabel(new ImageIcon(img1));
        cadrePioche.add(piocheImg);
        JLabel textePioche = new JLabel("PIOCHE", (int) CENTER_ALIGNMENT);
        cadrePioche.add(textePioche);
        
        
        
        piocheImg.addMouseListener(
            new MouseInputListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    control.piocher();
                    
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
                
            }
        );
        
        



        panneauHUD.add(cadrePreview);
        panneauHUD.add(panneauBoutons);
        panneauHUD.add(cadrePioche);
        

        panneauHUD.setBounds(0, HEIGHT*2/3, WIDTH, HEIGHT/3);
        

        panneauPlateau.setBackground(Color.YELLOW);
        panneauPlateau.setBounds(0, 0, WIDTH, HEIGHT*2/3);

        pane.add(panneauPlateau);
        pane.add(panneauHUD);
        

        setContentPane(pane);
        

    }

    public void updatePioche() {
        
    }

    public void updatePreview() {
        
        BufferedImage img = model.getActuel().getTuile().getImage();
        previewImg.setIcon(new ImageIcon(img));
        
        
    }

    





}
