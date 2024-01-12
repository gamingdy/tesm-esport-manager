package vue.admin.historique;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;


import controlleur.admin.historique.HistoriqueControlleur;
import modele.Pays;
import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.CustomScrollBarUI;
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
	 * Date | Nom Équipe | Points ("points1 - points 2") | Nom Équipe
	 */
	private DefaultTableModel modelMatch;

	public VueAdminHistorique() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		GridBagLayout layout = new GridBagLayout();

		layout.columnWeights = new double[]{0.5, 0.5};
		layout.rowWeights = new double[]{0.1, 0.45, 0.45};

		setLayout(layout);

		JPanel panelSaisons = new JPanel();
		panelSaisons.setOpaque(false);
		panelSaisons.setLayout(new GridLayout(2, 1, 15, 0));
		GridBagConstraints gbcSaisons = new GridBagConstraints();
		gbcSaisons.insets = new Insets(0, 0, 50, 0);
		gbcSaisons.gridx = 0;
		gbcSaisons.gridy = 0;
		gbcSaisons.fill = GridBagConstraints.HORIZONTAL;
		add(panelSaisons, gbcSaisons);

		JLabel labelSaisons = new JLabel("Saison: ");
		labelSaisons.setFont(MaFont.getFontTitre1());
		labelSaisons.setForeground(CustomColor.BLANC);
		panelSaisons.add(labelSaisons);

		modelSaisons = new DefaultComboBoxModel<>();
		comboBoxSaison = new CustomComboBox<>(modelSaisons);
		comboBoxSaison.setForeground(CustomColor.BLANC);
		comboBoxSaison.setFont(MaFont.getFontTitre1());
		comboBoxSaison.setRenderer(new javax.swing.ListCellRenderer<Integer>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Integer> list, Integer value, int index,
														  boolean isSelected, boolean cellHasFocus) {
				JLabel panel = new JLabel();
				panel.setOpaque(true);
				panel.setText(""+value);
				panel.setBackground(CustomColor.BACKGROUND_MAIN);
				panel.setFocusable(false);
				panel.setFont(MaFont.getFontTitre4());
				panel.setBorder(BorderFactory.createEmptyBorder(3, 5, 0, 0));
				if (isSelected) {
					panel.setForeground(CustomColor.ROSE_CONTOURS.darker());
				}
				else {
					panel.setForeground(CustomColor.BLANC);
				}
				return panel;
			}
		});
		panelSaisons.add(comboBoxSaison);
		
		modelEquipes = new DefaultTableModel(null, new String[] {"World Rank", "Équipe", "Points"}) {
			@Override
			public boolean isCellEditable(int row, int col) { return false;}
		};
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
		tableEquipes.getColumnModel().getColumn(0).setMaxWidth(130);
		tableEquipes.getColumnModel().getColumn(0).setPreferredWidth(130);
		tableEquipes.getColumnModel().getColumn(2).setMaxWidth(75);
		tableEquipes.getColumnModel().getColumn(2).setPreferredWidth(75);
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
		spEquipes.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		spEquipes.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		add(spEquipes, gbcEquipes);

		modelTournois = new DefaultTableModel(null, new String[]{"Tournoi", "Date", "Points"}){
			@Override
			public boolean isCellEditable(int row, int col) { return false;}
		};

		tableTournois = new JTable(modelTournois);
		tableTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		tableTournois.setForeground(CustomColor.ROSE_CONTOURS);
		tableTournois.setFont(MaFont.getFontTitre3());
		tableTournois.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tableTournois.setRowHeight(30);
		JTableHeader headerTournois = new JTableHeader(tableTournois.getColumnModel());
		headerTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		headerTournois.setForeground(CustomColor.BLANC);
		headerTournois.setFont(MaFont.getFontTitre2());
		tableTournois.setTableHeader(headerTournois);
		tableTournois.getColumnModel().getColumn(0).setCellRenderer(new SimpleRenderer());
		tableTournois.getColumnModel().getColumn(1).setCellRenderer(new SimpleRenderer());
		tableTournois.getColumnModel().getColumn(2).setCellRenderer(new SimpleRenderer());
		GridBagConstraints gbcTournois = new GridBagConstraints();
		gbcTournois.insets = new Insets(0, 50, 50, 0);
		gbcTournois.gridx = 1;
		gbcTournois.gridy = 0;
		gbcTournois.gridheight = 2;
		gbcTournois.fill = GridBagConstraints.BOTH;

		JScrollPane spTournois = new JScrollPane(tableTournois);
		spTournois.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		spTournois.setBackground(CustomColor.BACKGROUND_MAIN);
		spTournois.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		spTournois.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		add(spTournois, gbcTournois);

		modelMatch = new DefaultTableModel(null, new String[]{"Date", "Equipe 1", "Score", "Equipe 2"}){
			@Override
			public boolean isCellEditable(int row, int col) { return false;}
		};
		tableMatch = new JTable(modelMatch);
		tableMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		tableMatch.setForeground(CustomColor.ROSE_CONTOURS);
		tableMatch.setFont(MaFont.getFontTitre3());
		tableMatch.setRowHeight(30);
		tableMatch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTableHeader headerMatch = new JTableHeader(tableMatch.getColumnModel());
		headerMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		headerMatch.setForeground(CustomColor.BLANC);
		headerMatch.setFont(MaFont.getFontTitre2());
		tableMatch.setTableHeader(headerMatch);
		tableMatch.getColumnModel().getColumn(0).setCellRenderer((new SimpleRenderer()).smaller());
		tableMatch.getColumnModel().getColumn(1).setCellRenderer((new SimpleRenderer()).smaller());
		tableMatch.getColumnModel().getColumn(2).setCellRenderer((new SimpleRenderer()).smaller());
		tableMatch.getColumnModel().getColumn(3).setCellRenderer((new SimpleRenderer()).smaller());
		GridBagConstraints gbcMatch = new GridBagConstraints();
		gbcMatch.insets = new Insets(0, 50, 0, 0);
		gbcMatch.gridx = 1;
		gbcMatch.gridy = 2;
		gbcMatch.fill = GridBagConstraints.BOTH;
		JScrollPane spMatch = new JScrollPane(tableMatch);
		spMatch.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));
		spMatch.setBackground(CustomColor.BACKGROUND_MAIN);
		spMatch.getViewport().setBackground(CustomColor.BACKGROUND_MAIN);
		spMatch.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		add(spMatch, gbcMatch);
		
		setControleur(new HistoriqueControlleur(this));
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
	public JTable getTableEquipes(){
		return tableEquipes;
	}
	public JTable getTableTournois(){
		return tableTournois;
	}
	public static class CaseEquipe {
		private Icon logo;
		private String nom;

		/**
		 * @param logo
		 * @param nom
		 * @param aDroite
		 */
		public CaseEquipe(Icon logo, String nom ) {
			this.logo = logo;
			this.nom = nom;
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
	}

	private class SimpleRenderer implements TableCellRenderer {
		private Font font;
		public SimpleRenderer() {
			font = MaFont.getFontTitre3();
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel label = new JLabel(""+value, SwingConstants.CENTER);
			label.setForeground(CustomColor.BLANC);
			label.setHorizontalTextPosition(JLabel.CENTER);	
			label.setFont(font);
			if (isSelected || hasFocus) {
				label.setOpaque(true);
				label.setBackground(CustomColor.BACKGROUND_MENU.brighter());
			}
			return label;
		}
		public SimpleRenderer smaller() {
			font = MaFont.getFontPetitCorps();
			return this;
		}
	}

	private class EquipeRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			CaseEquipe equipe = (CaseEquipe) value;
			JLabel label = new JLabel(equipe.getNom(),SwingConstants.CENTER);
			label.setIcon(equipe.getLogo());
			label.setFont(MaFont.getFontTitre3());
			label.setForeground(CustomColor.BLANC);
//			label.setHorizontalTextPosition(equipe.aDroite ? JLabel.LEFT : JLabel.RIGHT);
			if (isSelected || hasFocus) {
				label.setOpaque(true);
				label.setBackground(CustomColor.BACKGROUND_MENU.brighter());
			}
			return label;
		}
	}

	public void setControleur(HistoriqueControlleur controlleur) {

		this.comboBoxSaison.addItemListener(controlleur);
		this.tableEquipes.getSelectionModel().addListSelectionListener(controlleur);
		this.tableTournois.getSelectionModel().addListSelectionListener(controlleur);
	}
}
