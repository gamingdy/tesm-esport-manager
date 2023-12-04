package controller;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.login.VueLogin;

public class VueControleur {
	private Vue vue;
	private VueObserver observer;

	public VueControleur(Vue vue) {
		this.vue = vue;
		this.observer = VueObserver.getInstance();
		observer.setVue(this);
		VueLogin vueLogin = new VueLogin();
		VueAdmin vueAdmin = new VueAdmin();
		vueLogin.attachObserver(this.observer);
		vueAdmin.attachObserver(this.observer);
		this.vue.addPage(vueAdmin, "Admin");
		this.vue.addPage(vueLogin, "Login");
		this.vue.setPage("Admin");
	}

	public void update(String page) {
		this.vue.setPage(page);
	}
}
