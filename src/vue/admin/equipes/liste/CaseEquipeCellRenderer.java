package vue.admin.equipes.liste;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import vue.common.CustomColor;

@SuppressWarnings("serial")
public class CaseEquipeCellRenderer extends JPanel implements ListCellRenderer<CaseEquipe> {
	private JPanel panelItem = new JPanel(new GridBagLayout());
	
	public CaseEquipeCellRenderer() {
		setBackground(CustomColor.BACKGROUND_MAIN);
		GridLayout layout = new GridLayout(0,3);
		layout.setHgap(15);
		layout.setVgap(15);
		setLayout(layout);
        
        panelItem.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,2));
        add(panelItem);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends CaseEquipe> list, CaseEquipe value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JLabel nom = new JLabel(value.getNom());
		nom.setIcon(value.getLogo());
		nom.setForeground(CustomColor.BLANC);
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weighty = 0.2F;
		gbcNom.gridwidth = 3;
		panelItem.add(nom,gbcNom);
		
		JPanel panelJoueurs = new JPanel(new GridLayout(0,1));
		((GridLayout) panelJoueurs.getLayout()).setVgap(3);
		for (String j : value.getJoueurs() ) {
			panelJoueurs.add(new JLabel(j));
		}
		GridBagConstraints gbcJoueurs = new GridBagConstraints();
		gbcJoueurs.gridx = 0;
		gbcJoueurs.gridy = 1;
		gbcJoueurs.weighty = 0.6F;
		gbcJoueurs.gridwidth = 3;
		panelJoueurs.setOpaque(false);
		panelItem.add(panelJoueurs,gbcJoueurs);
		
		JLabel labelDrapeau = new JLabel(value.getPays());
		GridBagConstraints gbcDrapeau = new GridBagConstraints();
		gbcDrapeau.gridx = 0;
		gbcDrapeau.gridy = 2;
		gbcDrapeau.weightx = 0.2F;
		gbcDrapeau.weighty = 0.2F;
		panelItem.add(labelDrapeau,gbcDrapeau);
		
		JLabel labelModif = new JLabel(value.getPays());
		labelModif.setHorizontalTextPosition(JLabel.TRAILING);
		GridBagConstraints gbcModif = new GridBagConstraints();
		gbcModif.gridx = 1;
		gbcModif.gridy = 2;
		gbcModif.weightx = 0.6F;
		gbcModif.weighty = 0.2F;
		panelItem.add(labelModif,gbcModif);
		
		JLabel labelDelete = new JLabel(value.getPays());
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDrapeau.gridx = 2;
		gbcDrapeau.gridy = 2;
		gbcDrapeau.weightx = 0.2F;
		gbcDrapeau.weighty = 0.2F;
		panelItem.add(labelDelete,gbcDelete);

		panelJoueurs.setOpaque(false);
		
		return this;
	}

}
