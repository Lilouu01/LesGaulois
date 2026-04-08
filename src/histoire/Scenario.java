package histoire;

import personnages.Gaulois;
import personnages.Romain;
import personnages.Druide;
import objets.Equipement;
import village_gaulois.Musee;

public class Scenario {

    public static void main(String[] args) {
        Druide druide = new Druide("Panoramix", 5);
        druide.parler("Je vais aller préparer une petite potion...");
        druide.fabriquerPotion(5);
        Gaulois obelix = new Gaulois("Obélix", 25);
        Gaulois asterix = new Gaulois("Astérix", 8);
        druide.boosterGaulois(obelix);
        obelix.parler("Par Bélénos, ce n'est pas juste !");
        druide.boosterGaulois(asterix);
        asterix.parler("Bonjour");
        Romain minus = new Romain("Minus", 6);
        Romain milexcus = new Romain("Milexcus", 8);
        minus.sEquiper(Equipement.BOUCLIER);
        minus.sEquiper(Equipement.CASQUE);
        milexcus.sEquiper(Equipement.CASQUE);
        minus.parler("UN GAU... UN GAUGAU...");

        // Bug b corrigé : >= 0 au lieu de > 0 pour prendre en compte l'égalité
        do {
            asterix.frapper(minus);
            // Bug d : si égalité (force absorbée), Minus nargue et Obélix aide
            if (minus.getForce() > 0) {
                minus.parler("Alors Asterix on fait moins le malin face à mon équipement, Hi ! Hi !");
                obelix.parler("Asterix, tu veux un peu d'aide ?");
                obelix.frapper(minus);
            }
        } while (minus.getForce() > 0);

        // PARTIE 5 : décommenté
        milexcus.parler("UN GAU... UN GAUGAU...");
        do {
            obelix.frapper(milexcus);
        } while (milexcus.getForce() > 0);

        Musee musee = new Musee();
        obelix.faireUneDonnation(musee);
        System.out.println(musee.extraireInstructionsOCaml());
    }
}