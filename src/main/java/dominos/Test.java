package dominos;
public class Test{
	public static void main(String[] args){
		
		CoteDomino c = new CoteDomino();
		System.out.println(c);
		TuileDomino t = new TuileDomino();
		t.afficher();
		t.tournerGauche();
		System.out.println();
		
		t.afficher();
		t.tournerDroite();
		System.out.println();
		t.afficher();
		
		/*
		System.out.println();
		SacDomino sac = new SacDomino();
		for(int i=0;i<50;i++){
			sac.getSac(i).afficher();
		}
		*/
		//sac.getSac(50).afficher();
		/*
		Plateau p = new Plateau();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				p.placer(i,j,new Tuile());
			}
		}
		p.afficher();
		*/
	}
}