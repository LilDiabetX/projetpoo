package carcassonne;

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
            System.out.println("tuile centree "+model.getPlateau().getTuileCentree());
            JoueurCarcassonne j = model.getActuel();
            model.piocher(j);
            piochee = true;
            vue.updatePreview();
            vue.updatePioche(model.getSac().estVide());
        }
        else{ //on clique pour defausser
            defausser();
            
        }      
    }

    public void defausser() {
        model.getActuel().defausser();
        vue.updateDefausse();
        model.incrementeTour();
        model.setActuel(model.getTour());
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

    public void placerTuile(int x, int y) {
        System.out.println("x : "+x+"     y : "+y);
        //System.out.println("tuile centree "+model.getPlateau().getTuileCentree());
        if (piochee && model.getActuel().placerTuile(x, y)) {
            vue.updatePlateau(model.getPlateau().sousTableau());
            model.incrementeTour();
            model.setActuel(model.getTour());
            piochee = false;
        }
        

    }

    public void abandonner() {
        model.getActuel().abandonner();
        defausser();
        if (model.joueursRestants() < 2) {
            finDePartie();
        }
        
    }


    public void finDePartie() {
        vue.fin();
    }


    private class WrongDirectionGivenException extends RuntimeException {
        WrongDirectionGivenException(String msg) {
            super(msg);
        }
    }
}
