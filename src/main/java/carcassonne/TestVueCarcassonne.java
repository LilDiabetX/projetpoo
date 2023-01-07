package carcassonne;

public class TestVueCarcassonne {
    private VueCarcassonne view;
    private ModelCarcassonne model;
    private ControleurCarcassonne control;

    public TestVueCarcassonne() {
        ModelCarcassonne model = new ModelCarcassonne();
        model.getTabJoueur().add(new JoueurCarcassonne(model.getPlateau(), true));
        model.getTabJoueur().add(new JoueurCarcassonne(model.getPlateau(), true));
        model.getTabJoueur().add(new JoueurCarcassonne(model.getPlateau(), true));
        model.setCouleursEtNum();
        model.setActuel(0);
        view = new VueCarcassonne(model);
        view.setVisible(true);
    }

    public static void main(String args []) {
        
        new TestVueCarcassonne();
    }
}
