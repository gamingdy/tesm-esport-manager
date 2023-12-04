package controller;
import vue.Vue;
import vue.admin.VueAdmin;
import vue.login.VueLogin;

public class ControleurVue {
	private Vue vue;
	private Vue observer;
	public ControleurVue(Vue vue){
		this.vue = vue;
		this.vue.addPage(new VueAdmin(), "Admin");
		this.vue.addPage(new VueLogin(),"Login");
		this.vue.setPage("Login");
	}
}
