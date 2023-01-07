package common;
public abstract class Tuile{

	/**
	 * faux tant que la tuile n'a pas été posée sur le plateau
	 */
	protected boolean posee = false;
	
	/**
	 * @return renvoie le coté nord de la tuile
	 */
	abstract public Cote getNord();
	/**
	 * @return renvoie le coté sud de la tuile
	 */
	abstract public Cote getSud();
	/**
	 * @return renvoie le coté est de la tuile
	 */
	abstract public Cote getEst();
	/**
	 * @return renvoie le coté ouest de la tuile
	 */
	abstract public Cote getOuest();

	/**
	 * affiche la tuile dans la console
	 */
	abstract public void afficher();

	/**
	 * tourne la tuile d'un quart de tour vers la gauche
	 */
	abstract public void tournerGauche();
	/**
	 * tourne la tuile d'un quart de tour vers la droite
	 */
	abstract public void tournerDroite();

	/**
	 * @return renvoie le numéro d'identification de la tuile
	 */
	abstract public int getId();
	/**
	 * met l'attribut posee à vrai
	 */
	abstract public void setPosee();


}