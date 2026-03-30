package personnages;

import village_gaulois.Village;

public class Gaulois {
    private String nom;
    private int force;
    private int effetPotion = 1;
    private Village village = null;

    public Gaulois(String nom, int force) {
        this.nom = nom;
        this.force = force;
    }

    public String getNom() {
        return nom;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public void parler(String texte) {
        System.out.println(prendreParole() + "\"" + texte + "\"");
    }

    private String prendreParole() {
        return "Le gaulois " + nom + " : ";
    }

    public void boirePotion(int forcePotion) {
        effetPotion = forcePotion;
    }

    public void frapper(Romain romain) {
        System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
        romain.recevoirCoup((force * effetPotion) / 3);

        if (effetPotion > 1) {
            effetPotion--;
        }
    }

    // --- TP2 partie 1f : sePresenter ---
    public void sePresenter() {
        if (village == null) {
            parler("Bonjour, je m'appelle " + nom + ". Je voyage de villages en villages.");
        } else if (village.getChef() == this) {
            parler("Bonjour, je m'appelle " + nom + ". Je suis le chef le village : " + village.getNom() + ".");
        } else {
            parler("Bonjour, je m'appelle " + nom + ". J'habite le village : " + village.getNom() + ".");
        }
    }

    @Override
    public String toString() {
        return nom;
    }
}