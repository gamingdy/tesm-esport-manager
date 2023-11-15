package vue;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class PageAccueil extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PageAccueil() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelClassement = new JPanel();
		GridBagConstraints gbc_panelClassement = new GridBagConstraints();
		gbc_panelClassement.weighty = 0.3;
		gbc_panelClassement.insets = new Insets(0, 0, 5, 0);
		gbc_panelClassement.fill = GridBagConstraints.BOTH;
		gbc_panelClassement.gridx = 0;
		gbc_panelClassement.gridy = 0;
		add(panelClassement, gbc_panelClassement);
		GridBagLayout gbl_panelClassement = new GridBagLayout();
		gbl_panelClassement.columnWidths = new int[]{0, 0};
		gbl_panelClassement.rowHeights = new int[]{0, 0, 0};
		gbl_panelClassement.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelClassement.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelClassement.setLayout(gbl_panelClassement);
		
		JLabel lblNewLabel = new JLabel("Classement des équipes année précédente");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.weighty = 0.2;
		gbc_lblNewLabel.weightx = 1.0;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelClassement.add(lblNewLabel, gbc_lblNewLabel);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.weighty = 0.8;
		gbc_list.weightx = 1.0;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panelClassement.add(list, gbc_list);
		
		JPanel panelBas = new JPanel();
		GridBagConstraints gbc_panelBas = new GridBagConstraints();
		gbc_panelBas.weighty = 0.7;
		gbc_panelBas.fill = GridBagConstraints.BOTH;
		gbc_panelBas.gridx = 0;
		gbc_panelBas.gridy = 1;
		add(panelBas, gbc_panelBas);
		GridBagLayout gbl_panelBas = new GridBagLayout();
		gbl_panelBas.columnWidths = new int[]{0, 0, 0};
		gbl_panelBas.rowHeights = new int[] {0};
		gbl_panelBas.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBas.rowWeights = new double[]{1.0};
		panelBas.setLayout(gbl_panelBas);
		
		JPanel panelTournois = new JPanel();
		GridBagConstraints gbc_panelTournois = new GridBagConstraints();
		gbc_panelTournois.weighty = 1.0;
		gbc_panelTournois.weightx = 0.5;
		gbc_panelTournois.fill = GridBagConstraints.BOTH;
		gbc_panelTournois.gridx = 0;
		gbc_panelTournois.gridy = 0;
		panelBas.add(panelTournois, gbc_panelTournois);
		panelTournois.setLayout(new BorderLayout(0, 0));
		
		JList list_2 = new JList();
		panelTournois.add(list_2);
		
		JLabel lblNewLabel_2 = new JLabel("Derniers tournois");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelTournois.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panelMatchs = new JPanel();
		GridBagConstraints gbc_panelMatchs = new GridBagConstraints();
		gbc_panelMatchs.weighty = 1.0;
		gbc_panelMatchs.weightx = 0.5;
		gbc_panelMatchs.fill = GridBagConstraints.BOTH;
		gbc_panelMatchs.gridx = 1;
		gbc_panelMatchs.gridy = 0;
		panelBas.add(panelMatchs, gbc_panelMatchs);
		panelMatchs.setLayout(new BorderLayout(0, 0));
		
		JList list_1 = new JList();
		panelMatchs.add(list_1);
		
		JLabel lblNewLabel_1 = new JLabel("Derniers Matchs");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelMatchs.add(lblNewLabel_1, BorderLayout.NORTH);

	}

}
