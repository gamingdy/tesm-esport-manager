package controller;

import vue.Vue;
import vue.admin.VueAdmin;
import vue.admin.arbitres.VueArbitres;
import vue.arbitres.PageTestArbitre;
import vue.login.VueLogin;

public class VueControleur {
	private Vue vue;
	private VueObserver observer;

	public VueControleur(Vue vue) throws Exception {
		this.vue = vue;
		this.observer = VueObserver.getInstance();
		observer.setVue(this);
		VueLogin vueLogin = new VueLogin();
		VueAdmin vueAdmin = new VueAdmin();
		VueArbitres vueArbitres = new VueArbitres();
		vueLogin.attachObserver(this.observer);
		vueAdmin.attachObserver(this.observer);
		this.vue.addPage(vueAdmin, "Admin");
		this.vue.addPage(vueLogin, "Login");
		this.vue.setPage("Login");
	}

	public void update(String page) {
		this.vue.setPage(page);
	}
}
