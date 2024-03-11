package vue.arbitre;

import vue.common.CustomColor;
import vue.common.MaFont;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

@SuppressWarnings("serial")
public abstract class VueArbitre extends JPanel {

	protected JLabel titre;
	protected JPanel main;
	protected JButton boutonAction;
	protected JButton boutonDeconnexion;

	protected VueArbitre() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowWeights = new double[]{0, 0.85, 0.15};
		gbl.columnWeights = new double[]{0.5, 0.5};
		setLayout(gbl);

		GridBagConstraints gbcTitre = new GridBagConstraints();
		gbcTitre.fill = GridBagConstraints.NONE;
		gbcTitre.gridwidth = 2;
		titre = new JLabel("Titre par défaut");
		titre.setFont(MaFont.getFontMenu());
		titre.setForeground(CustomColor.BLANC);
		add(titre, gbcTitre);


		GridBagConstraints gbcBoutonDeconnexion = new GridBagConstraints();
		gbcBoutonDeconnexion.gridy = 2;
		gbcBoutonDeconnexion.gridx = 0;
		gbcBoutonDeconnexion.fill = GridBagConstraints.NONE;
		boutonDeconnexion = new JButton("Déconnexion");
		boutonDeconnexion.setFont(MaFont.getFontLabelConnexion());
		boutonDeconnexion.setForeground(CustomColor.BLANC);
		boutonDeconnexion.setFocusPainted(false);
		boutonDeconnexion.setBackground(CustomColor.BACKGROUND_MENU);
		boutonDeconnexion.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		boutonDeconnexion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add(boutonDeconnexion, gbcBoutonDeconnexion);

		GridBagConstraints gbcBoutonAction = new GridBagConstraints();
		gbcBoutonAction.gridy = 2;
		gbcBoutonAction.gridx = 1;
		gbcBoutonAction.fill = GridBagConstraints.NONE;
		boutonAction = new JButton("Bouton par défaut");
		boutonAction.setFont(MaFont.getFontLabelConnexion());
		boutonAction.setForeground(CustomColor.BLANC);
		boutonAction.setFocusPainted(false);
		boutonAction.setBackground(CustomColor.BACKGROUND_MENU);
		boutonAction.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add(boutonAction, gbcBoutonAction);


		initMain();
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.insets = new Insets(0, 50, 0, 50);
		gbcMain.gridy = 1;
		gbcMain.gridwidth = 2;
		gbcMain.fill = GridBagConstraints.BOTH;
		add(main, gbcMain);
	}

	protected void initMain() {
		main = new JPanel();
		main.setBackground(CustomColor.BACKGROUND_MAIN);
		main.setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 3));

	}

	public void setTitre(String texte) {
		titre.setText(texte);
	}

	public void setTexteBouton(String texte) {
		boutonAction.setText(texte);
	}
}
