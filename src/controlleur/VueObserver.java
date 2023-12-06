package controlleur;

import vue.Vue;

public class VueObserver {
	private VueControlleur vueControleur;
	private static VueObserver instance;

	private VueObserver() {

	}

	public static synchronized VueObserver getInstance() {
		if (instance == null) {
			instance = new VueObserver();
		}
		return instance;
	}

	public void setVue(VueControlleur vue) {
		this.vueControleur = vue;
	}

	public void notifyVue(String page) {
		vueControleur.update(page);
	}
}
