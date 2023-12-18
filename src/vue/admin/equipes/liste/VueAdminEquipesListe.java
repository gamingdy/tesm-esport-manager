package vue.admin.equipes.liste;


import controlleur.admin.equipes.EquipesListeControlleur;
import vue.Vue;
import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VueAdminEquipesListe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel list;
	private JButton boutonAjouter;

	public VueAdminEquipesListe() {

		setOpaque(false);
		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

		List<CaseEquipe> model = new LinkedList<CaseEquipe>();
		list = new JPanel(new GridLayout(0, 3, 15, 15));
		list.setBackground(CustomColor.BACKGROUND_MAIN);
		list.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		//setListEquipes(model);

		JScrollPane sp = new JScrollPane(list);
		sp.setBackground(CustomColor.BACKGROUND_MAIN);
		sp.getVerticalScrollBar().setUnitIncrement(15);
		sp.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));

		GridBagConstraints gbcSp = new GridBagConstraints();
		gbcSp.fill = GridBagConstraints.BOTH;
		gbcSp.gridx = 0;
		gbcSp.gridy = 1;
		gbcSp.weighty = 0.8F;
		gbcSp.gridwidth = 3;
		add(sp, gbcSp);

		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());

		JPanel panelRecherche = new JPanel();
		((FlowLayout) panelRecherche.getLayout()).setHgap(10);
		((FlowLayout) panelRecherche.getLayout()).setAlignment(FlowLayout.LEFT);
		panelRecherche.add(new JLabel(Vue.resize(new ImageIcon("assets/recherche.png"), 45, 45)));
		JTextField recherche = new JTextField();
		panelRecherche.setBackground(CustomColor.BACKGROUND_MAIN);
		panelRecherche.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		recherche.setColumns(25);
		recherche.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		recherche.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2), BorderFactory.createEmptyBorder(10, 5, 10, 0)));
		recherche.setForeground(CustomColor.BLANC);
		recherche.setFont(MaFont.getFontTitre4());
		recherche.setCaretColor(CustomColor.BLANC);
		GridBagConstraints gbcRecherche = new GridBagConstraints();
		gbcRecherche.fill = GridBagConstraints.HORIZONTAL;
		gbcRecherche.gridx = 0;
		gbcRecherche.gridy = 0;
		gbcRecherche.weightx = 0.6F;
		gbcRecherche.weighty = 0.2F;
		panelRecherche.add(recherche);

		add(panelRecherche, gbcRecherche);

		boutonAjouter = new JButton("Ajouter");

		ImageIcon ajt = new ImageIcon("assets/BoutonAjouter.png");
		ajt = new ImageIcon(ajt.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		boutonAjouter.setIcon(ajt);
		boutonAjouter.setIconTextGap(10);
		boutonAjouter.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonAjouter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAjouter.setForeground(CustomColor.BLANC);
		boutonAjouter.setFont(MaFont.getFontTitre4());
		GridBagConstraints gbcBtnAjt = new GridBagConstraints();
		gbcBtnAjt.fill = GridBagConstraints.NONE;
		gbcBtnAjt.gridx = 1;
		gbcBtnAjt.gridy = 0;
		gbcBtnAjt.weightx = 0.4F;
		gbcBtnAjt.weighty = 0.2F;
		add(boutonAjouter, gbcBtnAjt);
	}

	public void setListEquipes(List<CaseEquipe> l) {
		list.removeAll();
		for (CaseEquipe c : l) {
			list.add(c.getPanel());
		}
		JPanel j;
		for (int i = 4; l.size() < i; i--) {
			j = new JPanel();
			j.setOpaque(false);
			list.add(j);
		}
	}

	public JButton getBoutonAjouter() {
		return this.boutonAjouter;
	}

	public void setControleur(EquipesListeControlleur controleur) {
		this.boutonAjouter.addActionListener(controleur);

	}

}
