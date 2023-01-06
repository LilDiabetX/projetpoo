package carcassonne;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Dimension;

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
    private ModelCarcassonne model;
    private ControleurCarcassonne control;

    
    final int WIDTH = 800; //1430;
    final int HEIGHT = 800; //850;

    JPanel pane; 

    JPanel panneauHUD;
    
    PlateauVue panneauPlateau;
    JPanel panneauBoutons;
    JPanel cadrePreview;
    JPanel cadrePioche;

    JPanel directionsBoutons;

    JButton upButton;
    JButton rightButton;
    JButton downButton;
    JButton leftButton;

    JButton turnRight;
    JButton turnLeft;

    JButton meepleButton;

    JButton abandonButton;

    JLabel piocheImg;
    JLabel piocheRestantes;
    JLabel previewImg;

    JLabel joueurActuel;



    

    public VueCarcassonne(ModelCarcassonne model) {
        this.model = model;
        control = new ControleurCarcassonne(model, this);

        //Config de la fenêtre
        setTitle("Carcassonne™");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Config des panneaux
        pane = new JPanel();
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);
        panneauHUD = new JPanel(new GridLayout(1, 3));
        cadrePreview = new JPanel(new GridLayout(0, 1));
        cadrePioche = new JPanel(new GridLayout(0, 1));
        panneauBoutons = new JPanel();
        directionsBoutons = new JPanel(new BorderLayout());

        
        panneauPlateau = new PlateauVue(control, model.getPlateau().sousTableau(), model.getPlateau());

        
        
        cadrePreview.setBackground(Color.GREEN);

        cadrePioche.setBackground(Color.RED);

        upButton = new JButton(new ImageIcon("src/main/ressources/icones/arrow-up.png"));
        upButton.setPreferredSize(new Dimension(16, 50));
        upButton.addActionListener((event) -> control.seDeplacer(0));
        downButton = new JButton(new ImageIcon("src/main/ressources/icones/arrow-down.png"));
        downButton.setPreferredSize(new Dimension(16, 50));
        downButton.addActionListener((event) -> control.seDeplacer(2));
        rightButton = new JButton(new ImageIcon("src/main/ressources/icones/arrow-right.png"));
        rightButton.setPreferredSize(new Dimension(50, 50));
        rightButton.addActionListener((event) -> control.seDeplacer(1));
        leftButton = new JButton(new ImageIcon("src/main/ressources/icones/arrow-left.png"));
        leftButton.setPreferredSize(new Dimension(50, 50));
        leftButton.addActionListener((event) -> control.seDeplacer(3));

        directionsBoutons.add(upButton, BorderLayout.NORTH);
        directionsBoutons.add(rightButton, BorderLayout.EAST);
        directionsBoutons.add(downButton, BorderLayout.SOUTH);
        directionsBoutons.add(leftButton, BorderLayout.WEST);
        directionsBoutons.setPreferredSize(new Dimension(120, 120));
        directionsBoutons.setBackground(Color.LIGHT_GRAY);

        
        //config des boutons de pivot
        turnLeft = new JButton(new ImageIcon("src/main/ressources/icones/gauche.png"));
        
        turnRight = new JButton(new ImageIcon("src/main/ressources/icones/droite.png"));
        
        turnLeft.addActionListener((event) -> control.pivot(270));
        turnRight.addActionListener((event) -> control.pivot(90));

        turnLeft.setEnabled(false);
        turnRight.setEnabled(false);

        meepleButton = new JButton(new ImageIcon("src/main/ressources/icones/pionCarcassonne.png")); 

        meepleButton.addActionListener((event) -> control.placerPion());

        meepleButton.setEnabled(false);


        abandonButton = new JButton("Abandonner");
        abandonButton.addActionListener((event) -> control.abandonner());

        panneauBoutons.add(turnLeft);
        panneauBoutons.add(turnRight);
        panneauBoutons.add(meepleButton);
        panneauBoutons.add(abandonButton);
        panneauBoutons.add(directionsBoutons);
        panneauBoutons.setBackground(Color.DARK_GRAY);

        //Config des images pour la pioche et l'emplacement de la preview de la tuile
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
        joueurActuel = new JLabel("Joueur "+ (model.getTour()-1)%model.getTabJoueur().size(), (int) CENTER_ALIGNMENT); 
        cadrePreview.add(joueurActuel);

        piocheImg = new JLabel(new ImageIcon(img1));
        cadrePioche.add(piocheImg);
        //JLabel textePioche = new JLabel("PIOCHE", (int) CENTER_ALIGNMENT);
        //cadrePioche.add(textePioche);
        piocheRestantes = new JLabel("Tuile Restantes : "+model.tuilesRestantes(), (int) CENTER_ALIGNMENT);
        cadrePioche.add(piocheRestantes);
        
        
        //Config du fonctionnement de la pioche
        piocheImg.addMouseListener(
            new MouseInputListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    control.piocher();
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
                    //Pas utilisé
                    
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
                
            }
        );

        
        

        //ajout des différents éléments aux panneaux

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

    /**
     * on diminue l'affichage des tuiles restantes
     * quand la pioche sera vide on enlève l'image de verso de carcassonne 
     * @param fini est-ce que le sac est vide ?
     */
    public void updatePioche(boolean fini) {
        piocheRestantes.setText("Tuile Restantes : "+model.tuilesRestantes());
        if (fini) {
            try {
            BufferedImage img = ImageIO.read(new File("src/main/ressources/icones/vide.png"));
            piocheImg.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    /**
     * met à jour l'image de preview des tuiles
     */
    public void updatePreview() {
        if (model.getActuel().getTuile() != null) {
            BufferedImage img = model.getActuel().getTuile().getImage();
            previewImg.setIcon(new ImageIcon(img));
            joueurActuel.setText("Joueur "+(model.getTour()-1)%model.getTabJoueur().size());
            turnLeft.setEnabled(true);
            turnRight.setEnabled(true);
            meepleButton.setEnabled(true);
        }
    }

    /**
     * retire la tuile de preview
     */
    public void updateDefausse() {
        try {
            BufferedImage img = ImageIO.read(new File("src/main/ressources/icones/vide.png"));
            previewImg.setIcon(new ImageIcon(img));
            turnLeft.setEnabled(false);
            turnRight.setEnabled(false);
            meepleButton.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * met à jour l'orientation de la tuile en preview
     * @param angle à gauche ou à droite
     */
    public void updatePivot(int angle) {
        ImageIcon icon = ((ImageIcon) previewImg.getIcon());
        BufferedImage img = ((BufferedImage) icon.getImage());

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        img = op.filter(img, null);


        previewImg.setIcon(new ImageIcon(img));
    }

    public void updatePion(Color couleur) {
        ImageIcon icon = ((ImageIcon) previewImg.getIcon());
        BufferedImage img = ((BufferedImage) icon.getImage());

        Graphics2D g2d = img.createGraphics();

        g2d.setColor(couleur);
        g2d.fillOval(43, 43, 25, 25);

        
        g2d.dispose();

        previewImg.setIcon(new ImageIcon(img));
    }

    public void updatePlateau(TuileCarcassonne[][] tab) {
        panneauPlateau.updatePlateau(tab, model.getPlateau());
        
    }

    public ControleurCarcassonne getController(){
        return control;
    }

    public void fin() {
        JLabel texteFin = new JLabel("FIN");
        JPanel pane = new JPanel();
        pane.add(texteFin);
        pane.setBounds(0, 0, 800, 800);
        setContentPane(pane);
    }

    

    





}
