package dominos;

import common.*;

public class ModelDomino extends Model {

    public ModelDomino(int n) {
        super.sac = new SacDomino();
        plateau = new PlateauDomino((TuileDomino) sac.getSac(tourDeJeu));
        super.nbJoueurs = n;
        super.tabJoueurs = new JoueurDomino[n];
        for (int i = 0; i < n; i++) {
            tabJoueurs[i] = new JoueurDomino(plateau);
        }
        super.actuel = tabJoueurs[0];
    }

    @Override
    public void piocher(Joueur joueur) {
        joueur.setTuile(sac.getSac(tourDeJeu));
    }

    @Override
    public void placer(Joueur joueur, int x, int y) {
        joueur.placerTuile(x, y);
    }

    public void play() {
        
    }
    
}
