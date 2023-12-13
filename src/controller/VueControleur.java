package controller;

import vue.Page;
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
		vueLogin.attachObserver(this.observer);
		vueAdmin.attachObserver(this.observer);
		this.vue.addPage(vueAdmin, Page.ACCUEIL_ADMIN.getNom());
		this.vue.addPage(vueLogin, Page.LOGIN.getNom());
		this.vue.setPage(Page.ACCUEIL_ADMIN.getNom());
	}

	public void update(String page) {
		this.vue.setPage(page);
	}
}
