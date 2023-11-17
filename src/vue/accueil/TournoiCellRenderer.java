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
import vue.common.MaFont;

public class TournoiCellRenderer implements ListCellRenderer<Object[]> {
	
	private boolean tournoisEnCours = false;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object[]> list, Object[] value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setLayout(new BorderLayout());
		
		JLabel legende = null;
		if ((Boolean) value[1]) {
			legende = new JLabel("En cours");
			legende.setFont(MaFont.getFontTitre2());
			panelTournoi.add(legende,BorderLayout.NORTH);
			panelTournoi.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
			legende.setOpaque(false);
			legende.setForeground(Vue.BLANC);
			tournoisEnCours=true;
		}
		else if (this.tournoisEnCours && index ==1 || !this.tournoisEnCours && index ==0) {
			legende = new JLabel("Précédents");
			legende.setFont(MaFont.getFontTitre2());
			panelTournoi.add(legende,BorderLayout.NORTH);
			panelTournoi.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
			legende.setOpaque(false);
			legende.setForeground(Vue.BLANC);
		}
		
		PanelNomTournoi panelNomTournoi = new PanelNomTournoi((String) value[0]);
		panelTournoi.add(panelNomTournoi, BorderLayout.CENTER);
		panelNomTournoi.setOpaque(false);
		panelNomTournoi.setForeground(Vue.BLANC);
		panelTournoi.setOpaque(false);
		panelNomTournoi.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,55,0,75),panelNomTournoi.getBorder()));
		return panelTournoi;
	}

}
