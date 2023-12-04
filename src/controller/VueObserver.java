package controller;

import vue.Vue;

public class VueObserver {
	private VueControleur vueControleur;
	private static VueObserver instance;

	private VueObserver() {

	}

	public static synchronized VueObserver getInstance() {
		if (instance == null) {
			instance = new VueObserver();
		}
		return instance;
	}

	public void setVue(VueControleur vue) {
		this.vueControleur = vue;
	}

	public void notifyVue(String page) {
		vueControleur.update(page);
	}
}
