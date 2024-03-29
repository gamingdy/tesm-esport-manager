package dao;

abstract class SuperDao {

    private final Connexion connexion;
    private final Constants constants;

    public  SuperDao(Connexion connexion) {
        this.connexion=connexion;
        this.constants=new Constants();
    }

    public Connexion getConnexion() {
        return connexion;
    }

    Constants getConstants() {
        return constants;
    }
}
