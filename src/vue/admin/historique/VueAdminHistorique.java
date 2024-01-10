package vue.admin.historique;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;


import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.MaFont;

public class VueAdminHistorique extends JPanel {

	private CustomComboBox<Integer> comboBoxSaison;
	/**
	 * Integer
	 */
	private DefaultComboBoxModel<Integer> modelSaisons;
	private JTable tableEquipes;
	/**
	 * World rank | Equipe (CaseEquipe) | Points
	 */
	private DefaultTableModel modelEquipes;
	private JTable tableTournois;
	private DefaultTableModel modelTournois;
	private JTable tableMatch;
	/**
	 * CaseEquipe1 | Points | CaseEquipe2 | Points
	 */
	private DefaultTableModel modelMatch;
	
	public VueAdminHistorique() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWeights = new double[] {0.5, 0.5};
		layout.rowWeights = new double[] {0.1, 0.45, 0.45};
		setLayout(layout);
		
		JPanel panelSaisons = new JPanel();
		panelSaisons.setOpaque(false);
		panelSaisons.setLayout(new GridLayout(2,1,15,0));
		GridBagConstraints gbcSaisons = new GridBagConstraints();
		gbcSaisons.insets = new Insets(0,0,50,0);
		gbcSaisons.gridx = 0;
		gbcSaisons.gridy = 0;
		gbcSaisons.fill = GridBagConstraints.HORIZONTAL;
		add(panelSaisons,gbcSaisons);
		
		JLabel labelSaisons = new JLabel("Saison: ");
		labelSaisons.setFont(MaFont.getFontTitre1());
		labelSaisons.setForeground(CustomColor.BLANC);
		panelSaisons.add(labelSaisons);
		
		modelSaisons = new DefaultComboBoxModel<>();
		comboBoxSaison = new CustomComboBox<>(modelSaisons);
		comboBoxSaison.setForeground(CustomColor.BLANC);
		comboBoxSaison.setFont(MaFont.getFontTitre1());
		panelSaisons.add(comboBoxSaison);
		
