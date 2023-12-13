package controlleur;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.common.TitleBar;
import vue.login.VueLogin;

public class VueControlleur {
	private Vue vue;
	private VueObserver observer;

	public VueControlleur(Vue vue) throws Exception {
		this.vue = vue;
		this.observer = VueObserver.getInstance();
		observer.setVue(this);
		VueLogin vueLogin = new VueLogin();
		VueAdmin vueAdmin = new VueAdmin();
		vueLogin.attachObserver(this.observer);
		vueAdmin.attachObserver(this.observer);
		this.vue.addPage(vueAdmin, "Admin");
		this.vue.addPage(vueLogin, "Login");
		this.vue.setPage("Login");
		TitleBar.getInstance().setTitle("Login");
	}

	public void update(String page) {
		this.vue.setPage(page);
		TitleBar.getInstance().setTitle(page);
	}
}
