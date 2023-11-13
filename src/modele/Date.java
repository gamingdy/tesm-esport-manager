package modele;

public class Date {
    private int annee;
    private int mois;
    private int jour;
    private int heure;
    private int min;

    public Date(int annee, int mois, int jour) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }

    public Date(int annee, int mois, int jour, int heure, int min) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.heure = heure;
        this.min = min;
    }


}
