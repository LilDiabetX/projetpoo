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
}
