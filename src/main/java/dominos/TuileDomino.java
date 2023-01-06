package dominos;

import common.Tuile;


public class TuileDomino extends Tuile{

	/**
	 * nombre total de tuiles
	 */
	private static int nbTuiles = 0;
	/**
	 * identifiant de la tuile
	 */
	private int id;

	// cotés de la tuile
	private CoteDomino nord;
	private CoteDomino est;
	private CoteDomino sud;
	private CoteDomino ouest;

	/**
	 * Crée un Domino avec quatre cotés aléatoires
	 */
	TuileDomino(){
		id = nbTuiles;
		nbTuiles++;
		nord = new CoteDomino();
		est = new CoteDomino();
		sud = new CoteDomino();
		ouest = new CoteDomino();
	}

	/**
	 * Crée une copie d'un domino
	 * @param t domino à copier
	 */
	TuileDomino(TuileDomino t, int id){
		this.id = id;
		nbTuiles++;
		this.nord=t.nord;
		this.sud=t.sud;
		this.est=t.est;
		this.ouest=t.ouest;
	}

	/**
	 * Crée un domino avec quatre coté déjà définis
	 * @param nord coté nord du domino
	 * @param sud coté sud du domino
	 * @param est coté est du domino
	 * @param ouest coté ouest du domino
	 */
	TuileDomino(CoteDomino nord,CoteDomino sud,CoteDomino est,CoteDomino ouest){
		id = nbTuiles;
		nbTuiles++;
		this.nord=nord;
		this.sud=sud;
		this.est=est;
		this.ouest=ouest;
	}

	/**
	 * @return renvoie le coté nord du domino
	 */
	public CoteDomino getNord(){
		return nord;
	}

	/**
	 * @return renvoie le coté est du domino
	 */
	public CoteDomino getEst(){
		return est;
	}

	/**
	 * @return renvoie le coté ouest du domino
	 */
	public CoteDomino getOuest(){
		return ouest;
	}

	/**
	 * @return renvoie le coté est du domino
	 */
	public CoteDomino getSud(){
		return sud;
	}

	/**
	 * @return renvoie l'id du domino
	 */
	public int getId(){
		return id;
	}

	/**
	 * affiche dans la console la tuile sous forme de carré. Méthode de test
	 */
	public void afficher(){
		String str=("  "+nord.getCote(0)+"|"+nord.getCote(1)+"|"+nord.getCote(2)+" ");
		str+=("\n"+ouest.getCote(2)+"       "+est.getCote(0));
		if(this.id<10){
			str+=("\n"+ouest.getCote(1)+"   "+this.id+"   "+est.getCote(1));
		}
		else{
			str+=("\n"+ouest.getCote(1)+"  "+this.id+"   "+est.getCote(1));
		}
		str+=("\n"+ouest.getCote(0)+"       "+est.getCote(2));
		str+=("\n  "+sud.getCote(2)+"|"+sud.getCote(1)+"|"+sud.getCote(0)+" ");
		System.out.println(str);
	}

	/**
	 * tourne la tuile d'un quart de tour sur la gauche
	 */
	public void tournerGauche() throws UnableToTurnException{
		if (!posee) {
			CoteDomino temp = new CoteDomino(nord,false);
		nord = new CoteDomino(est,false);
		est = new CoteDomino(sud,false);
		sud = new CoteDomino(ouest,false);
		ouest = new CoteDomino(temp,false);
		} else {
			throw new UnableToTurnException("La tuile est déjà placée");
		}
		
	}

	/**
	 * tourne la tuile d'un quart de tour sur la droite
	 */
	public void tournerDroite() throws UnableToTurnException {
		if (!posee) {
			CoteDomino temp = new CoteDomino(nord,false);
		nord = new CoteDomino(ouest,false);
		ouest = new CoteDomino(sud,false);
		sud = new CoteDomino(est,false);
		est = new CoteDomino(temp,false);
		} else {
			throw new UnableToTurnException("La tuile est déjà placée");
		}
		
	}

	/**
	 * indique que la tuile est posée sur le plateau
	 */
	public void setPosee() {
		super.posee = true;
	}

	/**
	 * Exception pour quand on ne peut pas faire pivoter une tuile
	 */
	private class UnableToTurnException extends RuntimeException {
		UnableToTurnException(String msg) {
			super(msg);
		}
	}
}