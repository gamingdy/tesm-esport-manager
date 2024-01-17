package controlleur.arbitre;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import vue.arbitre.CasePartie;

import java.awt.event.*;

public class PartieControlleur implements MouseListener,ActionListener {
	private CasePartie caseP;
	public PartieControlleur(CasePartie casePartie){
		this.caseP=casePartie;
		System.out.println("CASE AJOUTe controleur " + caseP);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("giga puuuuuuuuuuuuuute");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("puuuuuuuuuuuuuute");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("JAI CLIQUE");
	}
}
