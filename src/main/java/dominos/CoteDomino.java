package dominos;

import java.util.Random;
import common.Cote;


public class CoteDomino extends Cote{
	private String cote = "";

	/**
	 * Crée un nouveau coté aléatoire de Domino de la forme "123" avec des chiffres compris entre 0 et 4
	 */
	CoteDomino(){
		Random rand = new Random();
		for(int i=0;i<3;i++){
			this.cote+=rand.nextInt(5);
		}
	}

	/**
	 * Crée la copie exacte ou inverse d'un coté de Domino
	 * @param c Coté de domino à copier
	 * @param inverse faux si on veut la copie exacte de c et vrai si on veut son inverse ("123" donne "321")
	 */
	CoteDomino(CoteDomino c,boolean inverse){
		if(!inverse){
			this.cote=c.cote;
		}
		else{
			for(int i=2;i>=0;i--){
				this.cote+=c.cote.charAt(i);
			}
		}
	}

	/**
	 * @return renvoie les chiffres du coté de Domino séparés par des |
	 */
	public String toString(){
		return (cote.charAt(0)+"|"+cote.charAt(1)+"|"+cote.charAt(2));
	}

	/**
	 * @return renvoie le coté sous sa forme "123"
	 */
	public String getCote(){
		return cote;
	}

	/**
	 * @return renvoie le chiffre à la i-ème position du coté
	 */
	public Character getCote(int i) throws InvalidIndexException{
		if(i>=0&&i<3){
			return this.cote.charAt(i);
		}
		throw new InvalidIndexException("L'index recherché est invalide");
	}

	private class InvalidIndexException extends RuntimeException{
		InvalidIndexException(String msg){
			super(msg);
		}
	}
}