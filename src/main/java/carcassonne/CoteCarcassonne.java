package carcassonne;
import common.Cote;

public class CoteCarcassonne extends Cote{

    private String cote = ""; // Type de terrain
    
    /**
     * Crée un nouveau coté avec un certain type de terrain
     * @param terrain type de terrain du coté, il ne peut être qu'une Route, une Ville ou un Champ 
     * @throws InvalidTerrainException exception levée si le type de terrain donné en paramètre n'est pas valable
     */
    CoteCarcassonne(String terrain) throws InvalidTerrainException{
        if(terrain.equals("Route")||terrain.equals("Ville")||terrain.equals("Champ")){
            cote = terrain;
        }
        else{
            throw new InvalidTerrainException("Les terrains accéptés sont \"Route\", \"Ville\" et \"Champ\".");
        }
    }

    /**
     * Crée une copie du coté c
     * @param c cote à copier
     */
    CoteCarcassonne(CoteCarcassonne c){
        this.cote=c.cote;
    }

    /**
     * @return renvoie le type de terrain du coté
     */
    public String getCote(){
        return cote;
    }

    private class InvalidTerrainException extends RuntimeException{
        InvalidTerrainException(String msg){
            super(msg);
        }
    }
}
