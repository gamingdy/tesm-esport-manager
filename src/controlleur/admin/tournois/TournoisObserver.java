package controlleur.admin.tournois;

import vue.Page;

public class TournoisObserver {
	private TournoisControlleur controlleur;
	private static TournoisObserver instance;

	private TournoisObserver() {

	}

	public static synchronized TournoisObserver getInstance() {
		if (instance == null) {
			instance = new TournoisObserver();
		}
		return instance;
	}

	public void setVue(TournoisControlleur vue) {
		this.controlleur = vue;
	}

	public void notifyVue(Page page) {
		controlleur.update(page);
	}

}
