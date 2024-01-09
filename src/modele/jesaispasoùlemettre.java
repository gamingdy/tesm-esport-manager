package modele;

import java.util.Set;

import dao.DaoAppartenance;
import dao.DaoPoule;

public class jesaispasoùlemettre {

    private Niveau niveauTournoi;
    private float coef;

    public jesaispasoùlemettre(Tournoi tournoi) {
        niveauTournoi = tournoi.getNiveau();
        coef = niveauTournoi.getCoefficient();
    }

    public Set<Equipe> ClassementEquipePoule(Tournoi tournoi){
        for (Poule poule : getPouleByTournoi(tournoi)) {
            for (Object... value : DaoAppartenance.getEquipeByPoule(poule)) {
                MapEquipePoule.add(<Equipe><point>);
            }
        }

        for (Poule poule : DaoTournoi.getMatchesByPoule(poule)) {
            MapEquipePoule.get(getWinner(matche)) += 3;
            getWinner(matche).setPoint(getWinner(matche).getPoint() += 25 * 10 * coef);
            MapEquipePoule.get(getLooser(matche)) += 1;
            getLooser(matche).setPoint(getLooser(matche).getPoint() += 15 * coef);
        }

        // Faire une liste triée des équipes en fonction de leur point (si égalité prendre wr, sinon tirage au sort)
    }

    // Prendre les 2 meilleures équipes :
    // create matche (categorie = FINALE, equipe1, equipe2)

    public void updatePoint(){
        // réorganiser la liste si besoin en fonction du résultat de la finale
        // liste[0].setPoint(liste[0].getPoint() += 200)
        // liste[1].setPoint(liste[1].getPoint() += 100)
        // liste[2].setPoint(liste[2].getPoint() += 40)
        // liste[3].setPoint(liste[3].getPoint() += 20)
    }
}

/*niveauTournoi = tournoi.getNiveau()
coef = niveauTournoi.getCoefficient()
Pour chaque :
	DaoPoule.getPouleByTournoi(Tournoi tournoi)
Faire :
	Pour :
		DaoAppartenance.getEquipeByPoule(Object... value)
	Faire :
		MapEquipePoule.add(<Equipe><point>)

	Pour chaque :
		DaoTournoi.getMatchesByPoule(Poule poule)
	Faire :
		MapEquipePoule.get(getWinner(matche)) += 3
		getWinner(matche).setPoint(getWinner(matche).getPoint() += 25 * 10 * coef)
		MapEquipePoule.get(getLooser(matche)) += 1
		getLooser(matche).setPoint(getLooser(matche).getPoint() += 15 * coef)

Faire une liste triée des équipes en fonction de leur point(si égalité prendre wr, sinon tirage au sort)
Prendre les 2 meilleures équipes :
	create matche (categorie = FINALE, equipe1, equipe2)
	réorganiser la liste si besoin en fonction du résultat de la finale
	
liste[0].setPoint(liste[0].getPoint() += 200)
liste[1].setPoint(liste[1].getPoint() += 100)
liste[2].setPoint(liste[2].getPoint() += 40)
liste[3].setPoint(liste[3].getPoint() += 20)
*/



