package common;

public abstract class Plateau {

    /**
     * les dimensions de la grille
     */
    protected int hauteur, largeur;

    /**
     * les coordonnées actuelles de la pièce d'origine dans le référentiel de la grille
     */
    protected int x0, y0 = 0;

    /**
     * le nombre de tuiles placées sur le plateau
     */
    protected int placees = 0;

	/**
	 * id de la tuile sur laquelle on se situe
	 */
	protected int tuileCentree;

	/**
	 * coordonnées relatives à la tuile de départ de la tuile sur laquelle on est centré
	 */
	protected int xRelatif, yRelatif;

    /**
	 * Met à jour la variable xindex après un éventuel agrandissement de la grille
	 * @param x l'abscisse relative à la tuile d'origine
	 * @return l'abscisse relative à la grille
	 */
	protected int majX(int x) {
		return x0 + x;
	}
	/**
	 * Met à jour la variable yindex après un éventuel agrandissement de la grille
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @return l'ordonnée relative à la grille
	 */
	protected int majY(int y) {
		return y0 + y; 
	}
	public int getHauteur() {
		return hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	public int getX0() {
		return x0;
	}
	public int getY0() {
		return y0;
	}
	public int getPlacees() {
		return placees;
	}

	

}
