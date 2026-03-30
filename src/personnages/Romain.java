package personnages;

import objets.Equipement;

public class Romain {
    private String nom;
    private int force;

   
    private Equipement[] equipements = new Equipement[2];
    private int nbEquipement = 0;

    public Romain(String nom, int force) {
        this.nom = nom;
        this.force = force;
        assert isInvariantVerified() : "Invariant violé : force >= 0";
    }

    public String getNom() {
        return nom;
    }

    public void parler(String texte) {
        System.out.println(prendreParole() + "\"" + texte + "\"");
    }

    private String prendreParole() {
        return "Le romain " + nom + " : ";
    }

    
    private boolean isInvariantVerified() {
        return force >= 0;
    }

   
    public void recevoirCoup(int forceDuCoup) {
        assert forceDuCoup >= 0 : "Précondition violée : forceDuCoup doit être >= 0";
        int forceAvant = this.force;

        force = force - forceDuCoup;

        if (force < 1) {
            force = 0;
            parler("J'abandonne !");
        } else {
            parler("Aïe");
        }

        assert this.force <= forceAvant : "Postcondition violée : la force aurait dû diminuer";
        assert isInvariantVerified() : "Invariant violé : force >= 0";
    }

  
    public void sEquiper(Equipement equipement) {
        switch (nbEquipement) {
            case 2:
                System.out.println("Le soldat " + nom + " est déjà bien protégé !");
                break;
            case 1:
                if (equipements[0] == equipement) {
                    afficherDejaEquipe(equipement);
                } else {
                    ajouterEquipement(equipement);
                }
                break;
            default: // case 0
                ajouterEquipement(equipement);
                break;
        }
    }

    private void ajouterEquipement(Equipement equipement) {
        equipements[nbEquipement] = equipement;
        nbEquipement++;
        System.out.println("Le soldat " + nom + " s'équipe avec un " + equipement + ".");
    }

    private void afficherDejaEquipe(Equipement equipement) {
        System.out.println("Le soldat " + nom + " possède déjà un " + equipement + " !");
    }

    @Override
    public String toString() {
        return nom;
    }

    
    public static void main(String[] args) {
        // Test invariant (partie 2a) — lancer avec -ea
        Romain minus = new Romain("Minus", 6);

        // Test enum (partie 3a)
        System.out.println(Equipement.CASQUE);
        System.out.println(Equipement.BOUCLIER);

        // Test sEquiper (partie 3d)
        minus.sEquiper(Equipement.CASQUE);    // s'équipe avec un casque
        minus.sEquiper(Equipement.CASQUE);    // possède déjà un casque
        minus.sEquiper(Equipement.BOUCLIER);  // s'équipe avec un bouclier
        minus.sEquiper(Equipement.CASQUE);    // déjà bien protégé
    }
}