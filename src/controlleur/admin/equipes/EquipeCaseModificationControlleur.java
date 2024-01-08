package controlleur.admin.equipes;

import vue.admin.equipes.liste.CaseEquipe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EquipeCaseModificationControlleur extends MouseAdapter {
	private CaseEquipe caseEquipe;
	private boolean editing;

	public EquipeCaseModificationControlleur(CaseEquipe caseEquipe, boolean editing) {
		this.caseEquipe = caseEquipe;
		this.editing = editing;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (editing) {
			System.out.println("Clique sur le bouton modifié de la case " + this.caseEquipe.getNom());
		} else {
			System.out.println("Clique sur la case " + this.caseEquipe.getNom());
		}
	}
}
