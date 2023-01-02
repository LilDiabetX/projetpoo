package dominos;

import java.util.ArrayList;

import common.*;

import java.util.Scanner;
import java.util.InputMismatchException;


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
     * dernière position sur le plateau
     */
    private int dernierePosition;

    /**
     * Constructeur
     * @param n le nombre de joueurs
     */
    public ModelDomino() {
        Scanner sc = new Scanner(System.in);
        sac = new SacDomino();
        plateau = new PlateauDomino(sac.getSac(tourDeJeu));
        dernierePosition = sac.getSac(tourDeJeu).getId();
        super.tourDeJeu++;
        boolean entier = false;
        boolean joueurs = false;
        while(!joueurs){
            while(!entier){
                System.out.println("Combien de joueurs souhaitent jouer ?");
                String choix = sc.next();
                try{
                    nbJoueurs = Integer.valueOf(choix);
                    entier = true;
                }
                catch(NumberFormatException e){
                    System.out.println("Veuillez entrer un entier supérieur à 1");
                }
            }
            if(nbJoueurs>1){
                joueurs = true;
            }
            else{
                System.out.println("Veuillez entrer un entier supérieur à 1");
                entier = false;
            }
        }
        tabJoueurs = new ArrayList<JoueurDomino>();
        for (int i = 0; i < nbJoueurs; i++) {
            boolean IA = false;
            while(!IA){
                System.out.println("Le joueur numéro "+i+" est il un humain ? (répondez par \"oui\" ou par \"non\")");
                switch(sc.next()){
                    case "oui":
                    tabJoueurs.add(new JoueurDomino(plateau,true));
                    IA = true;
                    break;

                    case "non":
                    tabJoueurs.add(new JoueurDomino(plateau,false));
                    IA = true;
                    break;

                    default:
                    System.out.println("Réponse invalide");
                }
            }
        }
        actuel = tabJoueurs.get(0);
    }

    /**
     * pioche une tuile dans le sac
     * @param joueur joueur qui pioche la tuile
     */
    public void piocher(JoueurDomino joueur) {
        joueur.setTuile(sac.getSac(tourDeJeu));
    }

    /**
     * vérifie si les conditions de victoire par abandon sont vérifiée
     * @return renvoie vrai si il ne reste plus qu'un joueur qui n'a pas abandonné et faux sinon
     */
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
     * fonction qui lance et joue une partie de domino textuel
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        while(!(super.tourDeJeu>=sac.size())&&!victoireParAbandon()){
            // déroulement de la partie
            actuel = tabJoueurs.get((super.tourDeJeu-1)%tabJoueurs.size());
            // on vérifie que le joueur n'a pas déjà abandonné
            if(!actuel.getAbandon()){
                System.out.println("C'est au tour du joueur n°"+(tourDeJeu-1)%tabJoueurs.size()); 
                piocher(actuel);
                // on vérifie si le joueur est humain
                if(actuel.getHumain()){ 
                           
                    boolean action = false; // détermine si une action a été effectuée durant le tour
                    // choix et exécution de l'action du joueur
                    while(!action){
                        plateau.afficher(dernierePosition);
                        System.out.println("Votre tuile :");
                        actuel.getTuile().afficher();
                        System.out.println("Voulez vous placer votre tuile, la tourner, vous déplacer sur le plateau, vous défaussez ou bien abandonner ? (répondez par \"placer\", \"tourner\", \"déplacer\", \"défausser\" ou \"abandonner\")");
                        String choix = sc.next();
                        switch(choix){

                            case "placer" : 
                            boolean placee = false; // détermine si le joueur a placé sa tuile ou s'il a finalement renoncé à la placer
                            while(!placee){
                                System.out.println("Où voulez vous placer votre tuile en sachant que la tuile d'origine est placée en 0/0 ? Pour rappel, la tuile d'origine est la tuile n°"+sac.getSac(0).getId()+". Indiquez l'abscisse :");
                                String abscisse = sc.next();
                                try {
                                    int x = Integer.valueOf(abscisse);
                                    System.out.println("Indiquez l'ordonnée : ");
                                    String ordonnee = sc.next();
                                    try {
                                        int y = Integer.valueOf(ordonnee);
                                        if(actuel.placerTuile(x, y)){        
                                            placee = true;
                                            action = true;
                                        }
                                        else{
                                            // donner au joueur la possiblilité de renoncer à placer sa tuile ou le laisser réessayer
                                            sc.reset();
                                            boolean renoncer = false; // détermine si le joueur renonce à placer sa tuile ou souhaite réessayer de la placer
                                            while(!renoncer){
                                                System.out.println("Souhaitez vous retourner au choix des actions ou bien entrer de nouvelles coordonnées ? Répondez par \"action\" ou par \"placer\".");
                                                switch(sc.next()){
                                                    case "action" : renoncer = true; placee = true; break;
                                                    case "placer" : renoncer = true; break;
                                                    default : System.out.println("Choix invalide"); break;
                                                }
                                            }
                                        }
                                    } 
                                    catch (NumberFormatException e) {
                                        System.out.println("Veuillez entrer un entier pour l'ordonnée. ");
                                    }
                                    
                                }
                                catch(NumberFormatException e){
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
                                    if(plateau.placee(id)){
                                        dernierePosition = id;
                                        deplace = true;
                                    }
                                    else{
                                        System.out.println("La tuile d'id "+id+" n'a pas été placée.");
                                    }
                                }
                                catch(InputMismatchException e){
                                    System.out.println("Veuillez entrer un entier.");
                                }

                            }
                            break;

                            case "défausser" : 
                            actuel.defausser();
                            action = true;
                            break;

                            case "abandonner" :
                            actuel.abandonner();
                            action = true;
                            break;

                            case "tourner" :
                            boolean tourne = false;
                            while(!tourne){
                                System.out.println("Voulez vous tourner votre tuile vers la gauche ou vers la droite ? Répondez par \"gauche\" ou par \"droite\".");
                                switch (sc.next()) {
                                    case "gauche" :
                                    actuel.getTuile().tournerGauche();
                                    tourne = true;
                                    break;

                                    case "droite" :
                                    actuel.getTuile().tournerDroite();
                                    tourne = true;
                                    break;

                                    default:
                                    System.out.println("Sens invalide, veuillez réessayer.");
                                    break;
                                }
                            }
                            break;

                            default : 
                            System.out.println("Veuillez choisir une action valable.");
                            break;
                        }
                    }
                    tourDeJeu++; // passage au joueur suivant   
                }
                else{ // C'est au tour d'une IA de jouer
                    actuel.getTuile().afficher();
                    // vérification qu'il y a une action possible
                    plateau.agrandirHaut();
                    plateau.agrandirBas();
                    plateau.agrandirGauche();
                    plateau.agrandirDroite();
                    boolean pose = false;
                    for(int i=0;i<4;i++){
                        if(!pose){
                            actuel.getTuile().tournerDroite();
                            if(actuel.placerIA()){
                                System.out.println("Tuile placée");
                                tourDeJeu++;
                                pose = true;
                            }
                        }
                    }
                    if(!pose){ // sinon, défausse de la tuile et passage au joueur suivant
                        System.out.println("Tuile défaussée");
                        actuel.defausser();
                        tourDeJeu++;
                    }
                }
            }
            else{
                tourDeJeu++;
            }
        }
        sc.close();
        if(victoireParAbandon()){
            // victoire du seul joueur qui n'a pas abandonné
            for(int i=0;i<tabJoueurs.size();i++){
                if(!tabJoueurs.get(i).getAbandon()){
                    System.out.println("Bravo au joueur n°"+i+". Vous avez gagné avec un score de : "+tabJoueurs.get(i).getScore());
                }
            }
        }
        else{
            // victoire du ou des joueur(s) qui a/ont le plus de points
            int scoreMax = 0;
            for(int i=0;i<tabJoueurs.size();i++){
                if(tabJoueurs.get(i).getScore()>scoreMax){
                    scoreMax=tabJoueurs.get(i).getScore();
                }
            }
            ArrayList<Integer> vainqueurs = new ArrayList<Integer>();
            for(int i=0;i<tabJoueurs.size();i++){
                if(tabJoueurs.get(i).getScore()==scoreMax){
                    vainqueurs.add(i);
                }
            }
            if(vainqueurs.size()==1){
                System.out.println("Bravo au joueur n°"+vainqueurs.get(0)+". Vous avez gagné avec un score de : "+scoreMax);
            }
            else{
                String gagnants = "";
                for(int i=0;i<vainqueurs.size();i++){
                    gagnants+=vainqueurs.get(i)+", ";
                }
                gagnants = gagnants.substring(0,gagnants.length()-3);
                System.out.println("Bravo aux joueurs n°"+gagnants+". Vous avez gagné avec un score de : "+scoreMax);
            }
        }
    }
    
}
