package carcassonne;

import common.*;
import java.util.ArrayList;

public class PlateauCarcassonne extends Plateau {

	private ArrayList<ArrayList<TuileCarcassonne>>grille;

	/**
	 * Constructeur vide
	 */
	PlateauCarcassonne(){
		grille = new ArrayList<ArrayList<TuileCarcassonne>>();
		super.hauteur = 0;
		super.largeur = 0;
	}

	/**
	 * Constructeur avec la tuile initiale
	 * @param tuile
	 */
	PlateauCarcassonne(TuileCarcassonne tuile) {
		ArrayList<TuileCarcassonne> ligne = new ArrayList<TuileCarcassonne>();
		ligne.add(tuile);
		grille = new ArrayList<ArrayList<TuileCarcassonne>>();
		grille.add(ligne);
		super.hauteur = 1;
		super.largeur = 1;
		//tuile.setPosee(); à remettre VITE
		super.placees = 1;
		tuileCentree = tuile.getId();
	}

	/**
	 * Place une tuile aux coordonnées (x, y)
	 * @param x l'abscisse relative à la tuile d'origine
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @param tuile la tuile à placer
	 */
	public boolean placer(int x, int y, TuileCarcassonne tuile){
		//on transforme les coordonnées relatives x et y en coordonnées reconnaissables par la grille
		int xindex = x0 + x;
		int yindex = y0 + y; 
		
		//on vérifie si la tuile est plaçable
		if (placableGeneral(xindex, yindex, x, y)) {
			int xfinal = majX(x);
			int yfinal = majY(y);
			if (placableTuile(xfinal, yfinal, tuile)) {
				grille.get(yfinal).set(xfinal, tuile);
				tuile.setPosee();
				placees++;
				System.out.println("Tuile placée avec succès");
				return true;
			} else {
				System.out.println("Rééssayez");
				return false;
			}
		} else {
			System.out.println("Rééssayez");
			return false;
		}
	}

	/**
     * place un pion sur une tuile du plateau
     * @param x abscisse relative à la tuile de départ du plateau
     * @param y ordonnée relative à la tuile de départ du plateau
	 * @param j joueur à qui le pion appartient
     * @return renvoie vrai si un pion a bien été posé
     */
	public boolean placerPion(int x, int y, JoueurCarcassonne j){
		int xindex = x + x0;
		int yindex = y + y0;
		if(grille.get(yindex).get(xindex)!=null&&grille.get(yindex).get(xindex).getPion()==false){
			grille.get(yindex).get(xindex).setPion(j);
			return true;
		}
		else if(grille.get(yindex).get(xindex).getPion()){
			System.out.println("Il y a déjà un pion sur cette case.");
			return false;
		}
		else{
			System.out.println("Cette case n'existe pas");
			return false;
		}
	}


	/**
	 * vérifie si une tuile est plaçable sur la grille sans tenir compte des autres tuiles, agrandit la grille si besoin
	 * @param xindex la coordonnée x sur la grille
	 * @param yindex la coordonnée y sur la grille
	 * @param x l'abscisse relative à la tuile d'origine
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @return true si la tuile est plaçable, false sinon
	 */
	private boolean placableGeneral(int xindex, int yindex, int x, int y) {
		
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
		} else if (xindex < -1 || xindex > largeur){
			System.out.println("Erreur sur la coordonnée x");
			errorx = true;
		}
		if (yindex == -1) {
			plusHaut = true;
		} else if (yindex == hauteur) {
			plusBas = true;
		} else if (yindex < -1 || yindex > hauteur) {
			System.out.println("Erreur sur la coordonnée y");
			errory = true;
		}
		if (errorx || errory) {
			return false;
		}
		
		//on agrandit la grille si besoin
		agrandir(plusHaut, plusBas, plusDroite, plusGauche);

		int xfinal = majX(x);
		int yfinal = majY(y);

