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
		assert isInvariantVerified() : "Invariant : force >= 0";
	}

	public String getNom() {
		return nom;
	}

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

	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		forceCoup = calculerResistanceEquipement(forceCoup);
		force -= forceCoup;

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

	private int calculerResistanceEquipement(int forceCoup) {
		String texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipement != 0) {
			texte += "\nMais heureusement, grâce à mon équipement sa force a été ";
			for (int i = 0; i < nbEquipement; i++) {

				if (equipements[i] != null && equipements[i] == Equipement.BOUCLIER) {

					resistanceEquipement += 6;
				} else {
					resistanceEquipement += 3;
				}
			}

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
			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		nbEquipement = 0;
		return equipementEjecte;
	}

	public void sEquiper(Equipement equipement) {
		String chaine = "Le soldat " + nom;
		switch (nbEquipement) {
		case 2:
			chaine += " est déjà bien protégé !";
			break;
		case 1:
			if (equipements[0] != null && equipements[0].equals(equipement)) {
				chaine += " possède déjà un " + equipement + " !";
			} else {
				chaine += ajouterEquipement(equipement);
			}
			break;
		default:
			chaine += ajouterEquipement(equipement);
			break;
		}
		System.out.println(chaine);
	}

	private String ajouterEquipement(Equipement equipement) {
		equipements[nbEquipement] = equipement;
		nbEquipement++;
		return " s'équipe avec " + equipement + ".";
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