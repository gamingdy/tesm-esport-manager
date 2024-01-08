package controlleur.admin.equipes;

import vue.admin.equipes.liste.CaseEquipe;

import java.awt.event.MouseAdapter;

public class EquipeSuppresionControlleur extends MouseAdapter {
	private CaseEquipe caseEquipe;

	public EquipeSuppresionControlleur(CaseEquipe caseEquipe) {
		this.caseEquipe = caseEquipe;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("Clique sur le bouton supprimer de la case " + this.caseEquipe.getNom());
	}

}
