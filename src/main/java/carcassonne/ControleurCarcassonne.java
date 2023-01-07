package carcassonne;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ControleurCarcassonne {
    
    private ModelCarcassonne model;

    private VueCarcassonne vue;

    private boolean piochee = false;

    public ControleurCarcassonne(ModelCarcassonne model, VueCarcassonne vue) {
        this.model = model;
        this.vue = vue;
    }

    /**
     * gère le tirage dans la pioche
     * Lorsque l'on appuie une premère fois sur la pioche, on reçoit une tuile. On se défausse en rappuyant
     */
    void piocher() {
        if (!model.getSac().estVide()&&!piochee) {
            JoueurCarcassonne j = model.getActuel();
            model.piocher(j);
            piochee = true;
            vue.updatePreview();
            vue.updatePioche(model.getSac().estVide());
            if(!j.getHumain()&&j.placerIA()){
                BufferedImage img = ((BufferedImage) ((ImageIcon) vue.previewImg.getIcon()).getImage());
                model.getActuel().getTuile().setImage(img);
                vue.updatePlateau(model.getPlateau().sousTableau());
                defausser(true);
                piochee = false;
            }
            else if(!j.getHumain()){
                j.getTuile().afficher();
                defausser(true);
                System.out.println("tuile de l'IA défaussée");
            }
        }
        else{ //on clique pour defausser
            defausser(true);
            if (model.getSac().estVide()) {
                finDePartie();
            }
        }      
    }

    /**
     * défausse la tuile courante
     * @param increment s'il faut incrémenter ou non
     */
    public void defausser(boolean increment) {
        model.getActuel().defausser();
        if(increment){
            model.incrementeTour();
        }
        vue.updateDefausse();
        piochee = false;
        if (model.getSac().estVide()) {
            finDePartie();
        }
    }

    /**
     * place un pion sur la tuile dans la main du joueur actuel
     */
    public void placerPion(){
        if(model.getActuel().getTuile() != null && model.getActuel().getPions()>0 && !model.getActuel().getTuile().getPion()){
            model.getActuel().getTuile().placerPion(model.getActuel());
            model.getActuel().placerPion();
            vue.updatePion(model.getActuel().getCouleur());
        }
    }

    /**
     * gère la rotation de la tuile actuelle
     * @param angle à gauche ou à droite
     */
    public void pivot(int angle) {
        if (model.getActuel().getTuile() != null) {
            if (angle == 270) {
                model.getActuel().getTuile().tournerGauche();
            } else if (angle == 90) {
                model.getActuel().getTuile().tournerDroite();
            } else {
                throw new WrongDirectionGivenException("Erreur : l'angle "+angle+" n'est pas valide");
            }
            vue.updatePivot(angle);
        }   
    }

    /**
     * place une tuile sur le plateau
     * @param x
     * @param y
     */
    public void placerTuile(int x, int y) {
        if (piochee && model.getActuel().placerTuile(x, y)) {
            BufferedImage img = ((BufferedImage) ((ImageIcon) vue.previewImg.getIcon()).getImage());
            model.getActuel().getTuile().setImage(img);
            vue.updatePlateau(model.getPlateau().sousTableau());
            vue.updateDefausse();
            model.incrementeTour();
            vue.updateActuel();
            piochee = false;
        }
        

    }

    /**
     * se déplace sur le plateau dans la direction n
     * @param n
     */
    public void seDeplacer(int n) {
        model.getPlateau().deplacer(n);

        vue.updatePlateau(model.getPlateau().sousTableau());
    }


    /**
     * abandonne pour le joueur courant
     */
    public void abandonner() {
        model.abandonner();
        defausser(false);
        if (model.joueursRestants() < 2) {
            finDePartie();
        }
        
    }


    /**
     * déclenche la fin de partie
     */
    public void finDePartie() {
        vue.fin();
    }


    private class WrongDirectionGivenException extends RuntimeException {
        WrongDirectionGivenException(String msg) {
            super(msg);
        }
    }
}
