package vue.admin.equipes.liste;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

@SuppressWarnings("serial")
public class CaseEquipeCellRenderer extends JPanel implements ListCellRenderer<CaseEquipe> {
	private JPanel panelItem = new JPanel(new GridBagLayout());
	
	public CaseEquipeCellRenderer() {
		setBackground(CustomColor.BACKGROUND_MAIN);
		GridLayout layout = new GridLayout(0,3,15,15);
		setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
		
        add(panelItem);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends CaseEquipe> list, CaseEquipe value, int index,
			boolean isSelected, boolean cellHasFocus) {

		panelItem.setBackground(getBackground());
        panelItem.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,2),
        		BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		
		JLabel nom = new JLabel(value.getNom());
		nom.setIconTextGap(15);
		nom.setIcon(Vue.resize(value.getLogo(),45,45));
		nom.setForeground(CustomColor.BLANC);
		nom.setFont(MaFont.getFontTitre3());
		GridBagConstraints gbcNom = new GridBagConstraints();
		gbcNom.fill = GridBagConstraints.HORIZONTAL;
		gbcNom.gridx = 0;
		gbcNom.gridy = 0;
		gbcNom.weighty = 0.2F;
		gbcNom.gridwidth = GridBagConstraints.REMAINDER;
		panelItem.add(nom,gbcNom);
		
		JPanel panelJoueurs = new JPanel(new GridLayout(0,1));
		((GridLayout) panelJoueurs.getLayout()).setVgap(3);
		JLabel labelJ;
		for (String j : value.getJoueurs() ) {
			labelJ = new JLabel(j);
			labelJ.setForeground(CustomColor.BLANC);
			labelJ.setFont(MaFont.getFontTitre4());
			panelJoueurs.add(labelJ);
		}
		panelJoueurs.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));
		GridBagConstraints gbcJoueurs = new GridBagConstraints();
		gbcJoueurs.gridx = 0;
		gbcJoueurs.gridy = 1;
		gbcJoueurs.weighty = 0.6F;
		gbcJoueurs.gridwidth = GridBagConstraints.REMAINDER;
		panelJoueurs.setBackground(CustomColor.BACKGROUND_MAIN);
		panelItem.add(panelJoueurs,gbcJoueurs);
		
		JLabel labelDrapeau = new JLabel(Vue.resize(value.getPays(),45,30));
		labelDrapeau.setHorizontalAlignment(JLabel.LEFT);
		GridBagConstraints gbcDrapeau = new GridBagConstraints();
		gbcDrapeau.fill = GridBagConstraints.HORIZONTAL;
		gbcDrapeau.gridx = 0;
		gbcDrapeau.gridy = 2;
		gbcDrapeau.weightx = 0.2F;
		gbcDrapeau.weighty = 0.2F;
		panelItem.add(labelDrapeau,gbcDrapeau);
		
		JLabel labelModif = new JLabel(Vue.resize(new ImageIcon("assets/modif.png"),30,30));
		labelModif.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbcModif = new GridBagConstraints();
		gbcModif.fill = GridBagConstraints.HORIZONTAL;
		gbcModif.gridx = 1;
		gbcModif.gridy = 2;
		gbcModif.weightx = 0.6F;
		gbcModif.weighty = 0.2F;
		panelItem.add(labelModif,gbcModif);
		
		JLabel labelDelete = new JLabel(Vue.resize(new ImageIcon("assets/delete.png"),30,30));
		GridBagConstraints gbcDelete = new GridBagConstraints();
		gbcDelete.fill = GridBagConstraints.HORIZONTAL;
		gbcDelete.gridx = 2;
		gbcDelete.gridy = 2;
		gbcDelete.weightx = 0.2F;
		gbcDelete.weighty = 0.2F;
		panelItem.add(labelDelete,gbcDelete);
		
		return this;
	}

}
