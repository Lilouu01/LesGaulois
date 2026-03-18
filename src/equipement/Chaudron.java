package equipement;

public class Chaudron {
    private int quantite;
    private int forcePotion;

    public Chaudron() {
        this.quantite = 0;
        this.forcePotion = 0;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getForcePotion() {
        return forcePotion;
    }

    public boolean resterPotion() {
        return quantite > 0;
    }

    public void remplirChaudron(int quantite, int forcePotion) {
        this.quantite = quantite;
        this.forcePotion = forcePotion;
    }

    public void puiserPotion() {
        if (quantite > 0) {
            quantite--;
        }
    }
}