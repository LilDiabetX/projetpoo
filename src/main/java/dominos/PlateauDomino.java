package dominos;

import java.util.ArrayList;

import common.*;

public class PlateauDomino extends Plateau {

	/**
	 * Constructeur vide
	 */
	PlateauDomino(){
		super.grille = new ArrayList<ArrayList<Tuile>>();
		super.hauteur = 0;
		super.largeur = 0;
	}

	/**
	 * Constructeur avec la tuile initiale
	 * @param tuile
	 */
	PlateauDomino(TuileDomino tuile) {
		ArrayList<Tuile> ligne = new ArrayList<Tuile>();
		ligne.add(tuile);
		super.grille = new ArrayList<ArrayList<Tuile>>();
		super.grille.add(ligne);
		super.hauteur = 1;
		super.largeur = 1;
	}

	/**
     * place une tuile aux coordonnées demandées
     * @param x l'abscisse relative à la tuile d'origine
     * @param y l'ordonnée relative à la tuile d'origine
     * @param tuile la tuile à placer
     */
	@Override
	public void placer(int x, int y, Tuile tuile){
		//on transforme les coordonnées relatives x et y en coordonnées reconnaissables par la grille
		int xindex = x0 + x;
		int yindex = y0 + y; 
		
		//on vérifie si la tuile est plaçable
		if (placableGeneral(xindex, yindex) && majIndex(xindex, yindex, x, y) && placableTuile(xindex, yindex, tuile)) {
			grille.get(yindex).set(xindex, tuile);
		}
		System.out.println("Rééssayez");
	}


	/**
	 * vérifie si une tuile est plaçable sur la grille sans tenir compte des autres tuiles, agrandit la grille si besoin
	 * @param xindex la coordonnée x sur la grille
	 * @param yindex la coordonnée y sur la grille
	 * @return true si la tuile est plaçable, false sinon
	 */
	private boolean placableGeneral(int xindex, int yindex) {
		boolean errorx = false;
		boolean errory = false;

		boolean plusHaut = false;
		boolean plusBas = false;
		boolean plusGauche = false;
		boolean plusDroite = false;

		//on vérifie si les indices sont valides et si on a besoin d'agrandir la grille
		if (xindex == -1) {
			plusGauche = true;
		} else if (xindex == largeur) {
			plusDroite = true;
		} else if (yindex < -1 || yindex > largeur){
			System.out.println("Erreur sur la coordonnée y");
			errorx = true;
		}
		if (yindex == -1) {
			plusHaut = true;
		} else if (yindex == hauteur) {
			plusBas = true;
		} else if (yindex < -1 || yindex > hauteur) {
			System.out.println("Erreur sur la coordonnée x");
			errory = true;
		}
		if (errorx || errory) {
			return false;
		}
		
		//on agrandit la grille si besoin
		agrandir(plusHaut, plusBas, plusDroite, plusGauche);

		//on vérifie si la case n'est pas déjà occupée par une tuile
		if (grille.get(yindex).get(xindex) != null) {
			System.out.println("Case déjà occupée");
			return false;
		}
		return true;
	}

	/**
	 * vérifie si l'emplacement cible a des tuiles voisines, et si les côtés correspondent
	 * @param xindex la coordonnée x sur la grille
	 * @param yindex la coordonnée y sur la grille
	 * @param tuile la tuile à placer
	 * @return true si la tuile est plaçable, false sinon
	 */
	private boolean placableTuile(int xindex, int yindex, Tuile tuile) {
		//on crée une liste contenant les voisins de la position voulue
		Tuile[] voisins = listVoisins(xindex, yindex);
		if (allNull(voisins)) {
			System.out.println("Pas de voisins à cet emplacement");
			return false;
		}
		Tuile voisinHaut = voisins[0];
		Tuile voisinDroit = voisins[1];
		Tuile voisinBas = voisins[2];
		Tuile voisinGauche = voisins[3];

		//on vérifie si les côtés correspondent
		if ((voisinHaut != null && voisinHaut.getSud() != tuile.getNord())
		|| (voisinDroit != null && voisinDroit.getOuest() != tuile.getEst())
		|| (voisinBas != null && voisinBas.getNord() != tuile.getSud())
		|| (voisinGauche != null && voisinGauche.getEst() != tuile.getOuest())) {
			System.out.println("la tuile ne rentre pas à ici ! Vérifiez les voisins");
			return false;
		}
		
		return true;
	}

