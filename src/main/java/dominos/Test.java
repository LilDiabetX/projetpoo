

public class Test{
	public static void main(String[] args){
		/*
		Cote c = new Cote();
		System.out.println(c);
		Tuile t = new Tuile();
		t.afficher();
		t.tournerGauche();
		System.out.println();
		t.afficher();
		t.tournerDroite();
		System.out.println();
		t.afficher();*/
		Plateau p = new Plateau();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				p.placer(i,j,new Tuile());
			}
		}
		p.afficher();
	}
}