package vue.admin.equipes.liste;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import vue.common.CustomColor;
import vue.common.CustomScrollBarUI;
import vue.common.MaFont;

public class VueAdminEquipesListe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<CaseEquipe> list;

	public VueAdminEquipesListe() {

		setOpaque(false);
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		DefaultListModel<CaseEquipe> model = new DefaultListModel<CaseEquipe>();
		list = new JList<CaseEquipe>(model);
		list.setBackground(CustomColor.BACKGROUND_MAIN);
		list.setLayout(new GridLayout(0,3));
		list.setCellRenderer(new CaseEquipeCellRenderer());		
		list.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS,3));


		JScrollPane sp = new JScrollPane(list);		
		sp.setBorder(null);

		GridBagConstraints gbcSp = new GridBagConstraints();
		gbcSp.fill = GridBagConstraints.BOTH;
		gbcSp.gridx = 0;
		gbcSp.gridy = 1;
		gbcSp.weighty = 0.8F;
		gbcSp.gridwidth = 3;
		add(sp,gbcSp);

		sp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		
		JTextField recherche = new JTextField();
		recherche.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		recherche.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		recherche.setForeground(CustomColor.BLANC);
		recherche.setFont(MaFont.getFontTitre4());
		recherche.setCaretColor(CustomColor.BLANC);
		GridBagConstraints cbgRecherche = new GridBagConstraints();
		cbgRecherche.fill= GridBagConstraints.HORIZONTAL;
		cbgRecherche.gridx = 0;
		cbgRecherche.gridy = 0;
		cbgRecherche.weightx = 0.6F;
		cbgRecherche.weighty = 0.2F;
		add(recherche,cbgRecherche);
		
		JButton boutonAjouter = new JButton("Ajouter");
		ImageIcon ajt = new ImageIcon("assets/BoutonAjouter.png");
		ajt = new ImageIcon( ajt.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH));
		boutonAjouter.setIcon(ajt);
		boutonAjouter.setIconTextGap(10);
		boutonAjouter.setBackground(CustomColor.BACKGROUND_MENU.brighter());
		boutonAjouter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3), BorderFactory.createEmptyBorder(10, 40, 10, 40)));
		boutonAjouter.setForeground(CustomColor.BLANC);
		boutonAjouter.setFont(MaFont.getFontTitre4());
		GridBagConstraints gbcBtnAjt = new GridBagConstraints();
		gbcBtnAjt.fill= GridBagConstraints.NONE;
		gbcBtnAjt.gridx = 1;
		gbcBtnAjt.gridy = 0;
		gbcBtnAjt.weightx = 0.4F;
		gbcBtnAjt.weighty = 0.2F;
		add(boutonAjouter,gbcBtnAjt);

	}

}
