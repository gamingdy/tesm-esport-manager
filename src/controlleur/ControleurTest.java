package controlleur;

import vue.Vue;
import vue.admin.main.BoutonMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleurTest implements ActionListener, MouseListener {
	private Vue vue;
	private ETAT etat;
	public ControleurTest(Vue newVue){
		this.vue=newVue;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			//enuNavBar.selectionner((BoutonMenu) e.getSource());}
			System.out.println("patate");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof BoutonMenu) {
			BoutonMenu boutonSelectionné=(BoutonMenu) e.getSource();
			System.out.println("ui");
			if(boutonSelectionné.getText()=="Arbitres"){
				etat=ETAT.ARBITRES;
				System.out.println("PAGE arbitre");
				vue.setPage("Arbitres");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).survoller();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).finSurvoller();
		}
	}


}
