package common;

public abstract class Sac{
	
	/**
	 * contenu du sac
	 */
	protected Tuile[] sac;

	/**
	 * mélange le contenu du sac
	 */
	public abstract void melange();

	/**
	 * @return renvoie le contenu du sac
	 */
	public abstract Tuile[] getSac();

	/**
	 * @param i indice du la tuile que l'on veut prendre dans le sac
	 * @return renvoie la tuile à l'indice i du sac
	 */
	public abstract Tuile getSac(int i);

}