package vue.accueil;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class EquipeCellRenderer implements ListCellRenderer<LigneEquipe> {


	@Override
	public Component getListCellRendererComponent(JList<? extends LigneEquipe> list, LigneEquipe value, int index,
			boolean isSelected, boolean cellHasFocus) {
	
		PanelEquipeClassement equipe = new PanelEquipeClassement(value.getPlace(),value.getLogo(),value.getNom(),value.getPoints());
		equipe.setOpaque(false);
		equipe.setBorder(BorderFactory.createCompoundBorder(equipe.getBorder(),
				BorderFactory.createEmptyBorder(0,0,15,0)));
        return equipe;
        
	}

}
