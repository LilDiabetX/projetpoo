public class Plateau{
	private Tuile[][] plateau;
	private int hauteur;
	private int largeur;

	Plateau(){
		this.plateau=new Tuile[3][3];
		this.hauteur=3;
		this.largeur=3;
	}

	/**
	 * place la tuile t au coordonnées (x,y) du plateau
	 * @param x abscisse à laquelle on veut placer la tuile
	 * @param y ordonnée à laquelle on veut placer la tuile
	 * @param t tuile à placer 
	 */
	public void placer(int x, int y,Tuile t){
		this.plateau[y][x]=t;
	}

	public void afficherLigne(int i){
		System.out.println();
		for(int j=0;j<this.largeur;j++){
			System.out.print("  "+this.plateau[i][j].getNord().getCote(0)+"|"+this.plateau[i][j].getNord().getCote(1)+"|"+this.plateau[i][j].getNord().getCote(2)+"   ");
		}
		System.out.println();
		for(int j=0;j<this.largeur;j++){
			System.out.print(this.plateau[i][j].getOuest().getCote(2)+"       "+this.plateau[i][j].getEst().getCote(0)+" ");
		}
		System.out.println();
		for(int j=0;j<this.largeur;j++){
			System.out.print(this.plateau[i][j].getOuest().getCote(1)+"       "+this.plateau[i][j].getEst().getCote(1)+" ");
		}
		System.out.println();
		for(int j=0;j<this.largeur;j++){
			System.out.print(this.plateau[i][j].getOuest().getCote(0)+"       "+this.plateau[i][j].getEst().getCote(2)+" ");
		}
		System.out.println();
		for(int j=0;j<this.largeur;j++){
			System.out.print("  "+this.plateau[i][j].getSud().getCote(2)+"|"+this.plateau[i][j].getSud().getCote(1)+"|"+this.plateau[i][j].getSud().getCote(0)+"   ");
		}
	}

	/**
	 * affiche dans la console le plateau
	 */
	public void afficher(){
		for(int i=this.hauteur-1;i>=0;i--){
			this.afficherLigne(i);
		}
		System.out.println();
	}

	/**
	 * agrandit le plateau d'une ligne vers le haut en conservant les tuiles déjà placées
	 */
	public void agrandirHaut(){
		Tuile[][] agrandit = new Tuile[this.hauteur+1][this.largeur];
		for(int i=0;i<this.hauteur;i++){
			for(int j=0;j<this.largeur;j++){
				agrandit[i+1][j]=this.plateau[i][j];
			}
		}
		this.plateau=agrandit;
		this.hauteur++;
	}
}