package dominos;

import java.util.Random;

public class Cote{
	private int[] cote = new int[3];

	Cote(){
		Random rand = new Random();
		for(int i=0;i<3;i++){
			this.cote[i]=rand.nextInt(5);
		}
	}

	Cote(Cote c){
		for(int i=0;i<3;i++){
			this.cote[i]=c.cote[i];
		}
	}

	public String toString(){
		return (cote[0]+"|"+cote[1]+"|"+cote[2]);
	}

	public int getCote(int i){
		return this.cote[i];
	}
}