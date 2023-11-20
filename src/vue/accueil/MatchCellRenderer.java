package vue.accueil;

import javax.swing.JPanel;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MatchCellRenderer implements ListCellRenderer<Object[]> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object[]> list, Object[] value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel match = new PanelMatch((String) value[0], new ImageIcon((String) value[1]), (String) value[2], new ImageIcon((String) value[3]), new ImageIcon((String) value[4]), (String) value[5], new ImageIcon((String) value[6]));
		match.setOpaque(false);
		return match;
	}

}
