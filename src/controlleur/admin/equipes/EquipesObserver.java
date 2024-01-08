package controlleur.admin.equipes;

import vue.Page;

public class EquipesObserver {
	private EquipesControlleur controlleur;
	private static EquipesObserver instance;

	private EquipesObserver() {

	}

	public static synchronized EquipesObserver getInstance() {
		if (instance == null) {
			instance = new EquipesObserver();
		}
		return instance;
	}

	public void setVue(EquipesControlleur vue) {
		this.controlleur = vue;
	}

	public void notifyVue(Page page) {
		controlleur.update(page);
	}

	public void notifyVue(Page page, String nomEquipe, boolean editing) {
		controlleur.update(page, nomEquipe, editing);
	}

}
