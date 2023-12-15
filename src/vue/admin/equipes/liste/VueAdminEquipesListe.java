package vue.admin.equipes.liste;

import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import vue.common.CustomColor;

public class VueAdminEquipesListe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<CaseEquipe> list;

	public VueAdminEquipesListe() {

		setLayout(new GridBagLayout());
		
		DefaultListModel<CaseEquipe> model = new DefaultListModel<CaseEquipe>();
		list = new JList<CaseEquipe>(model);
		list.setCellRenderer(new ListCellRenderer<CaseEquipe>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends CaseEquipe> list, CaseEquipe value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JPanel retour = new JPanel();
				JLabel logo = new JLabel(value.getLogo());
				retour.add(logo);
				JLabel nom = new JLabel(value.getNom());
				retour.add(nom);
				
				return retour;
			}

		});
		
		JScrollPane sp = new JScrollPane(list);
		GridBagConstraints gbcSp = new GridBagConstraints();
		gbcSp.gridx = 0;
		gbcSp.gridy = 0;
		
		add(sp,gbcSp);
		
		JButton boutonValider = new JButton("Ajouter");
		boutonValider.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonValider.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonValider.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcBtnAjt = new GridBagConstraints();
		gbcBtnAjt.fill= GridBagConstraints.NONE;
		gbcBtnAjt.gridx = 0;
		gbcBtnAjt.gridy = 1;
		gbcBtnAjt.weighty = 0.2;
		add(boutonValider);

	}

}
