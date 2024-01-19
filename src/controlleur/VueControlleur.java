package controlleur;

import vue.Page;
import vue.Vue;
import vue.admin.VueAdmin;
import vue.arbitre.VueArbitrePoule;
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
		VueArbitrePoule vueArbitre = new VueArbitrePoule();
		vueLogin.attachObserver(this.observer);
		this.vue.addPage(vueAdmin, Page.ACCUEIL_ADMIN.getNom());
		this.vue.addPage(vueLogin, Page.LOGIN.getNom());
		this.vue.addPage(vueArbitre, Page.ARBITRE.getNom());
		this.vue.setPage(Page.LOGIN);
	}

	public void update(Page page) {
		this.vue.setPage(page);
		TitleBar.getInstance().setTitle(page.getNom());
	}
}
