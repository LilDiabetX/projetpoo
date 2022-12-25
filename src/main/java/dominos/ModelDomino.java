package dominos;

import java.util.ArrayList;

import common.*;

public class ModelDomino extends Model {

    /**
     * Constructeur
     * @param n le nombre de joueurs
     */
    public ModelDomino(int n) {
        super.sac = new SacDomino();
        plateau = new PlateauDomino((TuileDomino) sac.getSac(tourDeJeu));
        super.nbJoueurs = n;
        super.tabJoueurs = new ArrayList<Joueur>();
        for (int i = 0; i < n; i++) {
            tabJoueurs.add(new JoueurDomino(plateau));
        }
        super.actuel = tabJoueurs.get(0);
    }

    @Override
    public void piocher(Joueur joueur) {
        joueur.setTuile(sac.getSac(tourDeJeu));
    }

    @Override
    public void placer(Joueur joueur, int x, int y) {
        joueur.placerTuile(x, y);
    }

    /**
     * à faire
     * fonction qui lance le jeu
     */
    public void play() {
        
    }
    
}
