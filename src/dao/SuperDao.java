package dao;

abstract class SuperDao {

    private final Connexion connexion;
    private final Constants constants;

    protected SuperDao(Connexion connexion) {
        this.connexion=connexion;
        this.constants=new Constants();
    }

    public Connexion getConnexion() {
        return Connexion.getConnexion() ;
    }

    Constants getConstants() {
        return constants;
    }
}
