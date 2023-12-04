package controller;

public class VueObserver {
	private VueControleur vueControleur;

	public void setVue(VueControleur vue) {
		this.vueControleur = vue;
	}

	public void notifyVue(String page) {
		vueControleur.update(page);
	}
}
