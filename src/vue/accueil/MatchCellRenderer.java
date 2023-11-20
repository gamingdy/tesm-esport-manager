package vue.accueil;

import javax.swing.JPanel;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MatchCellRenderer implements ListCellRenderer<LigneMatche> {

	@Override
	public Component getListCellRendererComponent(JList<? extends LigneMatche> list, LigneMatche value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel match = new PanelMatch(value.getDateHeure(), value.getImageEquipe1(), value.getNomEquipe1(), value.getTropheeEquipe1(),value.getImageEquipe2(), value.getNomEquipe2(), value.getTropheeEquipe2());
		match.setOpaque(false);
		return match;
	}

}
