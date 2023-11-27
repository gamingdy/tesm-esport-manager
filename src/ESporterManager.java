import javax.swing.JFrame;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.login.VueLogin;

public class ESporterManager {

	public static void main(String[] args) {
		Vue frame = new Vue();
		frame.addPage(new VueLogin(), "Login");
		frame.setPage("Login");
		frame.setVisible(true);
	}

}
