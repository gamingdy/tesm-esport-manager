package controlleur.admin.tournois;

import vue.admin.tournois.liste.VueAdminTournoisListe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TournoisListeControlleur implements ActionListener {
	private VueAdminTournoisListe vueAdminTournoisListe;

	public TournoisListeControlleur(VueAdminTournoisListe newVue) {
		this.vueAdminTournoisListe = newVue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
