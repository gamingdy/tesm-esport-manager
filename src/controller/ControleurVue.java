package controller;
import vue.Vue;
import vue.admin.VueAdmin;
import vue.login.VueLogin;

public class ControleurVue {
	private Vue vue;
	private ControlleurObserver observer;
	public ControleurVue(Vue vue){
		this.vue = vue;
		this.observer = new ControlleurObserver();
		observer.setVue(this);
		VueLogin vueLogin = new VueLogin();
		vueLogin.attachObserver(this.observer);
		this.vue.addPage(new VueAdmin(),"Admin");
		this.vue.addPage(vueLogin,"Login");
		this.vue.setPage("Login");
	}

	public void update(String page){
		this.vue.setPage(page);
	}
}
