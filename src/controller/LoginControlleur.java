package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;

import dao.DaoTournoi;
import modele.CompteAdmin;
import modele.CompteArbitre;
import modele.CompteUtilisateur;
import modele.Tournoi;
import vue.admin.main.BoutonMenu;
import vue.login.ChampConnexion;
import vue.login.VueLogin;

import javax.swing.*;

public class LoginControlleur implements ActionListener, MouseListener {
	private VueLogin vue;
	private CompteAdmin admin = CompteAdmin.compteAdmin;
	private CompteArbitre arbitre;
	private String champLogin;
	private String champMotDePasse;
	private DaoTournoi dao;
	private Tournoi tournoi;

	private ControlleurObserver obs;

	public LoginControlleur(VueLogin newVue){
		this.vue=newVue;
	}

	public void attach(ControlleurObserver obs){
		this.obs = obs;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton bouton=(JButton) e.getSource();
		champLogin = vue.getIdentifiant();
		champMotDePasse=vue.getMotDePasse();
		if (bouton.getText()=="Connexion" )  {
			if(!champMotDePasse.isEmpty() && !champLogin.isEmpty()) {
				CompteUtilisateur compteActuel=compteAdminOuUtilisateur(champLogin,champMotDePasse);
				if(compteActuel==null){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
					JOptionPane.showMessageDialog(vue,"Identifiant ou mot de passe incorrect");
				}
				else {
					if(compteActuel instanceof CompteArbitre) {
						JOptionPane.showMessageDialog(vue, "Bienvenue en tant qu'Arbitre");
					}
					if (compteActuel==this.admin){
						obs.notifyVue("Admin");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(vue,"Un des champs est vide");
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	public CompteUtilisateur compteAdminOuUtilisateur(String login,String mdp){
		if (login.equals(admin.getUsername())&&mdp.equals(admin.getMdp())){
			return this.admin;
		}
		/*
		if (login.equals(arbitre.getUsername())&&mdp.equals(arbitre.getMdp())){
			return this.arbitre;
		}*/
		return null;
	}
}
