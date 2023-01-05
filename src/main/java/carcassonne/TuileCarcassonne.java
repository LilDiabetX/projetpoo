package carcassonne;
import common.Tuile;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TuileCarcassonne extends Tuile{
    
    /**
     * nombre total de tuiles
     */
    private static int nbTuiles = 0;
    /**
     * numéro d'identification de la tuile
     */
	private int id;

    /**
     * cotés de la tuile, le coté à l'indice 0 correspond au coté nord, celui à l'indice 1 le coté est, celui à l'indice 2 le coté sud et celui à l'indice 3 le coté ouest
     */
	private CoteCarcassonne[] cotes;

    /**
     * indique la présence ou non d'une abbaye sur la tuile
     */
    private boolean abbaye; 

    /**
     * indique la présence d'un partisan sur la tuile
     */
    private boolean pion = false;

    /**
     * Indique à qui appartient l'éventuel pion sur la Tuile
     */
    JoueurCarcassonne joueur = null;

    /**
     * illustration de la tuile
     */
    private BufferedImage image; 

    /**
     * crée une tuile existante dans le jeu de plateau Carcassonne et lui associe l'image correspondante
     * @param typeTuile numéro indiquant quelle tuile du jeu doit être créée
     * @throws InvalidTileTypeException lance une exception si on essaie de créer une tuile dont le type n'est pas implémenté
     */
    TuileCarcassonne(int typeTuile) throws InvalidTileTypeException{
        try{
            cotes = new CoteCarcassonne[4];
            switch(typeTuile){
                case 0:
                for(int i=0;i<4;i++){
                    if(i!=2){
                        cotes[i] = new CoteCarcassonne("Champ");
                    }
                    else{
                        cotes[i] = new CoteCarcassonne("Route");
                    }
                }
                abbaye = true;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/0.png"));
                break;

                case 1:
                for(int i=0;i<4;i++){
                    cotes[i] = new CoteCarcassonne("Champ");
                }
                abbaye = true;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/1.png"));
                break;

                case 2:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Champ");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/2.png"));
                break;

                case 3:
                cotes[0] = new CoteCarcassonne("Ville");
                for(int i=1;i<4;i++){
                    cotes[i] = new CoteCarcassonne("Champ");
                }
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/3.png"));
                break;

                case 4:
                for(int i=0;i<4;i++){
                    if(i%2==0){
                        cotes[i] = new CoteCarcassonne("Champ");
                    }
                    else{
                        cotes[i] = new CoteCarcassonne("Ville");
                    }
                }
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/4.png"));
                break;

                case 5:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Champ");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/5.png"));
                break;

                case 6:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Champ");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/6.png"));
                break;

                case 7:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/7.png"));
                break;

                case 8:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Ville");
                cotes[2] = new CoteCarcassonne("Champ");
                cotes[3] = new CoteCarcassonne("Champ");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/8.png"));
                break;

                case 9:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Ville");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/9.png"));
                break;

                case 10:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Ville");
                cotes[2] = new CoteCarcassonne("Champ");
                cotes[3] = new CoteCarcassonne("Ville");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/10.png"));
                break;

                case 11:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Ville");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Ville");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/11.png"));
                break;
                
                case 12:
                cotes[0] = new CoteCarcassonne("Route");
                cotes[1] = new CoteCarcassonne("Champ");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Champ");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/12.png"));
                break;

                case 13:
                cotes[0] = new CoteCarcassonne("Champ");
                cotes[1] = new CoteCarcassonne("Champ");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/13.png"));
                break;

                case 14:
                cotes[0] = new CoteCarcassonne("Champ");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/14.png"));
                break;

                case 15:
                cotes[0] = new CoteCarcassonne("Route");
                cotes[1] = new CoteCarcassonne("Route");
                cotes[2] = new CoteCarcassonne("Route");
                cotes[3] = new CoteCarcassonne("Route");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/15.png"));
                break;

                case 16:
                cotes[0] = new CoteCarcassonne("Ville");
                cotes[1] = new CoteCarcassonne("Ville");
                cotes[2] = new CoteCarcassonne("Ville");
                cotes[3] = new CoteCarcassonne("Ville");
                abbaye = false;
                id = nbTuiles;
                nbTuiles++;
                image = ImageIO.read(new File("src/main/ressources/imagesTuilesCarca/8.png"));
                break;

                default:
                throw new InvalidTileTypeException("Le type de tuile donné en paramètre n'est pas valide (doit être compris entre 0 et 16)");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Crée une copie de la tuile t
     * @param t Tuile à copier
     */
    TuileCarcassonne(TuileCarcassonne t){
        cotes = new CoteCarcassonne[4];
        for(int i=0;i<4;i++){
            cotes[i]=new CoteCarcassonne(t.cotes[i]);
        }
        image = t.getImage();
    }

    /**
     * @return renvoie le coté nord de la tuile
     */
    public CoteCarcassonne getNord(){
        return cotes[0];
    }
    /**
     * @return renvoie le coté sud de la tuile
     */
	public CoteCarcassonne getSud(){
        return cotes[2];
    }
    /**
     * @return renvoie le coté est de la tuile
     */
	public CoteCarcassonne getEst(){
        return cotes[1];
    }
    /**
     * @return renvoie le coté ouest de la tuile
     */
	public CoteCarcassonne getOuest(){
        return cotes[3];
    }

    /**
     * @return renvoie si une abbaye se trouve sur la tuile
     */
    public boolean getAbbaye(){
        return abbaye;
    }

    /**
     * getter
     * @return renvoie vrai si un pion se trouve sur la tuile
     */
    public boolean getPion(){
        return pion;
    }

    /**
     * getter
     * @return renvoie le joueur à qui appartient l'éventuel pion sur la tuile
     */
    public JoueurCarcassonne getJoueur(){
        return joueur;
    }

    /**
     * setter
     * place un partisan sur la tuile
     * @param j joueur à qui appartient le pion posé
     */
    public void setPion(JoueurCarcassonne j){
        pion = true;
        joueur = j;
    }

	public void afficher(){
        
    }

    /**
     * tourne la tuile d'un quart de tour vers la gauche
     */
	public void tournerGauche(){
        CoteCarcassonne temp = new CoteCarcassonne(cotes[0].getCote());
        cotes[0] = new CoteCarcassonne(cotes[1].getCote());
        cotes[1] = new CoteCarcassonne(cotes[2].getCote());
        cotes[2] = new CoteCarcassonne(cotes[3].getCote());
        cotes[3] = temp;
    }

    /**
     * tourne la tuile d'un quart de tour vers la droite
     */
	public void tournerDroite(){
        CoteCarcassonne temp = new CoteCarcassonne(cotes[0].getCote());
        cotes[0] = new CoteCarcassonne(cotes[3].getCote());
        cotes[3] = new CoteCarcassonne(cotes[2].getCote());
        cotes[2] = new CoteCarcassonne(cotes[1].getCote());
        cotes[1] = temp;
    }

    /**
     * @return renvoie le numéro d'identification de la tuile
     */
    public int getId(){
        return id;
    }

    /**
     * met l'attribut posee à vrai
     */
    public void setPosee(){
        super.posee=true;
    }

    private class InvalidTileTypeException extends RuntimeException{
        InvalidTileTypeException(String msg){
            super(msg);
        }
    }

    public static int getNbTuiles() {
        return nbTuiles;
    }

    public CoteCarcassonne[] getCotes() {
        return cotes;
    }

    public BufferedImage getImage() {
        //System.out.println(this.image + " getter");
        return this.image;
    }

    /**
     * place un pion sur la tuile
     * @param j joueur à qui appartient le pion
     */
    public void placerPion(JoueurCarcassonne j){
        pion = true;
        joueur = j;
    }
}
