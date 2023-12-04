package controller;

import java.util.Set;

public class ControlleurObserver {
	private ControleurVue vue;
	public void setVue(ControleurVue vue){
		this.vue = vue;
	}
	public void notifyVue(String page) {
		vue.update(page);
	}
}
