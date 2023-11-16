package vue.accueil;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import vue.common.MaFont;

public class TournoiCellRenderer implements ListCellRenderer<Object[]> {
	
	private int nbTournois = 0;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object[]> list, Object[] value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panelTournoi = new JPanel();
		
		if (this.nbTournois == 0) {
			JLabel panelTournoiFini = new JLabel("Précédents");
			panelTournoiFini.setFont(MaFont.getFontTitre5());
		}
		
		return panelTournoi;
	}

}
