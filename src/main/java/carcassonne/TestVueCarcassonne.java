package carcassonne;

public class TestVueCarcassonne {
    private VueCarcassonne view;
    private ModelCarcassonne model;
    private ControleurCarcassonne control;

    public TestVueCarcassonne() {
        view = new VueCarcassonne();
        view.setVisible(true);
    }

    public static void main(String args []) {
        
        new TestVueCarcassonne();
    }
}
