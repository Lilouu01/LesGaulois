package test_fonctionnel;

import personnages.Gaulois;

public class TestGaulois {
	public static void main(String[] args) {
		Gaulois asterix;
		asterix = new Gaulois("Astérix", 16);
		Gaulois obelix;
		obelix = new Gaulois("Obelix", 16);

		asterix.parler("Bonjour Obélix.");
		obelix.parler("Bonjour Astérix. Ca te dirais d'aller chasser des sangliers ?");

	}
}
