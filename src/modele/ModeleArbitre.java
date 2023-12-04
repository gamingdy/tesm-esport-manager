package modele;

import java.util.List;
import java.util.Set;

public class ModeleArbitre {

    private static ModeleArbitre instance;
    private Tournoi tournoi;
    private CompteArbitre arbitre;
    private Set<Equipe> equipes;
    private Saison saison;

    private ModeleArbitre(CompteArbitre arbitre) {
        this.arbitre = arbitre;
        this.tournoi = arbitre.getTournoi();
        this.saison = tournoi.getSaison();
        this.equipes = saison.getEquipes();
        // TODO Faire l'appel de la base de données
    }

    public static ModeleArbitre getInstance(CompteArbitre arbitre) {
        if (instance == null) {
            synchronized (ModeleArbitre.class) {
                if (instance == null) {
                    instance = new ModeleArbitre(arbitre);
                }
            }
        }
        return instance;
    }


    
}
