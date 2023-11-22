package vue.accueil;

import javax.swing.JPanel;

import vue.Vue;
import vue.common.MaFont;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PanelMatch extends JPanel {
	private JPanel panelEquipeDeux;
	private JPanel panelEquipeUn;
	private JPanel panelBot;

	public PanelMatch(String date, ImageIcon img1, String nomEquipe1, ImageIcon trophee1, ImageIcon img2, String nomEquipe2, ImageIcon trophee2) {
		
		JLabel labelDate = new JLabel(date);
		labelDate.setHorizontalAlignment(JLabel.CENTER);
		labelDate.setFont(MaFont.getFontTitre4());
		labelDate.setForeground(Vue.BLANC);
		setLayout(new BorderLayout(0, 0));
		add(labelDate, BorderLayout.NORTH);
		
		panelBot = new JPanel();
		add(panelBot, BorderLayout.CENTER);
		panelBot.setLayout(new GridLayout(1,0, 0, 0));
		panelBot.setBorder(new LineBorder(Vue.ROSE_CONTOURS));
		
		
		panelEquipeUn = new JPanel();
		panelBot.add(panelEquipeUn);
		GridBagLayout gblPanelEquipeUn = new GridBagLayout();
		gblPanelEquipeUn.columnWidths = new int[] {0};
		gblPanelEquipeUn.rowHeights = new int[] {0};
		gblPanelEquipeUn.columnWeights = new double[]{0.0, 0.0, 0.0};
		gblPanelEquipeUn.rowWeights = new double[]{0.0};
		panelEquipeUn.setLayout(gblPanelEquipeUn);
		
		JLabel labelIcone1 = new JLabel();
		labelIcone1.setIcon(new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		labelIcone1.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelIcone1 = new GridBagConstraints();
		gbcLabelIcone1.fill = GridBagConstraints.BOTH;
		gbcLabelIcone1.gridx = 0;
		gbcLabelIcone1.gridy = 0;
		gbcLabelIcone1.weightx = 1.5F/10F;
		panelEquipeUn.add(labelIcone1, gbcLabelIcone1);
		
		JLabel labelNom1 = new JLabel(nomEquipe1);
		labelNom1.setFont(MaFont.getFontTitre3());
		labelNom1.setForeground(Vue.BLANC);
		labelNom1.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelNom1 = new GridBagConstraints();
		gbcLabelNom1.fill = GridBagConstraints.BOTH;
		gbcLabelNom1.gridx = 1;
		gbcLabelNom1.gridy = 0;
		gbcLabelNom1.weightx = 7F/10F;
		panelEquipeUn.add(labelNom1, gbcLabelNom1);
		
		JLabel labelTrophee1 = new JLabel();
		labelTrophee1.setIcon(new ImageIcon(trophee1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		labelTrophee1.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelTrophee1 = new GridBagConstraints();
		gbcLabelTrophee1.fill = GridBagConstraints.BOTH;
		gbcLabelTrophee1.gridx = 2;
		gbcLabelTrophee1.gridy = 0;
		gbcLabelTrophee1.weightx = 1.5F/10F;
		panelEquipeUn.add(labelTrophee1, gbcLabelTrophee1);
		panelEquipeUn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Vue.ROSE_CONTOURS),BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		panelEquipeDeux = new JPanel();
		panelBot.add(panelEquipeDeux,0,1);
		GridBagLayout gblPanelEquipeDeux = new GridBagLayout();
		gblPanelEquipeDeux.columnWidths = new int[] {0};
		gblPanelEquipeDeux.rowHeights = new int[] {0};
		gblPanelEquipeDeux.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblPanelEquipeDeux.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelEquipeDeux.setLayout(gblPanelEquipeDeux);
		
		JLabel labelTrophee2 = new JLabel();
		labelTrophee2.setIcon(new ImageIcon(trophee2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		labelTrophee2.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelTrophee2 = new GridBagConstraints();
		gbcLabelTrophee2.fill = GridBagConstraints.BOTH;
		gbcLabelTrophee2.gridx = 0;
		gbcLabelTrophee2.gridy = 0;
		gbcLabelTrophee2.weightx = 1.5F/10F;
		panelEquipeDeux.add(labelTrophee2, gbcLabelTrophee2);
		
		JLabel labelNom2 = new JLabel(nomEquipe2);
		labelNom2.setFont(MaFont.getFontTitre3());
		labelNom2.setForeground(Vue.BLANC);
		labelNom2.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelNom2 = new GridBagConstraints();
		gbcLabelNom2.fill = GridBagConstraints.BOTH;
		gbcLabelNom2.gridx = 1;
		gbcLabelNom2.gridy = 0;
		gbcLabelNom2.weightx = 7F/10F;
		panelEquipeDeux.add(labelNom2, gbcLabelNom2);
		
		JLabel labelIcone2 = new JLabel();
		labelIcone2.setIcon(new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		labelIcone2.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints gbcLabelIcone2 = new GridBagConstraints();
		gbcLabelIcone2.fill = GridBagConstraints.BOTH;
		gbcLabelIcone2.gridx = 2;
		gbcLabelIcone2.gridy = 0;
		gbcLabelIcone2.weightx = 1.5F/10F;
		panelEquipeDeux.add(labelIcone2, gbcLabelIcone2);
		panelEquipeDeux.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));
		
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		panelEquipeUn.setOpaque(false);
		panelBot.setOpaque(false);
		panelEquipeDeux.setOpaque(false);
	}
	
	@Override
	public void setOpaque(boolean bool) {
		super.setOpaque(bool);
	}

}
