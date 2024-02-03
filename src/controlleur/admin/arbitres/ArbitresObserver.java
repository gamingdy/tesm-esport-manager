package controlleur.admin.arbitres;

import vue.Page;

public class ArbitresObserver {
	private ArbitresControlleur controlleur;
	private static ArbitresObserver instance;

	private ArbitresObserver() {

	}

	public static synchronized ArbitresObserver getInstance() {
		if (instance == null) {
			instance = new ArbitresObserver();
		}
		return instance;
	}

	public void setVue(ArbitresControlleur vue) {
		this.controlleur = vue;
	}

	public void notifyVue(Page page) {
		controlleur.update(page);
	}

}
