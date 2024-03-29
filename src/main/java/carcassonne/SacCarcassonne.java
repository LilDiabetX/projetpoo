package carcassonne;
import common.Sac;
import java.util.Random;

public class SacCarcassonne extends Sac {

    /**
     * contenu du sac
     */
    private TuileCarcassonne[] sac;

    /**
     * le nombre de tuiles restantes dans le sac
     */
    private int tuilesRestantes;

    /**
     * Crée un nouveau sac de 72 tuiles fidèles au jeu de plateau carcassonne à l'exception des tuiles avec un bouclier qui ont été remplacées par leur équivalent sans bouclier et de quelques tuiles dont la représentation dans ce jeu aurait été identique
     */
    SacCarcassonne(){
        int i = 0;
        sac = new TuileCarcassonne[72];
        for(int j=0;j<4;j++){
            sac[i] = new TuileCarcassonne(2); //la tuile de base dans carcassonne est de type 2 donc on met celles-ci en premier
            i++;
        }
        for(int j=0;j<2;j++){
            sac[i] = new TuileCarcassonne(0);
            i++;
        }
        for(int j=0;j<4;j++){
            sac[i] = new TuileCarcassonne(1);
            i++;
        }
        for(int j=0;j<5;j++){
            sac[i] = new TuileCarcassonne(3);
            i++;
        }
        for(int j=0;j<6;j++){
            sac[i] = new TuileCarcassonne(4);
            i++;
        }
        for(int j=0;j<3;j++){
            sac[i] = new TuileCarcassonne(5);
            i++;
        }
        for(int j=0;j<3;j++){
            sac[i] = new TuileCarcassonne(6);
            i++;
        }
        for(int j=0;j<3;j++){
            sac[i] = new TuileCarcassonne(7);
            i++;
        }
        for(int j=0;j<7;j++){
            sac[i] = new TuileCarcassonne(8);
            i++;
        }
        for(int j=0;j<5;j++){
            sac[i] = new TuileCarcassonne(9);
            i++;
        }
        for(int j=0;j<4;j++){
            sac[i] = new TuileCarcassonne(10);
            i++;
        }
        for(int j=0;j<3;j++){
            sac[i] = new TuileCarcassonne(11);
            i++;
        }
        for(int j=0;j<8;j++){
            sac[i] = new TuileCarcassonne(12);
            i++;
        }
        for(int j=0;j<9;j++){
            sac[i] = new TuileCarcassonne(13);
            i++;
        }
        for(int j=0;j<4;j++){
            sac[i] = new TuileCarcassonne(14);
            i++;
        }
        sac[i] = new TuileCarcassonne(15);
        i++;
        sac[i] = new TuileCarcassonne(16);
        i++;
        melange();
        tuilesRestantes = sac.length;
    }

    /**
     * mélange le contenu du sac
     */
    public void melange(){
        Random rand = new Random();

		for(int i=1;i<sac.length;i++){ //on ne mélange pas la première tuile avec le reste
			int randomIndexSwap = rand.nextInt(sac.length-1)+1;
			TuileCarcassonne temp = new TuileCarcassonne(sac[randomIndexSwap]);
			sac[randomIndexSwap] = new TuileCarcassonne(sac[i]);
			sac[i] = temp;
		}
    }

    /**
     * @return renvoie le contenu du sac
     */
    public TuileCarcassonne[] getSac(){
        return sac;
    }

    /**
     * @return renvoie et supprime du sac la tuile situé à l'indice i du sac
     * @throws InvalidIndexException
     */
    public TuileCarcassonne getSac(int i)throws InvalidIndexException{
        if(i>=0&&i<sac.length){
            TuileCarcassonne temp = new TuileCarcassonne(sac[i]);
            sac[i] = null;
            tuilesRestantes--;
			return temp;
		}
		throw new InvalidIndexException("L'indice recherché n'est pas valide");
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

    public int getTuilesRestantes() {
        return tuilesRestantes;
    }

    private class InvalidIndexException extends RuntimeException{
		InvalidIndexException(String message){
			super(message);
		}
	}

    
    
}
