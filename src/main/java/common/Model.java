package common;

public abstract class Model {
    protected Plateau plateau;

    protected int nbJoueurs;
    protected Joueur[] tabJoueurs;
    protected Joueur actuel;

    protected Sac sac;


    public abstract void piocher(Joueur joueur);
}
