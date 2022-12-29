package dominos;

import java.util.ArrayList;

import common.*;

import java.util.Scanner;
import java.util.InputMismatchException;
import dominos.PlateauDomino.TileNotPlacedException;

public class ModelDomino extends Model {

    /**
     * plateau de jeu
     */
    private PlateauDomino plateau;

    /**
     * les joueurs
     */
    private ArrayList<JoueurDomino> tabJoueurs;

    /**
     * le joueur dont c'est le tour
     */
    private JoueurDomino actuel;

    /**
     * le sac contenant les tuiles du jeu
     */
    private SacDomino sac;

    /**
     * Constructeur
     * @param n le nombre de joueurs
     */
    public ModelDomino(int n) {
        Scanner sc = new Scanner(System.in);
        sac = new SacDomino();
        plateau = new PlateauDomino((TuileDomino) sac.getSac(tourDeJeu));
        super.nbJoueurs = n;
        tabJoueurs = new ArrayList<JoueurDomino>();
        for (int i = 0; i < n; i++) {
            System.out.println("Le joueur numéro "+i+" est il un humain ? (répondez par \"oui\" ou par \"non\")");
            boolean humain = sc.next().equals("oui");
            tabJoueurs.add(new JoueurDomino(plateau,humain));
        }
        actuel = tabJoueurs.get(0);
    }

    public void piocher(JoueurDomino joueur) {
        joueur.setTuile(sac.getSac(tourDeJeu));
    }

    public void placer(JoueurDomino joueur, int x, int y) {
        joueur.placerTuile(x, y);
    }

    public boolean victoireParAbandon(){
        int joueursEnLice = tabJoueurs.size();
        for(int i=0;i<tabJoueurs.size();i++){
            if(tabJoueurs.get(i).getAbandon()){
                joueursEnLice--;
            }
        }
        return joueursEnLice==1;
    }

    /**
     * à faire
     * fonction qui lance le jeu
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        while(!sac.estVide()&&!victoireParAbandon()){
            // déroulement de la partie
            actuel = tabJoueurs.get(super.tourDeJeu%tabJoueurs.size());
            // on vérifie que le joueur n'a pas déjà abandonné
            if(!actuel.getAbandon()){
                piocher(actuel);
                // on vérifie si le joueur est humain
                if(actuel.getHumain()){
                    plateau.afficher(plateau.getDerniere());
                    System.out.println("Votre tuile :");
                    actuel.getTuile().afficher();
                    boolean action = false; // détermine si une action a été effectuée durant le tour
                    // choix et exécution de l'action du joueur
                    while(!action){
                        System.out.println("Voulez vous placer votre tuile, vous déplacer sur le plateau, vous défaussez ou bien abandonner ? (répondez par \"placer\", \"déplacer\", \"défausser\" ou \"abandonner\")");
                        sc.reset();
                        String choix = sc.nextLine();
                        switch(choix){

                            case "placer" : 
                            boolean placee = false; // détermine si le joueur a placé sa tuile ou s'il a finalement renoncé à la placer
                            while(!placee){
                                System.out.println("Où voulez vous placer votre tuile en sachant que la tuile d'origine est placée en 0/0 ? Indiquez l'abscisse :");
                                try {
                                    int x = sc.nextInt();
                                    System.out.println("Indiquez l'ordonnée : ");
                                    try {
                                        int y = sc.nextInt();
                                        if(actuel.placerTuile(x, y)){
                                            System.out.println("C'est au tour du joueur n°"+(tourDeJeu+1)%tabJoueurs.size());
                                            placee = true;
                                            action = true;
                                        }
                                        else{
                                            // donner au joueur la possiblilité de renoncer à placer sa tuile ou le laisser réessayer
                                            sc.reset();
                                            boolean renoncer = false; // détermine si le joueur renonce à placer sa tuile ou souhaite réessayer de la placer
                                            while(!renoncer){
                                                System.out.println("Souhaitez vous retourner au choix des actions ou bien entrer de nouvelles coordonnées ? Répondez par \"action\" ou par \"placer\".");
                                                switch(sc.nextLine()){
                                                    case "action" : renoncer = true; placee = true; break;
                                                    case "placer" : renoncer = true; break;
                                                    default : System.out.println("Choix invalide"); break;
                                                }
                                            }
                                        }
                                    } 
                                    catch (InputMismatchException e) {
                                        System.out.println("Veuillez entrer un entier pour l'ordonnée. ");
                                    }
                                    
                                }
                                catch(InputMismatchException e){
                                    System.out.println("Veuillez entrer un entier pour l'abscisse. ");
                                }
                            }
                            break;

                            case "déplacer" : 
                            boolean deplace = false; // détermine si le joueur s'est déplacé ou bien s'il a renoncé à se déplacer
                            while(!deplace){
                                System.out.println("Où souhaitez vous vous déplacer ? Entrez l'id de la tuile sur laquelle vous voulez vous centrer : ");
                                try{
                                    int id = sc.nextInt();
                                    try{
                                        plateau.afficher(id);
                                        deplace = true;
                                    }
                                    catch(TileNotPlacedException e){
                                        System.out.println("La tuile d'id "+id+" n'a pas été placée.");
                                    }
                                }
                                catch(InputMismatchException e){
                                    System.out.println("Veuillez entrer un entier.");
                                }

                            }

                            case "défausser" : 
                            actuel.defausser();
                            System.out.println("C'est au tour du joueur n°"+(tourDeJeu+1)%tabJoueurs.size());
                            action = true;

                            case "abandonner" :
                            actuel.abandonner();
                            System.out.println("C'est au tour du joueur n°"+(tourDeJeu+1)%tabJoueurs.size());
                            action = true;

                            default : 
                            System.out.println("Veuillez choisir une action valable.");
                        }
                    }
                    tourDeJeu++; // passage au joueur suivant   
                }
                else{ // C'est au tour d'une IA de jouer
                    // vérification qu'il y a une action possible
                    if(actuel.placerIA()){
                        tourDeJeu++;
                    }
                    else{ // sinon, défausse de la tuile et passage au joueur suivant
                        actuel.defausser();
                        tourDeJeu++;
                    }
                }
            }
        }
        if(victoireParAbandon()){
            // victoire du seul joueur qui n'a pas abandonné
        }
        else{
            // victoire du joueur qui a le plus de points
        }
    }
    
}
