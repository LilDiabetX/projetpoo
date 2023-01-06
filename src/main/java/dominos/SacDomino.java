package dominos;

import java.util.Random;
import common.Sac;

public class SacDomino extends Sac{
	
	/**
	 * sac de tuiles
	 */
	private TuileDomino[] sac;

	/**
	 * Crée un sac de 50 dominos aléatoires
	 */
	SacDomino(){
		sac = new TuileDomino[50];			// On initialise le sac à une taille de 50 tuiles
		CoteDomino[] cotes = new CoteDomino[30];			// On souhaite créer 15 cotés de tuiles aléatoires et leur inverse pour créer 50 tuiles qui pourront donc être posées les unes à coté des autres
		for(int i=0;i<15;i++){
			cotes[i]=new CoteDomino();			// On crée les 15 cotés aléatoires
			cotes[i+15]=new CoteDomino(cotes[i],true);			// Et leur inverse
		}
		int k=0;
		int i=0;
		while(k<50){	
			sac[k]=new TuileDomino(cotes[i%30],cotes[(i+1)%30],cotes[(i+2)%30],cotes[(i+3)%30]);		// On remplit le sac avec des Tuiles créées avec les cotés créés précédemment
			k++;
			i+=4;
		}
		melange();			// On mélange le sac
	}

	/**
	 * mélange aléatoirement les dominos du sac
	 */
	public void melange(){
		Random rand = new Random();

		for(int i=0;i<sac.length;i++){
			int randomIndexSwap = rand.nextInt(sac.length);
			TuileDomino temp = new TuileDomino(sac[randomIndexSwap],sac[randomIndexSwap].getId());
			sac[randomIndexSwap] = new TuileDomino(sac[i],sac[i].getId());
			sac[i] = temp;
		}
	}

	/**
	 * @return renvoie le contenu du sac
	 */
	public TuileDomino[] getSac(){
		return sac;
	}

	/**
	 * @return renvoie la tuile à l'indice i du sac
	 */
	public TuileDomino getSac(int i) throws InvalidIndexException{
		if(i>=0&&i<sac.length){
			return sac[i];
		}
		throw new InvalidIndexException("L'indice recherché n'est pas valide");
	}

	/**
	 * @return renvoie la taille du sac
	 */
	public int size(){
		return sac.length;
	}

	/**
	 * @return renvoie vrai si le sac est vide et faux sinon
	 */
	public boolean estVide(){
        for(int i=0;i<sac.length;i++){
            if(sac[i]!=null){
                return false;
            }
        }
        return true;
    }

	private class InvalidIndexException extends RuntimeException{
		InvalidIndexException(String message){
			super(message);
		}
	}

}