package personnages;

import objets.Equipement;

public class Romain {
    private String nom;
    private int force;
    private Equipement[] equipements = new Equipement[2];
    private int nbEquipement = 0;
    private String texte;

    public Romain(String nom, int force) {
        this.nom = nom;
        this.force = force;
        assert isInvariantVerified() : "Invariant : force >= 0";
    }

    public String getNom() {
        return nom;
    }

    // --- Getter force (demandé en partie 4) ---
    public int getForce() {
        return force;
    }

    public void parler(String message) {
        System.out.println(prendreParole() + "\"" + message + "\"");
    }

    private String prendreParole() {
        return "Le romain " + nom + " : ";
    }

    private boolean isInvariantVerified() {
        return force >= 0;
    }

    // Ancienne recevoirCoup mise en commentaire, nouvelle version TP3 :
    public Equipement[] recevoirCoup(int forceCoup) {
        Equipement[] equipementEjecte = null;
        forceCoup = calculerResistanceEquipement(forceCoup); // Bug a corrigé : nom en camelCase
        force -= forceCoup;

        // Bug a : le switch était inversé (case 0 disait "Aïe" alors qu'il devrait abandonner)
        // Correction : force <= 0 → abandonner, sinon → Aïe
        if (force <= 0) {
            force = 0;
            equipementEjecte = ejecterEquipement();
            parler("J'abandonne...");
        } else {
            parler("Aïe");
        }

        assert isInvariantVerified() : "Invariant : force >= 0";
        return equipementEjecte;
    }

    // Bug clean code S100 : renommé en camelCase + verbe
    private int calculerResistanceEquipement(int forceCoup) {
        texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
        int resistanceEquipement = 0;
        if (nbEquipement != 0) { // Bug S1940 : simplifié
            texte += "\nMais heureusement, grâce à mon équipement sa force a été ";
            for (int i = 0; i < nbEquipement; i++) { // Bug S127 : i++ dans le for
                // Bug S1125 : comparaison directe avec ==, pas .equals()
                if (equipements[i] != null && equipements[i] == Equipement.BOUCLIER) {
                    // Bug e : bouclier = 6 au lieu de 8
                    resistanceEquipement += 6;
                } else {
                    resistanceEquipement += 3; // Bug e : casque = 3 au lieu de 5
                }
            }
            // Bug S2757 : texte =+ était faux (assignait +resistanceEquipement)
            // Correction : vérifier si absorbé totalement ou partiellement
            if (forceCoup <= resistanceEquipement) {
                texte += "complètement absorbée.";
            } else {
                texte += "diminuée de " + resistanceEquipement + " !";
            }
        }
        parler(texte);
        forceCoup -= resistanceEquipement;
        if (forceCoup < 0) {
            forceCoup = 0;
        }
        return forceCoup;
    }

    private Equipement[] ejecterEquipement() {
        Equipement[] equipementEjecte = new Equipement[nbEquipement];
        System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
        int nbEquipementEjecte = 0;
        for (int i = 0; i < nbEquipement; i++) {
            if (equipements[i] != null) {  // Bug S3626 : supprimé continue, inversé la logique
                equipementEjecte[nbEquipementEjecte] = equipements[i];
                nbEquipementEjecte++;
                equipements[i] = null;
            }
        }
        nbEquipement = 0;
        return equipementEjecte;
    }

    public void sEquiper(Equipement equipement) {
        switch (nbEquipement) {
            case 2:
                System.out.println("Le soldat " + nom + " est déjà bien protégé !");
                break;
            case 1:
                if (equipements[0] != null && equipements[0].equals(equipement)) {
                    System.out.println("Le soldat " + nom + " possède déjà un " + equipement + " !");
                } else {
                    ajouterEquipement(equipement);
                }
                break;
            default:
                ajouterEquipement(equipement);
                break;
        }
    }

    private void ajouterEquipement(Equipement equipement) {
        equipements[nbEquipement] = equipement;
        nbEquipement++;
        System.out.println("Le soldat " + nom + " s'équipe avec " + equipement + ".");
    }

    public static void main(String[] args) {
        Romain minus = new Romain("Minus", 6);
        System.out.println(Equipement.CASQUE);
        System.out.println(Equipement.BOUCLIER);
        minus.sEquiper(Equipement.CASQUE);
        minus.sEquiper(Equipement.CASQUE);
        minus.sEquiper(Equipement.BOUCLIER);
        minus.sEquiper(Equipement.CASQUE);
    }
}