package personnages;

import equipement.Chaudron;

public class Druide {
    private String nom;
    private int force;
    private Chaudron chaudron;

    public Druide(String nom, int force) {
        this.nom = nom;
        this.force = force;
        this.chaudron = new Chaudron();
    }

    public String getNom() {
        return nom;
    }

    public void parler(String texte) {
        System.out.println(prendreParole() + "\"" + texte + "\"");
    }

    private String prendreParole() {
        return "Le Druide " + nom + " : ";
    }

    public void fabriquerPotion(int quantite) {
        chaudron.remplirChaudron(quantite, force);
        parler("J'ai concocté " + quantite + " doses de potion magique. Elle a une force de " + force + ".");
    }

    public void boosterGaulois(Gaulois gaulois) {
        if (gaulois.getNom().equals("Obélix")) {
            parler("Non, " + gaulois.getNom() + " Non !... Et tu le sais très bien !");
        } else if (chaudron.resterPotion()) {
            parler("Tiens " + gaulois.getNom() + " un peu de potion magique.");
            gaulois.boirePotion(chaudron.getForcePotion());
            chaudron.puiserPotion();
        }
    }
}