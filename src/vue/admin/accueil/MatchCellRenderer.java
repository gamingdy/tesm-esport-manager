package vue.admin.accueil;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

public class MatchCellRenderer implements ListCellRenderer<LigneMatche> {

	@Override
	public Component getListCellRendererComponent(JList<? extends LigneMatche> list, LigneMatche value, int index,
												  boolean isSelected, boolean cellHasFocus) {
		return new PanelMatch(value.getDateHeure(), value.getImageEquipe1(), value.getNomEquipe1(), value.getTropheeEquipe1(), value.getImageEquipe2(), value.getNomEquipe2(), value.getTropheeEquipe2());
	}

}
