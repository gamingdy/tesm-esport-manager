package vue.admin.historique;

import javax.swing.ComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.*;
import javax.swing.*;

import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.MaFont;

public class VueAdminHistorique extends JPanel {

	public CustomComboBox<Integer> comboBoxSaison;
	public ComboBoxModel<Integer> modelSaisons;
	public JTable equipes;
	public JTable tournois;
	public TableModel modelEquipes;
	public TableModel modelTournois;
	
	public VueAdminHistorique() {
		setOpaque(false);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWeights = new double[] {0.6, 0.4};
		layout.rowWeights = new double[] {0.2, 0.8};
		setLayout(layout);
		
		JPanel panelSaisons = new JPanel();
		panelSaisons.setBackground(CustomColor.BACKGROUND_MAIN);
		panelSaisons.setLayout(new GridLayout(2,1,15,0));
		GridBagConstraints gbcSaisons = new GridBagConstraints();
		gbcSaisons.gridx = 0;
		gbcSaisons.gridy = 0;
		gbcSaisons.fill = GridBagConstraints.BOTH;
		add(panelSaisons);
		
		JLabel labelSaisons = new JLabel("Saison: ");
		labelSaisons.setFont(MaFont.getFontTitre2());
		labelSaisons.setForeground(CustomColor.BLANC);
		panelSaisons.add(labelSaisons);
		
		modelSaisons = new DefaultComboBoxModel<>();
		comboBoxSaison = new CustomComboBox<>(modelSaisons);
		comboBoxSaison.setForeground(CustomColor.BLANC);
		comboBoxSaison.setFont(MaFont.getFontTitre3());
		
		panelSaisons.add(comboBoxSaison);
		}
}