		//on vérifie si la case n'est pas déjà occupée par une tuile
		if (grille.get(yfinal).get(xfinal) != null) {
			System.out.println("Case déjà occupée");
			return false;
		}
		return true;
	}


	/**
	 * vérifie si l'emplacement cible a des tuiles voisines, et si les côtés correspondent
	 * @param xfinal la coordonnée x sur la grille
	 * @param yfinal la coordonnée y sur la grille
	 * @param tuile la tuile à placer
	 * @return true si la tuile est plaçable, false sinon
	 */
	private boolean placableTuile(int xfinal, int yfinal, TuileCarcassonne tuile) {

		//on crée une liste contenant les voisins de la position voulue
		TuileCarcassonne[] voisins = listVoisins(xfinal, yfinal);
		if (allNull(voisins)) {
			System.out.println("Pas de voisins à cet emplacement");
			return false;
		}
		Tuile voisinHaut = voisins[0];
		Tuile voisinDroit = voisins[1];
		Tuile voisinBas = voisins[2];
		Tuile voisinGauche = voisins[3];


		//on vérifie si les côtés correspondent
		if ((voisinHaut == null || voisinHaut.getSud().getCote().equals((tuile.getNord().getCote())))
		&& (voisinDroit == null || voisinDroit.getOuest().getCote().equals((tuile.getEst().getCote())))
		&& (voisinBas == null || voisinBas.getNord().getCote().equals((tuile.getSud().getCote())))
		&& (voisinGauche == null || voisinGauche.getEst().getCote().equals((tuile.getOuest().getCote())))) {
			return true;
		}
		System.out.println("La tuile ne rentre pas ici ! Vérifiez les voisins");
		return false;
	}

	/**
	 * Vérifie si l'IA peut placer sa tuile quelque part sur le plateau et la place si possible
	 * @param t Tuile de l'IA
	 * @return renvoie vrai si la tuile a été placée et faux sinon
	 */
	public boolean placableIA(TuileCarcassonne t){ 
		agrandir(true,true,true,true);
		for(int i=0;i<largeur;i++){
			for(int j=0;j<hauteur;j++){
				//on crée une liste contenant les voisins de la position voulue
				
				if(grille.get(j).get(i)==null){
					TuileCarcassonne[] voisins = listVoisins(i, j);

					if (!allNull(voisins)) {
						Tuile voisinHaut = voisins[0];
						Tuile voisinDroit = voisins[1];
						Tuile voisinBas = voisins[2];
						Tuile voisinGauche = voisins[3];

						//on vérifie si les côtés correspondent
						if ((voisinHaut == null || voisinHaut.getSud().getCote().equals((t.getNord().getCote())))
						&& (voisinDroit == null || voisinDroit.getOuest().getCote().equals((t.getEst().getCote())))
						&& (voisinBas == null || voisinBas.getNord().getCote().equals((t.getSud().getCote())))
						&& (voisinGauche == null || voisinGauche.getEst().getCote().equals((t.getOuest().getCote())))) {
							grille.get(j).set(i, t);
							t.setPosee();
							placees++;
							return true;
						}
					}
				}
			}
		}
		return false;
	}


	/**
	 * renvoie la liste des tuiles voisines de la position (xfinal, yfinal)
	 * @param xfinal coordonnée x
	 * @param yfinal coordonnée y
	 * @return la liste des voisins sous la forme : [haut, droite, bas, gauche]
	 */
	private TuileCarcassonne[] listVoisins(int xfinal, int yfinal) {
		TuileCarcassonne[] tab = new TuileCarcassonne[4];
		if (yfinal == 0 && yfinal != hauteur - 1) {
			tab[0] = grille.get(yfinal + 1).get(xfinal);
		} else if (yfinal == hauteur - 1 && yfinal != 0) {
			tab[2] = grille.get(yfinal - 1).get(xfinal);
		} else if (yfinal > 0 && yfinal < hauteur - 1) {
			tab[2] = grille.get(yfinal - 1).get(xfinal);
			tab[0] = grille.get(yfinal + 1).get(xfinal);
		} 

		if (xfinal == 0 && xfinal != largeur - 1) {
			tab[1] = grille.get(yfinal).get(xfinal + 1);
		} else if (xfinal == largeur - 1 && xfinal != 0) {
			tab[3] = grille.get(yfinal).get(xfinal - 1);
		} else if (xfinal > 0 && xfinal < largeur - 1) {
			tab[1] = grille.get(yfinal).get(xfinal + 1);
			tab[3] = grille.get(yfinal).get(xfinal - 1);
		}
		return tab;
	}


	/**
	 * vérifie si tous les éléments d'un tableau de tuiles sont null
	 * @param tab le tableau à parcourir
	 * @return true si tout le tableau est null, false sinon
	 */
	private boolean allNull(TuileCarcassonne[] tab) {
		for (TuileCarcassonne t : tab) {
			if (t != null) {
				return false;
			}
		}
		return true;
	}


	/**
	 * agrandit le plateau d'une ligne vers le haut en conservant les tuiles déjà placées
	 */
	public void agrandirHaut(){
		ArrayList<TuileCarcassonne> ligne = new ArrayList<TuileCarcassonne>();
		for (int i = 0; i < largeur; i++) {
			ligne.add(null);
		}
		grille.add(0, ligne);
		y0++;
		hauteur++;
	}


	/**
	 * agrandit le plateau d'une ligne vers le bas en conservant les tuiles déjà placées
	 */
	 void agrandirBas() {
		ArrayList<TuileCarcassonne> ligne = new ArrayList<TuileCarcassonne>();
		for (int i = 0; i < largeur; i++) {
			ligne.add(null);
		}
		grille.add(ligne);
		hauteur++;
	}


	/**
	 * agrandit le plateau d'une colonne vers la droite en conservant les tuiles déjà placées
	 */
	void agrandirDroite() {
		for (int i = 0; i < hauteur; i++) {
			grille.get(i).add(null);
		}
		largeur++;
	}

	/**
	 * agrandit le plateau d'une colonne vers la gauche en conservant les tuiles déjà placées
	 */
	void agrandirGauche() {
		for (int i = 0; i < hauteur; i++) {
			grille.get(i).add(0, null);
		}
		x0++;
		largeur++;
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

	/**
	 * cherche la position dans la grille d'une tuile
	 * @param id numéro de la tuile que l'on cherche
	 * @return renvoie la position de la tuile cherchée et renvoie [-1,-1] si elle n'a pas été placée
	 */
	public int[] trouverTuile(int id){
		int[] position = {-1,-1};
		for(int i=0;i<largeur;i++){
			for(int j=0;j<hauteur;j++){
				if(grille.get(j).get(i)!=null&&grille.get(j).get(i).getId()==id){
					position[0] = j;
					position[1] = i;
				}
			}
		}
		return position;
	}

	/**
	 * méthode mettant à jour l'id de la tuile sur laquelle on se centre en fonction de la direction qu'on donne
	 * @param direction 0:haut 1:droite 2:bas 3:gauche
	 * @return renvoie vrai si une tuile existe dans la direction donnée et donc que l'id de tuileCentree a été modifiée et faux sinon
	 */
	public boolean deplacer(int direction) throws BadDirectionException{
		if(direction>=0&&direction<4){ 
			int[] positionCourante = trouverTuile(tuileCentree);
			TuileCarcassonne[] voisins = listVoisins(positionCourante[0],positionCourante[1]);
			if(voisins[direction]!=null){
				tuileCentree = voisins[direction].getId();
				switch(direction){
					case 0:
					yRelatif--;
					break;

					case 1:
					xRelatif++;
					break;

					case 2:
					yRelatif++;
					break;

					case 3:
					xRelatif--;
					break;
				}
				return true;
			}
			return false;
		}
		else{
			throw new BadDirectionException("Direction non valide");
		}
	}


	/**
	 * Fonction de test, affiche la grille dans un état plus brut
	 */
	public void printGrille() {
		System.out.print("[");
		for(ArrayList<TuileCarcassonne> list : grille) {
			System.out.print("[");
			for (Tuile t : list) {
				if (t!= null) {
					System.out.print(t.getId()+ " ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.print("], ");
		}
		System.out.print("]");
	}

	public TuileCarcassonne getGrille(int x, int y) {
		return grille.get(x).get(y);
	}

	public TuileCarcassonne[][] sousTableau() {
		TuileCarcassonne[][] sousTab = new TuileCarcassonne[5][7];
		int[] xy = trouverTuile(tuileCentree);
		int x = xy[0];
		int y = xy[1];
		int a = 0;
		for (int i = y+2; i >= y-2; i--) {
			if (i > -1 && i < hauteur) {
				sousTab[a] = sousLigne(i, x);
			}
			a++;
		}
		return sousTab;
	}

	/**
	 * renvoie un tableau centré autour de l'élément d'abscisse x
	 * @param i la ligne de l'élément
	 * @param x son abscisse
	 * @return
	 */
	public TuileCarcassonne[] sousLigne(int i, int x) {
		TuileCarcassonne[] tab = new TuileCarcassonne[7];
		int b =0;
		for (int j = x - 3; j <= x + 3; j++) {
			if (j>-1 && j < largeur) {
				tab[b] = grille.get(i).get(j);

			}
			b++;
		}
		return tab;
	}

	/**
	 * getter
	 * @return renvoie la position relative à la tuile de départ en x de la tuile sur laquelle on est centré
	 */
	public int getXRelatif(){
		return xRelatif;
	}

	/**
	 * getter
	 * @return renvoie la position relative à la tuile de départ en y de la tuile sur laquelle on est centré
	 */
	public int getYRelatif(){
		return yRelatif;
	}

	public int getTuileCentree(){
		return tuileCentree;
	}

	public static void printTab(TuileCarcassonne[][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (tab[i][j] != null) {
					System.out.print(" "+tab[i][j].getId());
				} else {
					System.out.print(" "+"null");
				}
				
			}
			System.out.println();
		}
	}

	public class BadDirectionException extends RuntimeException{
		BadDirectionException(String msg){
			super(msg);
		}
	}

}