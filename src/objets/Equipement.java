package objets;

public enum Equipement {

    CASQUE("casque"),
    BOUCLIER("bouclier");

    private String nom;

    Equipement(String nom) {
        this.nom = nom;
    }

    @Override 
    public String toString() {
        return nom;
    } 
}