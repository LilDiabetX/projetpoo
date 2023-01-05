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
            JoueurCarcassonne j = model.getActuel();
            model.piocher(j);
            piochee = true;
            vue.updatePreview();
            vue.updatePioche(model.getSac().estVide());
        }
        else{ //on clique pour defausser
            model.getActuel().defausser();
            vue.updateDefausse();
            model.incrementeTour();
            model.setActuel(model.getTour());
            piochee = false;
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


    private class WrongDirectionGivenException extends RuntimeException {
        WrongDirectionGivenException(String msg) {
            super(msg);
        }
    }
}
