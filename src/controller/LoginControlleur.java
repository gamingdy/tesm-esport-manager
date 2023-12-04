package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;

import modele.CompteAdmin;
import modele.CompteArbitre;
import modele.CompteUtilisateur;
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
	private String login="sofiya";
	private String mdp="patata";

	public LoginControlleur(VueLogin newVue){
		this.vue=newVue;

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
						JOptionPane.showMessageDialog(vue,"Identifiant ou mot de passe incorrect");
				}
				else {
					if(compteActuel instanceof CompteArbitre) {
						JOptionPane.showMessageDialog(vue, "Bienvenue en tant qu'Arbitre");
					}
					if (compteActuel==this.admin){
						JOptionPane.showMessageDialog(vue, "Bienvenue en tant qu'Admin");
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	public CompteUtilisateur compteAdminOuUtilisateur(String login,String mdp){
		if (login.equals(admin.getUsername())&&mdp.equals(admin.getMdp())){
			return this.admin;
		}
		if (login.equals(arbitre.getUsername())&&mdp.equals(arbitre.getMdp())){
			return this.arbitre;
		}
		return null;
	}
}
