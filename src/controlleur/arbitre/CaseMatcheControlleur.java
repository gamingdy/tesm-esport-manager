package controlleur.arbitre;

import vue.arbitre.CaseMatch;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CaseMatcheControlleur extends MouseAdapter {

	private CaseMatch caseMatch;
	private boolean is_left;

	public CaseMatcheControlleur(CaseMatch caseMatch, boolean is_left) {
		this.caseMatch = caseMatch;
		this.is_left = is_left;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("CaseMatcheControlleur");
		if (is_left) {
			System.out.println("is_left");
		} else {
			System.out.println("is_right");
		}
	}
}
