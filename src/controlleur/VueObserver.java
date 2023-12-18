package controlleur;

public class VueObserver {
	private VueControlleur vueControlleur;
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
		this.vueControlleur = vue;
	}

	public void notifyVue(String page) {
		vueControlleur.update(page);
	}
}
