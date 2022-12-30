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
		tuile.setPosee();
		super.placees = 1;
		super.idDerniereTuilePlacee = tuile.getId();
	}
	
	@Override
	public boolean placer(int x, int y, Tuile tuile){


		//on transforme les coordonnées relatives x et y en coordonnées reconnaissables par la grille
		int xindex = x0 + x;
		int yindex = y0 + y; 
		
		//on vérifie si la tuile est plaçable
		if (placableGeneral(xindex, yindex, x, y)) {
			int xfinal = majX(xindex, x);
			int yfinal = majY(yindex, y);
			if (placableTuile(xfinal, yfinal, tuile)) {
				grille.get(yfinal).set(xfinal, tuile);
				tuile.setPosee();
				placees++;
				super.idDerniereTuilePlacee = tuile.getId();
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

		int xfinal = majX(xindex, x);
		int yfinal = majY(yindex, y);

		//on vérifie si la case n'est pas déjà occupée par une tuile
		if (grille.get(yfinal).get(xfinal) != null) {
			System.out.println("Case déjà occupée");
			return false;
		}
		return true;
	}
	
	/**
	 * Met à jour la variable xindex après un éventuel agrandissement de la grille
	 * @param xindex l'indice à mettre à jour
	 * @param x l'abscisse relative à la tuile d'origine
	 * @return l'abscisse relative à la grille
	 */
	private int majX(int xindex, int x) {
		return x0 + x;
	}
	/**
	 * Met à jour la variable yindex après un éventuel agrandissement de la grille
	 * @param yindex l'indice à mettre à jour
	 * @param y l'ordonnée relative à la tuile d'origine
	 * @return l'ordonnée relative à la grille
	 */
	private int majY(int yindex, int y) {
		return y0 + y;
	}


	/**
	 * vérifie si l'emplacement cible a des tuiles voisines, et si les côtés correspondent
	 * @param xfinal la coordonnée x sur la grille
	 * @param yfinal la coordonnée y sur la grille
	 * @param tuile la tuile à placer
	 * @return true si la tuile est plaçable, false sinon
	 */
	private boolean placableTuile(int xfinal, int yfinal, Tuile tuile) {

		//on crée une liste contenant les voisins de la position voulue
		Tuile[] voisins = listVoisins(xfinal, yfinal);
		if (allNull(voisins)) {
			System.out.println("Pas de voisins à cet emplacement");
			return false;
		}
		Tuile voisinHaut = voisins[0];
		Tuile voisinDroit = voisins[1];
		Tuile voisinBas = voisins[2];
		Tuile voisinGauche = voisins[3];


		//on vérifie si les côtés correspondent
		if ((voisinHaut == null || voisinHaut.getSud().getCote().equals(((CoteDomino) tuile.getNord()).getInverse()))
		&& (voisinDroit == null || voisinDroit.getOuest().getCote().equals(((CoteDomino) tuile.getEst()).getInverse()))
		&& (voisinBas == null || voisinBas.getNord().getCote().equals(((CoteDomino) tuile.getSud()).getInverse()))
		&& (voisinGauche == null || voisinGauche.getEst().getCote().equals(((CoteDomino) tuile.getOuest()).getInverse()))) {
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
	public boolean placableIA(TuileDomino t){
		this.agrandirBas();
		this.agrandirHaut();
		this.agrandirGauche();
		this.agrandirDroite();
		for(int i=0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				//on crée une liste contenant les voisins de la position voulue
				Tuile[] voisins = listVoisins(j, i);

				Tuile voisinHaut = voisins[0];
				Tuile voisinDroit = voisins[1];
				Tuile voisinBas = voisins[2];
				Tuile voisinGauche = voisins[3];

				//on vérifie si les côtés correspondent
				if ((voisinHaut == null || voisinHaut.getSud().getCote().equals(((CoteDomino) t.getNord()).getInverse()))
				&& (voisinDroit == null || voisinDroit.getOuest().getCote().equals(((CoteDomino) t.getEst()).getInverse()))
				&& (voisinBas == null || voisinBas.getNord().getCote().equals(((CoteDomino) t.getSud()).getInverse()))
				&& (voisinGauche == null || voisinGauche.getEst().getCote().equals(((CoteDomino) t.getOuest()).getInverse()))
				&& (grille.get(i).get(j)==null)
				&& (voisinHaut != null || voisinDroit != null || voisinBas != null || voisinGauche != null)) {
					grille.get(j).set(i, t);
					t.setPosee();
					placees++;
					super.idDerniereTuilePlacee = t.getId();
					return true;
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
	private Tuile[] listVoisins(int xfinal, int yfinal) {
		Tuile[] tab = new Tuile[4];
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
	 * renvoie la somme des valeurs des côtés adjacents à d'autres tuiles
	 * @param tuile 
	 * @return la somme
	 */
	public int sommeCotesAdja(Tuile tuile) {
		try {
			int[] coordonnes = getXY(tuile.getId());
			Tuile[] voisins = listVoisins(coordonnes[0], coordonnes[1]);

			Tuile voisinHaut = voisins[0];
			Tuile voisinDroit = voisins[1];
			Tuile voisinBas = voisins[2];
			Tuile voisinGauche = voisins[3];
			int sum = 0;
			if (voisinHaut != null) {
				sum += ((CoteDomino) voisinHaut.getSud()).sommeChiffres();
			}
			if (voisinDroit != null) {
				sum += ((CoteDomino) voisinDroit.getOuest()).sommeChiffres();
			}
			if (voisinBas != null) {
				sum += ((CoteDomino) voisinBas.getNord()).sommeChiffres();
			}
			if (voisinGauche != null) {
				sum += ((CoteDomino) voisinGauche.getEst()).sommeChiffres();
			}
			return sum;
		} 
		catch (TileNotPlacedException e) {
			e.printStackTrace();
			return -1;
		}

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
	 * affiche une ligne de la grille
	 * @param i l'indice de la ligne à afficher
	 */
	public void afficherLigne(int i){
		System.out.println();
		for(int j=0;j<largeur;j++){
			if (grille.get(i).get(j) != null) {
				System.out.print("  "+grille.get(i).get(j).getNord().getCote().charAt(0)+"|"+grille.get(i).get(j).getNord().getCote().charAt(1)+"|"+grille.get(i).get(j).getNord().getCote().charAt(2)+"   ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			if (grille.get(i).get(j) != null) {
				System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(2)+"       "+grille.get(i).get(j).getEst().getCote().charAt(0)+" ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			if (grille.get(i).get(j) != null) {
				System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(1)+"   "+grille.get(i).get(j).getId()+"   "+grille.get(i).get(j).getEst().getCote().charAt(1)+" ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			if (grille.get(i).get(j) != null) {
				System.out.print(grille.get(i).get(j).getOuest().getCote().charAt(0)+"       "+grille.get(i).get(j).getEst().getCote().charAt(2)+" ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=0;j<largeur;j++){
			if (grille.get(i).get(j) != null) {
				System.out.print("  "+grille.get(i).get(j).getSud().getCote().charAt(2)+"|"+grille.get(i).get(j).getSud().getCote().charAt(1)+"|"+grille.get(i).get(j).getSud().getCote().charAt(0)+"   ");
			} else {
				System.out.print("          ");
			}
			
		}
	}

	/**
	 * affiche dans la console le plateau
	 */
	public void afficher(){
		for(int i=hauteur-1;i>=0;i--){
			this.afficherLigne(i);
		}
		System.out.println();
	}

	/**
	 * affiche dans la console une partie du tableau centrée sur la tuile voulue
	 * @param id l'identifiant de la tuile voulue
	 * @throws TileNotPlacedException
	 */
	public void afficher(int id) throws TileNotPlacedException {
		try {
			int[] xy = getXY(id);
			int x = xy[0];
			int y = xy[1];

			for (int i = y + 1; i >= y - 1; i--) {
				if (i == -1 || i == hauteur) {
					this.afficherLigneVide();
				} else {
					this.afficherPetiteLigne(i, x);
				}
			}
			System.out.println();
		} catch (TileNotPlacedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * affiche une ligne sans tuiles de taille 3
	 */
	public void afficherLigneVide() {
		String miniligne = "";
		
		for (int i = 0; i < 30; i++) {
			miniligne += " ";
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(miniligne);
		}
	}

	/**
	 * affiche 3 tuiles de la grille en ce centrant sur le x-ième élément de la ligne i
	 * @param i la ligne
	 * @param x le centre de l'affichage
	 */
	public void afficherPetiteLigne(int i, int x) {
		boolean videGauche = false;
		boolean videDroit = false;
		if (x == 0) {
			videGauche = true;
		}
		if (x == largeur - 1) {
			videDroit = true;
		}

		System.out.println();
		for(int j=x-1;j<=x+1;j++){
			Tuile tuile = null;
			if (j == x-1 && !videGauche || j == x+1 && !videDroit || j == x) {
				tuile = grille.get(i).get(j);
			}

			if (tuile != null) {
				System.out.print("  "+tuile.getNord().getCote().charAt(0)+"|"+tuile.getNord().getCote().charAt(1)+"|"+tuile.getNord().getCote().charAt(2)+"   ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=x-1;j<=x+1;j++){
			Tuile tuile = null;
			if (j == x-1 && !videGauche || j == x+1 && !videDroit || j == x) {
				tuile = grille.get(i).get(j);
			}

			if (tuile != null) {
				System.out.print(tuile.getOuest().getCote().charAt(2)+"       "+tuile.getEst().getCote().charAt(0)+" ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=x-1;j<=x+1;j++){
			Tuile tuile = null;
			if (j == x-1 && !videGauche || j == x+1 && !videDroit || j == x) {
				tuile = grille.get(i).get(j);
			}

			if (tuile != null) {
				String space = "  ";
				if (tuile.getId() < 10) {
					space+=" ";
				}
				System.out.print(tuile.getOuest().getCote().charAt(1)+space+tuile.getId()+"   "+tuile.getEst().getCote().charAt(1)+" ");

			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=x-1;j<=x+1;j++){
			Tuile tuile = null;
			if (j == x-1 && !videGauche || j == x+1 && !videDroit || j == x) {
				tuile = grille.get(i).get(j);
			}

			if (tuile != null) {
				System.out.print(tuile.getOuest().getCote().charAt(0)+"       "+tuile.getEst().getCote().charAt(2)+" ");
			} else {
				System.out.print("          ");
			}
			
		}
		System.out.println();
		for(int j=x-1;j<=x+1;j++){
			Tuile tuile = null;
			if (j == x-1 && !videGauche || j == x+1 && !videDroit || j == x) {
				tuile = grille.get(i).get(j);
			}

			if (tuile != null) {
				System.out.print("  "+tuile.getSud().getCote().charAt(2)+"|"+tuile.getSud().getCote().charAt(1)+"|"+tuile.getSud().getCote().charAt(0)+"   ");
			} else {
				System.out.print("          ");
			}
			
		}
	}

	/**
	 * renvoie les coordonnées relatives à la grille de la tuile d'indice id
	 * @param id l'identifiant de la tuile voulue
	 * @return les coordonnées sous la forme [x, y]
	 */
	public int[] getXY(int id) throws TileNotPlacedException {
		int[] tab = {-1, -1};
		boolean found = false;
			for (int i = 0; i < hauteur; i++) {

				for (int j = 0; j < largeur; j++) {
					if (grille.get(i).get(j) != null && grille.get(i).get(j).getId() == id) {
						tab = new int[2];
						tab[0] = j;
						tab[1] = i;
						found = true;
						break;
					}
				}
				if (found) {
					break;
				}
			}
			if (tab[0] == -1 || tab[1] == -1) {
				throw new TileNotPlacedException("La tuile d'indice "+id+"n'a pas été placée");
			} 
			else {
				return tab;
			}
	
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
		hauteur++;
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
		hauteur++;
	}

	/**
	 * agrandit le plateau d'une colonne vers la droite en conservant les tuiles déjà placées
	 */
	private void agrandirDroite() {
		for (int i = 0; i < hauteur; i++) {
			grille.get(i).add(null);
		}
		largeur++;
	}

	/**
	 * agrandit le plateau d'une colonne vers la gauche en conservant les tuiles déjà placées
	 */
	private void agrandirGauche() {
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
	 * Fonction de test, affiche la grille dans un état plus brut
	 */
	public void printGrille() {
		System.out.print("[");
		for(ArrayList<Tuile> list : grille) {
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

	public boolean placee(int id){
		for(int i=0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				if(grille.get(i).get(j)!=null&&grille.get(i).get(j).getId()==id){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Exception active quand on cherche l'id d'une tuile non placée sur le plateau
	 */
	public class TileNotPlacedException extends RuntimeException {

		TileNotPlacedException(String msg) {
			super(msg);
		}
	}


}