		modelEquipes = new DefaultTableModel(null, new String[] {"World Rank", "Équipe", "Points de la saison"});
		tableEquipes = new JTable(modelEquipes);
		tableEquipes.setBackground(CustomColor.BACKGROUND_MAIN);
		tableEquipes.setForeground(CustomColor.ROSE_CONTOURS);
		tableEquipes.setFont(MaFont.getFontTitre3());
		tableEquipes.setRowHeight(70);
		tableEquipes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTableHeader headerEquipes = new JTableHeader(tableEquipes.getColumnModel());
		headerEquipes.setBackground(CustomColor.BACKGROUND_MAIN);
		headerEquipes.setForeground(CustomColor.BLANC);
		headerEquipes.setFont(MaFont.getFontTitre2());
		tableEquipes.setTableHeader(headerEquipes);
		tableEquipes.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableEquipes.getColumnModel().getColumn(2).setMinWidth(140);
		tableEquipes.getColumnModel().getColumn(0).setCellRenderer(new SimpleRenderer());
		tableEquipes.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				CaseEquipe equipe = (CaseEquipe) value;
				JLabel label = new JLabel(equipe.getNom());
				label.setIcon(equipe.getLogo());
				label.setFont(MaFont.getFontTitre3());
				label.setForeground(CustomColor.BLANC);
				if (isSelected || hasFocus) {
					label.setOpaque(true);
					label.setBackground(CustomColor.BACKGROUND_MENU.brighter());
				}
				return label;
			}
		});
		tableEquipes.getColumnModel().getColumn(2).setCellRenderer(new SimpleRenderer());
		GridBagConstraints gbcEquipes = new GridBagConstraints();
		gbcEquipes.gridx = 0;
		gbcEquipes.gridy = 1;
		gbcEquipes.gridheight = 2;
		gbcEquipes.fill = GridBagConstraints.BOTH;

		JScrollPane spEquipes = new JScrollPane(tableEquipes);
		spEquipes.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		spEquipes.setBackground(CustomColor.BACKGROUND_MAIN);
		add(spEquipes,gbcEquipes);
		
		modelTournois = new DefaultTableModel(null,new String[] {"Tournoi","Date","Points"});
		tableTournois = new JTable(modelTournois);
		tableTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		tableTournois.setForeground(CustomColor.ROSE_CONTOURS);
		tableTournois.setFont(MaFont.getFontTitre3());
		tableTournois.setRowHeight(70);
		tableTournois.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTableHeader headerTournois = new JTableHeader(tableTournois.getColumnModel());
		headerTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		headerTournois.setForeground(CustomColor.BLANC);
		headerTournois.setFont(MaFont.getFontTitre2());
		tableTournois.setTableHeader(headerTournois);
		tableTournois.getColumnModel().getColumn(0).setCellRenderer(new SimpleRenderer());
		tableTournois.getColumnModel().getColumn(1).setCellRenderer(new SimpleRenderer());
		tableTournois.getColumnModel().getColumn(2).setCellRenderer(new SimpleRenderer());
		GridBagConstraints gbcTournois = new GridBagConstraints();
		gbcTournois.insets = new Insets(0,50,50,0);
		gbcTournois.gridx = 1;
		gbcTournois.gridy = 0;
		gbcTournois.gridheight = 2;
		gbcTournois.fill = GridBagConstraints.BOTH;
		
		JScrollPane spTournois = new JScrollPane(tableTournois);
		spTournois.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		spTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		add(spTournois,gbcTournois);
		
		modelMatch = new DefaultTableModel(null,new String[] {"Date","Equipe 1","Score","Equipe 2"});
		tableMatch = new JTable(modelMatch);
		tableMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		tableMatch.setForeground(CustomColor.ROSE_CONTOURS);
		tableMatch.setFont(MaFont.getFontTitre3());
		tableMatch.setRowHeight(70);
		tableMatch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTableHeader headerMatch = new JTableHeader(tableMatch.getColumnModel());
		headerMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		headerMatch.setForeground(CustomColor.BLANC);
		headerMatch.setFont(MaFont.getFontTitre2());
		tableMatch.setTableHeader(headerMatch);
		tableMatch.getColumnModel().getColumn(0).setCellRenderer(new SimpleRenderer());
		tableMatch.getColumnModel().getColumn(1).setCellRenderer(new SimpleRenderer());
		tableMatch.getColumnModel().getColumn(2).setCellRenderer(new SimpleRenderer());
		tableMatch.getColumnModel().getColumn(3).setCellRenderer(new EquipeRenderer());
		GridBagConstraints gbcMatch = new GridBagConstraints();
		gbcMatch.insets = new Insets(0,50,0,0);
		gbcMatch.gridx = 1;
		gbcMatch.gridy = 2;
		gbcMatch.fill = GridBagConstraints.BOTH;
		JScrollPane spMatch = new JScrollPane(tableMatch);
		spMatch.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		spMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		add(spMatch,gbcMatch);
		
		
		}
	
	/**
	 * @return the modelSaisons
	 */
	public DefaultComboBoxModel<Integer> getModelSaisons() {
		return modelSaisons;
	}
	/**
	 * @return the modelEquipes
	 */
	public DefaultTableModel getModelEquipes() {
		return modelEquipes;
	}
	/**
	 * @return the modelTournois
	 */
	public DefaultTableModel getModelTournois() {
		return modelTournois;
	}
	/**
	 * @return the modelMatch
	 */
	public DefaultTableModel getModelMatch() {
		return modelMatch;
	}

	public class CaseEquipe {
		private Icon logo;
		private String nom;
		private boolean aDroite;
		/**
		 * @param logo
		 * @param nom
		 * @param aDroite
		 */
		public CaseEquipe(Icon logo, String nom, boolean aDroite) {
			this.logo = logo;
			this.nom = nom;
			this.aDroite = aDroite;
		}
		/**
		 * @return the logo
		 */
		public Icon getLogo() {
			return logo;
		}
		/**
		 * @param logo the logo to set
		 */
		public void setLogo(Icon logo) {
			this.logo = logo;
		}
		/**
		 * @return the nom
		 */
		public String getNom() {
			return nom;
		}
		/**
		 * @param nom the nom to set
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}
		/**
		 * @return the aDroite
		 */
		public boolean isaDroite() {
			return aDroite;
		}
		/**
		 * @param aDroite the aDroite to set
		 */
		public void setaDroite(boolean aDroite) {
			this.aDroite = aDroite;
		
	}
	}
	private class SimpleRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
			JLabel label = new JLabel("" + value);
			label.setFont(MaFont.getFontTitre3());
			label.setForeground(CustomColor.BLANC);
			label.setHorizontalTextPosition(JLabel.CENTER);
			if (isSelected || hasFocus) {
				label.setOpaque(true);
				label.setBackground(CustomColor.BACKGROUND_MENU.brighter());
			}
			return label;
		}
	}
	private class EquipeRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			CaseEquipe equipe = (CaseEquipe) value;
			JLabel label = new JLabel(equipe.getNom());
			label.setIcon(equipe.getLogo());
			label.setFont(MaFont.getFontTitre3());
			label.setForeground(CustomColor.BLANC);
			label.setHorizontalTextPosition(equipe.aDroite ? JLabel.LEFT : JLabel.RIGHT);
			if (isSelected || hasFocus) {
				label.setOpaque(true);
				label.setBackground(CustomColor.BACKGROUND_MENU.brighter());
			}
			return label;
		}
	}
}
