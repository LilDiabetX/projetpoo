import dominos.*;
import carcassonne.*;
import java.util.Scanner;

public class Launcher {
    
    private ModelCarcassonne modelCarca;
    private VueCarcassonne viewCarca;
    private ControleurCarcassonne controllerCarca;

    private ModelDomino modelDom;

    Launcher(){
        Scanner sc = new Scanner(System.in);
        boolean jeu = false;
        boolean mode = false;
        int nbJoueurs = 0;
        while(!jeu){
            System.out.println("À quel jeu souhaitez vous jouer ? Répondez par \"domino\" ou par \"carcassonne\".");
            switch(sc.next()){
                case "domino" :
                while(!mode){
                    System.out.println("Voulez vous jouer dans la console ou avec une interface graphique ? Répondez par \"console\" ou par \"graphique\".");
                    switch(sc.next()){
                        case "console" :
                        modelDom = new ModelDomino(); // paramètre : DominoView null
                        mode = true;
                        break;

                        case "graphique" :
                        modelDom = new ModelDomino(); // paramètre : new DominoView
                        mode = true;
                        break;

                        default :
                        System.out.println("Réponse invalide.");
                        break;
                    }
                }
                jeu = true;
                break;

                case "carcassonne" :
                modelCarca = new ModelCarcassonne();
                jeu = true;
                break;

                default : 
                System.out.println("Réponse invalide.");
                break;
            }
        }
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
            if(modelDom!=null){
                if(nbJoueurs>1){
                    joueurs = true;
                }
                else{
                    System.out.println("Veuillez entrer un entier supérieur à 1");
                    entier = false;
                }
            }
            else{
                if(nbJoueurs>1&&nbJoueurs<6){
                    joueurs = true;
                }
                else{
                    System.out.println("Veuillez entrer un entier supérieur à 1 et inférieur à 6");
                    entier = false;
                }
            }
        }
                
        for (int i = 0; i < nbJoueurs; i++) {
            boolean IA = false;
            while(!IA){
                System.out.println("Le joueur numéro "+i+" est il un humain ? (répondez par \"oui\" ou par \"non\")");
                switch(sc.next()){
                    case "oui":
                    if(modelDom!=null){
                        modelDom.getTabJoueur().add(new JoueurDomino(modelDom.getPlateau(),true));
                        
                    }
                    else{
                        modelCarca.getTabJoueur().add(new JoueurCarcassonne(modelCarca.getPlateau(),true));
                    }
                    IA = true;
                    break;

                    case "non":
                    if(modelDom!=null){
                        modelDom.getTabJoueur().add(new JoueurDomino(modelDom.getPlateau(),false));
                        
                    }
                    else{
                        modelCarca.getTabJoueur().add(new JoueurCarcassonne(modelCarca.getPlateau(),false));
                    }
                    IA = true;
                    break;

                    default:
                    System.out.println("Réponse invalide");
                }
            }
        }

        if(modelDom!=null){
            modelDom.setActuel(0);
            modelDom.play();
        }
        else{
            modelCarca.setActuel(0);
            viewCarca = new VueCarcassonne(modelCarca);
            controllerCarca = viewCarca.getController();
            viewCarca.setVisible(true);
        }
    }

    public static void main(String[] args){
        Launcher launcher = new Launcher();
    }

}
