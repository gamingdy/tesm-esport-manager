package vue.admin.accueil;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;


public class EquipeCellRenderer implements ListCellRenderer<LigneEquipe> {
	private Map<String, PanelEquipeClassement> cache = new HashMap<>();

	@Override
	public Component getListCellRendererComponent(JList<? extends LigneEquipe> list, LigneEquipe value, int index,
												  boolean isSelected, boolean cellHasFocus) {


		PanelEquipeClassement equipe = cache.get(value.getNom());
		if (equipe == null) {
			equipe = new PanelEquipeClassement(value.getPlace(), value.getLogo(), value.getNom(), value.getPoints());
			cache.put(value.getNom(), equipe);
		}

		return equipe;

	}

}
