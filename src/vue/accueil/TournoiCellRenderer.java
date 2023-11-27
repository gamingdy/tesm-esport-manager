package vue.accueil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

public class TournoiCellRenderer implements ListCellRenderer<LigneTournoi> {

	private boolean tournoisEnCours = false;

	@Override
	public Component getListCellRendererComponent(JList<? extends LigneTournoi> list, LigneTournoi value, int index,
												  boolean isSelected, boolean cellHasFocus) {

		JPanel panelTournoi = new JPanel();
		panelTournoi.setLayout(new BorderLayout());

		JLabel legende = null;
		if (value.isEnCours()) {
			legende = new JLabel("En cours");
			legende.setFont(MaFont.getFontTitre2());
			panelTournoi.add(legende, BorderLayout.NORTH);
			panelTournoi.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
			legende.setOpaque(false);
			legende.setForeground(CustomColor.BLANC);
			tournoisEnCours = true;
		} else if (this.tournoisEnCours && index == 1 || !this.tournoisEnCours && index == 0) {
			legende = new JLabel("Précédents");
			legende.setFont(MaFont.getFontTitre2());
			panelTournoi.add(legende, BorderLayout.NORTH);
			panelTournoi.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
			legende.setOpaque(false);
			legende.setForeground(CustomColor.BLANC);
		}

		PanelNomTournoi panelNomTournoi = new PanelNomTournoi(value.getNom());
		panelTournoi.add(panelNomTournoi, BorderLayout.CENTER);
		panelNomTournoi.setOpaque(false);
		panelNomTournoi.setForeground(CustomColor.BLANC);
		panelTournoi.setOpaque(false);
		panelNomTournoi.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 55, 0, 75), panelNomTournoi.getBorder()));
		return panelTournoi;
	}

}
