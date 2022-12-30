package dominos;

public class Test{
	public static void main(String[] args){
		
		/*
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
		*/
		
		/* 
		System.out.println();
		SacDomino sac = new SacDomino();
		for(int i=0;i<50;i++){
			sac.getSac(i).afficher();
		}
		*/
		/*
		//sac.getSac(50).afficher();
		CoteDomino c1 = new CoteDomino("123");
		CoteDomino c2 = new CoteDomino("321");
		CoteDomino c3 = new CoteDomino("111");
		TuileDomino t0 = new TuileDomino(c3, c1, c3, c1);
		
		TuileDomino t1 = new TuileDomino(c1, c3, c2, c1);
		
		PlateauDomino p = new PlateauDomino(t0);
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				p.placer(i,j,new TuileDomino());
			}
		}
		
		System.out.println("\n-------------------------------------------------");
		p.placer(-1, 0, t1);
		//p.afficher();
		System.out.println("\n-------------------------------------------------");
		TuileDomino t2 = new TuileDomino(t0);
		p.placer(-1, -1, t2);
		//p.afficher();
		System.out.println("\n-------------------------------------------------");
		TuileDomino t3 = new TuileDomino(t1);
		p.placer(1, 0, t3);
		//p.afficher();
		t3.tournerDroite();
		p.placer(1, 0, t3);
		p.afficher();
		System.out.println("\n-------------------------------------------------");
		TuileDomino t4 = new TuileDomino(t3);
		p.placer(1, 1, t4);
		p.afficher();
		System.out.println("\n-------------------------------------------------");
		TuileDomino t5 = new TuileDomino(t1);
		p.placer(-2, 0, t5);
		p.afficher();
		System.out.println("\n-------------------------------------------------");
		TuileDomino t6 = new TuileDomino(t2);
		t6.tournerDroite();
		p.placer(0, 1, t6);
		p.afficher();
		System.out.println("\n-------------------------------------------------");
		p.afficher(6);
		System.out.println("\n-------------------------------------------------");
		TuileDomino t7 = new TuileDomino(t4);
		p.placer(-2, 1, t7);
		p.afficher();
		System.out.println("\n-------------------------------------------------");
		p.afficher(7);

		System.out.println();
		System.out.println();
		System.out.println();
		*/
		 
		ModelDomino model = new ModelDomino(2);
		model.play();
		
		/*CoteDomino c1 = new CoteDomino("104");
		CoteDomino c2 = new CoteDomino("313");
		CoteDomino c3 = new CoteDomino("000");

		TuileDomino t0 = new TuileDomino(c1, c3, c3, c3);
		TuileDomino t2 = new TuileDomino(c2, new CoteDomino(c1, true), c3, c3);
		TuileDomino t3 = new TuileDomino(c3, new CoteDomino(c2, true), c3, c3);

		PlateauDomino p = new PlateauDomino(t0);

		p.afficher();

		t2.afficher();

		p.placer(0, 1, t2);

		p.placer(0, 2, t3);

		t3.afficher();
		System.out.println("-----------");

		p.afficher();/* */
	}
}