	/**
	 * renvoie la liste des tuiles voisines de la position (xindex, yindex)
	 * @param xindex coordonnée x
	 * @param yindex coordonnée y
	 * @return la liste des voisins sous la forme : [haut, droite, bas, gauche]
	 */
	private Tuile[] listVoisins(int xindex, int yindex) {
		Tuile[] tab = new Tuile[4];
		if (yindex == 0) {
			tab[2] = grille.get(yindex + 1).get(xindex);
		} else if (yindex == hauteur - 1) {
			tab[0] = grille.get(yindex - 1).get(xindex);
		} else {
			tab[0] = grille.get(yindex - 1).get(xindex);
			tab[2] = grille.get(yindex + 1).get(xindex);
		} 

		if (xindex == 0) {
			tab[1] = grille.get(yindex).get(xindex + 1);
		} else if (xindex == largeur - 1) {
			tab[3] = grille.get(yindex).get(xindex - 1);
		} else {
			tab[1] = grille.get(yindex).get(xindex + 1);
			tab[3] = grille.get(yindex).get(xindex - 1);
		}
		return tab;
	}

	/**
	 * vérifie si tous les éléments d'un tableau de tuiles sont null
	 * @param tab le tableau à parcourir
	 * @return true si tout le tableau est null, false sinon
	 */
	private boolean allNull(Tuile[] tab) {
		for (Tuile t : tab) {
			if (t != null) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Met à jour les variables xindex et yindex après un éventuel agrandissement de la grille
	 * @param xindex variable à mettre à jour
	 * @param yindex variable à mettre à jour
	 * @param x l'abscisse relative à la tuile d'origine
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @return true
	 */
	private boolean majIndex(int xindex, int yindex, int x, int y) {
		xindex = x0 + x;
		yindex = y0 + y;
		return true;
	}


	public void afficherLigne(int i){
		System.out.println();
		for(int j=0;j<largeur;j++){
			System.out.print("  "+grille.get(i).get(j).getNord().getCote().charAt(0)+"|"+grille.get(i).get(j).getNord().getCote().charAt(1)+"|"+grille.get(i).get(j).getNord().getCote().charAt(2)+"   ");
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(2)+"       "+grille.get(i).get(j).getEst().getCote().charAt(0)+" ");
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(1)+"       "+grille.get(i).get(j).getEst().getCote().charAt(1)+" ");
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(0)+"       "+grille.get(i).get(j).getEst().getCote().charAt(2)+" ");
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			System.out.print("  "+grille.get(i).get(j).getSud().getCote().charAt(2)+"|"+grille.get(i).get(j).getSud().getCote().charAt(1)+"|"+grille.get(i).get(j).getSud().getCote().charAt(0)+"   ");
		}
	}

	/**
	 * affiche dans la console le plateau
	 */
	public void afficher(int id){
		for(int i=hauteur-1;i>=0;i--){
			this.afficherLigne(i);
		}
		System.out.println();
	}

	/**
	 * agrandit le plateau d'une ligne vers le haut en conservant les tuiles déjà placées
	 */
	public void agrandirHaut(){
		ArrayList<Tuile> ligne = new ArrayList<Tuile>();
		for (int i = 0; i < largeur; i++) {
			ligne.add(null);
		}
		grille.add(0, ligne);
		y0++;
	}

	/**
	 * agrandit le plateau d'une ligne vers le bas en conservant les tuiles déjà placées
	 */
	private void agrandirBas() {
		ArrayList<Tuile> ligne = new ArrayList<Tuile>();
		for (int i = 0; i < largeur; i++) {
			ligne.add(null);
		}
		grille.add(ligne);
	}

	/**
	 * agrandit le plateau d'une colonne vers la droite en conservant les tuiles déjà placées
	 */
	private void agrandirDroite() {
		for (int i = 0; i < hauteur; i++) {
			grille.get(i).add(null);
		}
	}

	/**
	 * agrandit le plateau d'une colonne vers la gauche en conservant les tuiles déjà placées
	 */
	private void agrandirGauche() {
		for (int i = 0; i < hauteur; i++) {
			grille.get(i).add(0, null);
		}
		x0++;
	}

	/**
	 * agrandit le plateau de lignes ou de colonnes selon les paramètres
	 * @param plusHaut est-ce qu'il faut agrandir en haut ?
	 * @param plusBas est-ce qu'il faut agrandir en bas ?
	 * @param plusDroite est-ce qu'il faut agrandir à droite ?
	 * @param plusGauche est-ce qu'il faut agrandir à gauche ?
	 */
	private void agrandir(boolean plusHaut, boolean plusBas, boolean plusDroite, boolean plusGauche) {
		if (plusHaut) {
			agrandirHaut();
		}
		if (plusBas) {
			agrandirBas();
		}
		if (plusDroite) {
			agrandirDroite();
		}
		if (plusGauche) {
			agrandirGauche();
		}
	}
}