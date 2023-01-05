package carcassonne;

public class TestVueCarcassonne {
    private VueCarcassonne view;
    private ModelCarcassonne model;
    private ControleurCarcassonne control;

    public TestVueCarcassonne() {
       
        view = new VueCarcassonne(new ModelCarcassonne());
        view.setVisible(true);
    }

    public static void main(String args []) {
        
        new TestVueCarcassonne();
    }
}
