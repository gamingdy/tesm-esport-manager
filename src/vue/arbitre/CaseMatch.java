package vue.arbitre;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vue.Vue;
import vue.common.CustomColor;
import vue.common.MaFont;

public class CaseMatch extends CasePartie{

	private String date;
	private int idMatche;
	private JPanel panel;
	
	/**
	 * @param date
	 * @param logoGauche
	 * @param nomGauche
	 * @param logoDroite
	 * @param nomDroite
	 */
	public CaseMatch(String date, ImageIcon logoGauche, String nomGauche, ImageIcon imageGauche, ImageIcon imageDroite, String nomDroite, ImageIcon logoDroite, int idMatche, ActionListener alGauche, ActionListener alDroite) {
		super(logoGauche,nomGauche,imageGauche,imageDroite,nomDroite,logoDroite, alGauche, alDroite);
		this.date = date;
		this.idMatche=idMatche;
	}
	public int getIdMatche(){
		return this.idMatche;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public JComponent getPanel() {
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(false);
		JLabel panelDate = new JLabel();
		panelDate.setFont(MaFont.getFontTitre3());
		panelDate.setForeground(CustomColor.BLANC.darker());
		GridBagConstraints gbcDate = new GridBagConstraints();
		gbcDate.gridwidth = 2;
		gbcDate.insets = new Insets(0,0,10,0);
		panel.add(panelDate,gbcDate);
		
		JPanel panelE = new JPanel(new GridBagLayout());
		panelE.setOpaque(false);
		GridBagConstraints gbcEquipe = new GridBagConstraints();
		gbcEquipe.fill = GridBagConstraints.BOTH;
		gbcEquipe.weightx = 1;
		gbcEquipe.weighty = 1;
		gbcEquipe.gridy = 1;
		panel.add(panelE,gbcEquipe);
		JPanel equipe1 = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
		equipe1.setOpaque(false);
		equipe1.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 1),
				BorderFactory.createEmptyBorder(10,30,10,15)));
		JLabel logoEquipe1 = new JLabel();
		equipe1.add(logoEquipe1);
		JLabel nomEquipe1 = new JLabel();
		nomEquipe1.setFont(MaFont.getFontTitre3());
		nomEquipe1.setForeground(CustomColor.BLANC);
		equipe1.add(nomEquipe1);
		JButton image1 = new JButton();
		image1.setContentAreaFilled(false);
		image1.setBorder(null);
		image1.setFocusPainted(false);
		equipe1.add(image1);
		equipe1.setPreferredSize(new Dimension(1,1));
		GridBagConstraints gbcEquipe1 = new GridBagConstraints();
		gbcEquipe1.fill = GridBagConstraints.BOTH;
		gbcEquipe1.weightx = 0.50;
		gbcEquipe1.weighty = 1;
		gbcEquipe1.weighty = 1;
		panelE.add(equipe1,gbcEquipe1);
		
		JPanel equipe2 = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
		equipe2.setOpaque(false);
		equipe2.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1,0,1,1,CustomColor.ROSE_CONTOURS),
				BorderFactory.createEmptyBorder(10,15,10,30)));		
		JLabel logoEquipe2 = new JLabel();
		equipe2.add(logoEquipe2);
		JLabel nomEquipe2 = new JLabel();
		nomEquipe2.setFont(MaFont.getFontTitre3());
		nomEquipe2.setForeground(CustomColor.BLANC);
		equipe2.add(nomEquipe2);
		JButton image2 = new JButton();
		image2.setContentAreaFilled(false);
		image2.setBorder(null);
		image2.setFocusPainted(false);
		equipe2.add(image2);
		equipe2.setPreferredSize(new Dimension(1,1));
		GridBagConstraints gbcEquipe2 = new GridBagConstraints();
		gbcEquipe2.fill = GridBagConstraints.BOTH;
		gbcEquipe2.gridx = 1;
		gbcEquipe2.weightx = 0.5;
		gbcEquipe2.weighty = 1;
		panelE.add(equipe2,gbcEquipe2);
		panelDate.setText(getDate());
		logoEquipe1.setIcon(Vue.resize(getLogoGauche(),50,50));
		nomEquipe1.setText(getNomGauche());
		image1.setIcon(Vue.resize(getImageBoutonGauche(),50,50));
		image1.addActionListener(getAlGauche());
		logoEquipe2.setIcon(Vue.resize(getLogoDroite(),50,50));
		nomEquipe2.setText(getNomDroite());
		image2.setIcon(Vue.resize(getImageBoutonDroite(),50,50));
		return panel;
	}
}
