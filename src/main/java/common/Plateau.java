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
	 * Met à jour la variable xindex après un éventuel agrandissement de la grille
	 * @param xindex l'indice à mettre à jour
	 * @param x l'abscisse relative à la tuile d'origine
	 * @return l'abscisse relative à la grille
	 */
	protected int majX(int xindex, int x) {
		return x0 + x;
	}
	/**
	 * Met à jour la variable yindex après un éventuel agrandissement de la grille
	 * @param yindex l'indice à mettre à jour
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @return l'ordonnée relative à la grille
	 */
	protected int majY(int yindex, int y) {
		return y0 + y;
	}

}
