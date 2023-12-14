package vue.admin.equipes;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class VueAdminEquipes extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout cl;

	/**
	 * Create the panel.
	 */
	public VueAdminEquipes() {
		cl = new CardLayout();
		setLayout(cl);
		setOpaque(false);
	}

	public void addPage(JComponent c, String id) {
		this.add(c, id);
	}

	public void setPage(String id) {
		cl.show(this, id);
	}


}
