package controlleur.arbitre;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import vue.arbitre.CasePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartieControlleur implements ActionListener {
	private CasePartie caseP;
	public PartieControlleur(CasePartie casePartie){
		this.caseP=casePartie;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("J'ai cliqué");
	}
}
