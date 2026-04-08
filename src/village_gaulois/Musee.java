package village_gaulois;

import objets.Equipement;
import objets.Trophee;
import personnages.Gaulois;

public class Musee {
    private Trophee[] trophees = new Trophee[200];
    private int nbTrophee = 0;

    // --- Partie 5b : donnerTrophees ---
    public void donnerTrophees(Gaulois gaulois, Equipement equipement) {
        trophees[nbTrophee] = new Trophee(gaulois, equipement);
        nbTrophee++;
    }

    // --- Partie 5 point 2 : extraireInstructionsOCaml ---
    public String extraireInstructionsOCaml() {
        StringBuilder sb = new StringBuilder("let musee = [\n");
        for (int i = 0; i < nbTrophee; i++) {
            sb.append("\t\"").append(trophees[i].donnerNom()).append("\", \"")
              .append(trophees[i].getEquipement()).append("\"");
            if (i < nbTrophee - 1) {
                sb.append(";");
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}