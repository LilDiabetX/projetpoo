package common;
public abstract class Tuile{

	private static int nbTuiles;
	private int id;

	private Cote nord;
	private Cote sud;
	private Cote est;
	private Cote ouest;
	
	abstract public Cote getNord();
	abstract public Cote getSud();
	abstract public Cote getEst();
	abstract public Cote getOuest();

	abstract public void afficher();

	abstract public void tournerGauche();
	abstract public void tournerDroite();
}