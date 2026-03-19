package equipement;

public class Chaudron {
    private int quantite = 0 ;
    private int forcePotion = 0;

    public int getQuantite() {
        return quantite;
    }

    public boolean resterPotion() {
        return quantite > 0;
    }

    public void remplirChaudron(int quantite, int forcePotion) {
        this.quantite = quantite;
        this.forcePotion = forcePotion;
    }

    
    public int prendreLouche() {
        quantite--;
        if (quantite < 1) {
        	forcePotion = 0;
        }
        return forcePotion;
        
        
    }
}