import javax.swing.JFrame;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.login.VueLogin;

public class DemoAdmin {

	public static void main(String[] args) {
		Vue frame = new Vue();
		frame.addPage(new VueAdmin(), "Admin");
		frame.setPage("Admin");
		frame.setVisible(true);
	}

}
