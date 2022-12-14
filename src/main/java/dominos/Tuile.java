

public class Tuile{
	private Cote nord;
	private Cote est;
	private Cote sud;
	private Cote ouest;

	Tuile(){
		nord = new Cote();
		est = new Cote();
		sud = new Cote();
		ouest = new Cote();
	}

	Tuile(Cote c){
		nord=new Cote(c);
		est = new Cote();
		sud = new Cote();
		ouest = new Cote();
	}

	public Cote getNord(){
		return nord;
	}

	public Cote getEst(){
		return est;
	}

	public Cote getOuest(){
		return ouest;
	}

	public Cote getSud(){
		return sud;
	}

	/**
	 * affiche dans la console la tuile sous forme de carré
	 */
	public void afficher(){
		String str=("  "+nord.getCote(0)+"|"+nord.getCote(1)+"|"+nord.getCote(2)+" ");
		str+=("\n"+ouest.getCote(2)+"       "+est.getCote(0));
		str+=("\n"+ouest.getCote(1)+"       "+est.getCote(1));
		str+=("\n"+ouest.getCote(0)+"       "+est.getCote(2));
		str+=("\n  "+sud.getCote(2)+"|"+sud.getCote(1)+"|"+sud.getCote(0)+" ");
		System.out.println(str);
	}

	/**
	 * tourne la tuile d'un quart de tour sur la gauche
	 */
	public void tournerGauche(){
		Cote temp = new Cote(nord);
		nord = new Cote(est);
		est = new Cote(sud);
		sud = new Cote(ouest);
		ouest = new Cote(temp);
	}

	/**
	 * tourne la tuile d'un quart de tour sur la droite
	 */
	public void tournerDroite(){
		Cote temp = new Cote(nord);
		nord = new Cote(ouest);
		ouest = new Cote(sud);
		sud = new Cote(est);
		est = new Cote(temp);
	}
}