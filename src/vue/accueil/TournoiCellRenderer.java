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
	
	private int nbTournois = 0;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object[]> list, Object[] value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setLayout(new BorderLayout());
		
		JLabel legende = null;
		if ((Boolean) value[1]) {
			legende = new JLabel("En cours");
			legende.setFont(MaFont.getFontTitre4());
			panelTournoi.add(legende,BorderLayout.NORTH);
			legende.setOpaque(false);
			legende.setForeground(Vue.BLANC);
		}
		else if (this.nbTournois == 0) {
			legende = new JLabel("Précédents");
			legende.setFont(MaFont.getFontTitre4());
			this.nbTournois += 1;
			panelTournoi.add(legende,BorderLayout.NORTH);
			legende.setOpaque(false);
			legende.setForeground(Vue.BLANC);
		}
		
		PanelNomTournoi panelNomTournoi = new PanelNomTournoi((String) value[0]);
		panelTournoi.add(panelNomTournoi, BorderLayout.CENTER);
		panelNomTournoi.setOpaque(false);
		panelNomTournoi.setForeground(Vue.BLANC);
		panelTournoi.setOpaque(false);
		if (legende != null)  {
			legende.setBorder(BorderFactory.createLineBorder(Color.blue,2));
		}
		return panelTournoi;
	}

}
