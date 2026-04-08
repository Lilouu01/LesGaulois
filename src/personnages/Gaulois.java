package personnages;

import objets.Equipement;
import village_gaulois.Musee;
import village_gaulois.Village;

public class Gaulois {
    private String nom;
    // private int force; // commenté comme demandé en partie 2
    private int force;
    private int nbTrophees;
    private Equipement[] trophees = new Equipement[100];
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

    // Ancienne prendreParole commentée, nouvelle version clean code
    private String prendreParole() {
        String texte = "Le gaulois " + nom + " : ";
        return texte;
    }

    public void boirePotion(int forcePotion) {
        effetPotion = forcePotion;
    }

    // Ancienne frapper commentée, nouvelle version TP3
    public void frapper(Romain romain) {
        System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
        // Bug e corrigé : force / 2 au lieu de force / 3
        Equipement[] tropheesBataille = romain.recevoirCoup((force / 2) * effetPotion);
        effetPotion--;
        if (effetPotion < 1) {
            effetPotion = 1;
        }
        for (int i = 0; tropheesBataille != null && i < tropheesBataille.length; i++, nbTrophees++) {
            this.trophees[nbTrophees] = tropheesBataille[i];
        }
    }

    // Ancienne toString commentée (comme demandé en partie 2)

    public void sePresenter() {
        if (village == null) {
            parler("Bonjour, je m'appelle " + nom + ". Je voyage de villages en villages.");
        } else if (village.getChef() == this) {
            parler("Bonjour, je m'appelle " + nom + ". Je suis le chef le village : " + village.getNom() + ".");
        } else {
            parler("Bonjour, je m'appelle " + nom + ". J'habite le village : " + village.getNom() + ".");
        }
    }

    // --- Partie 5c : faireUneDonnation ---
    public void faireUneDonnation(Musee musee) {
        if (nbTrophees > 0) {
            StringBuilder message = new StringBuilder("Je donne au musee tous mes trophees :");
            for (int i = 0; i < nbTrophees; i++) {
                if (trophees[i] != null) {
                    message.append("\n- ").append(trophees[i]);
                    musee.donnerTrophees(this, trophees[i]);
                    trophees[i] = null;
                }
            }
            nbTrophees = 0;
            parler(message.toString());
        }
    }
}