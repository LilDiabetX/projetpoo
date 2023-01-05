package carcassonne;

public class ControleurCarcassonne {
    
    private ModelCarcassonne model;

    private VueCarcassonne vue;

    public ControleurCarcassonne(ModelCarcassonne model, VueCarcassonne vue) {
        this.model = model;
        this.vue = vue;
    }

    void piocher() {
        JoueurCarcassonne j = model.getActuel();
        model.piocher(j);
        //System.out.println("ControleurPiocher : "+j.getTuile().getImage());
        vue.updatePreview();
    }

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
