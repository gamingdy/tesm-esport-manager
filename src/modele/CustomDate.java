package modele;

import java.sql.Date;
import java.time.LocalDate;

import exceptions.ErreurDate;

public class CustomDate  implements Comparable<CustomDate>{
    private int annee;
    private int mois;
    private int jour;
    private int heure;
    private int min;
    
	/**
 	* Créer un Date correspondant à minuit de la date passée en paramètre
 	*
 	* @param date la date au format java.sql.Date
 	*/
	public CustomDate(Date date){
		LocalDate localDate = date.toLocalDate();
		this.annee = localDate.getYear();
		this.mois = localDate.getMonthValue();
		this.jour = localDate.getDayOfMonth();
		this.heure = 0;
		this.min = 0;
	}

	/**
 	* Créer un Date correspondant au premier jour de l'année passé en paramètre
 	*
 	* @param annee L'année correspondant à la date
 	*/
    public CustomDate(int annee) {
        this.annee = annee;
        this.mois = 1;
        this.jour = 1;
		this.heure = 0;
		this.min = 0;
    }

	/**
 	* Créer un Date correspondant à minuit de la date passée en paramètre
 	*
 	* @param annee L'année correspondant à la date
	* @param mois Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	* @param jour Le jour correspondant à la date
	* @exception	ErreurDate si le mois est trop grand (>12) ou trop petit (<12)
	* @exception	ErreurDate si le jour est trop petit (<1)
	* @exception	ErreurDate si le jour est trop grand (>31)
	* @exception	ErreurDate si le jour est trop grand (traitement des mois à 30 jours --> (>30))
	* @exception	ErreurDate si le jour est trop grand (traitement de février en année bissextile --> (>29))
	* @exception	ErreurDate si le jour est trop grand (traitement de février hors année bissextile --> (>29))
 	*/
    public CustomDate(int annee, int mois, int jour) throws ErreurDate {
    	this(annee);
    	if (mois < 1 || mois > 12) {
    		throw new ErreurDate("Le mois saisi n'est pas correct");
    	}
    	if (jour < 1) {
    		throw new ErreurDate("Le jour saisi est inférieur à 1");
    	}
    	if (jour > 31) {
    		throw new ErreurDate("Le jour saisi est supérieur à 31");
    	}
    	if ((mois == 4 || mois == 6 || mois == 9 || mois == 11) && (jour > 30)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 30");
    	}
    	if ((mois == 2) && (jour > 29)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 29");
    	}
    	if ((mois == 2) && (jour > 28) && ((annee % 4 != 0) || ((annee % 100 == 0) && (annee % 400 != 0)))) {
    		throw new ErreurDate("Le jour saisi est supérieur à 28");
    	}
        this.mois = mois;
        this.jour = jour;
    }

	/**
 	* Créer un Date correspondant aux paramètres
 	*
 	* @param annee L'année correspondant à la date
	* @param mois Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	* @param jour Le jour correspondant à la date
	* @param heure L'heure correspondant à la date
	* @param min Le nombre de minutes correspondant à la date
	* @exception	ErreurDate si le mois est trop grand (>12) ou trop petit (<12)
	* @exception	ErreurDate si le jour est trop petit (<1)
	* @exception	ErreurDate si le jour est trop grand (>31)
	* @exception	ErreurDate si le jour est trop grand (traitement des mois à 30 jours --> (>30))
	* @exception	ErreurDate si le jour est trop grand (traitement de février en année bissextile --> (>29))
	* @exception	ErreurDate si le jour est trop grand (traitement de février hors année bissextile --> (>29))
	* @exception	ErreurDate si l'heure est trop grande (>23) ou trop petite (<0)
	* @exception	ErreurDate si le nombre de minute est trop grand (>59) ou trop petit (<0)
 	*/
    public CustomDate(int annee, int mois, int jour, int heure, int min) throws ErreurDate {
    	this(annee, mois, jour);
    	if (heure < 0 || heure > 23) {
    		throw new ErreurDate("L'heure saisie n'est pas correcte");
    	}
    	if (min < 0 || min > 59) {
    		throw new ErreurDate("Les minutes saisies ne sont pas correctes");
    	}
        this.heure = heure;
        this.min = min;
    }

	/**
 	* Retourne l'année correspondant à la Date
	* @return l'année, en Integer
 	*/
	public int getAnnee() {
		return this.annee;
	}

	/**
 	* Modifie l'année de la Date en fonction du paramètre
 	*
 	* @param annee L'année correspondant à la date
 	*/
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
 	* Retourne le mois correspondant à la Date
	* @return le mois, en Integer (1 pour janvier, ...)
 	*/
	public int getMois() {
		return this.mois;
	}

	/**
 	* Modifie le mois de la Date en fonction du paramètre
 	*
 	* @param mois Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	* @exception	ErreurDate si le mois est trop grand (>12) ou trop petit (<12)
 	*/
	public void setMois(int mois) throws ErreurDate {
		if (mois < 1 || mois > 12) {
    		throw new ErreurDate("Le mois saisi n'est pas correct");
    	}
		this.mois = mois;
	}

	/**
 	* Retourne le jour correspondant à la Date
	* @return le jour, en Integer
 	*/
	public int getJour() {
		return this.jour;
	}

	/**
 	* Modifie le jour de la Date en fonction du paramètre
 	*
 	* @param jour Le jour correspondant à la date
	* @exception	ErreurDate si le jour est trop petit (<1)
	* @exception	ErreurDate si le jour est trop grand (>31)
	* @exception	ErreurDate si le jour est trop grand (traitement des mois à 30 jours --> (>30))
	* @exception	ErreurDate si le jour est trop grand (traitement de février en année bissextile --> (>29))
	* @exception	ErreurDate si le jour est trop grand (traitement de février hors année bissextile --> (>29))
 	*/
	public void setJour(int jour) throws ErreurDate {
		if (jour < 1) {
    		throw new ErreurDate("Le jour saisi est inférieur à 1");
    	}
    	if (jour > 31) {
    		throw new ErreurDate("Le jour saisi est supérieur à 31");
    	}
    	if ((mois == 4 || mois == 6 || mois == 9 || mois == 11) && (jour > 30)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 30");
    	}
    	if ((mois == 2) && (jour > 29)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 29");
    	}
    	if ((mois == 2) && (jour > 28) && ((annee % 4 != 0) || ((annee % 100 == 0) && (annee % 400 != 0)))) {
    		throw new ErreurDate("Le jour saisi est supérieur à 28");
    	}
		this.jour = jour;
	}

	/**
 	* Retourne l'heure correspondant à la Date
	* @return l'heure, en Integer
 	*/
	public int getHeure() {
		return this.heure;
	}

	/**
 	* Modifie l'heure de la Date en fonction du paramètre
 	*
 	* @param heure L'heure correspondant à la date
 	* @exception	ErreurDate si l'heure est trop grande (>23) ou trop petite (<0)
 	*/
	public void setHeure(int heure) throws ErreurDate {
		if (heure < 0 || heure > 23) {
    		throw new ErreurDate("L'heure saisie n'est pas correcte");
    	}
		this.heure = heure;
	}

	/**
 	* Retourne le nombre de minutes correspondant à la Date
	* @return les minutes, en Integer
 	*/
	public int getMinute() {
		return this.min;
	}

	/**
 	* Modifie l'es minutes de la Date en fonction du paramètre
 	*
 	* @param min le nombre de minutes correspondant à la date
	* @exception	ErreurDate si le nombre de minute est trop grand (>59) ou trop petit (<0)
 	*/
	public void setMinute(int min) throws ErreurDate {
		if (min < 0 || min > 59) {
    		throw new ErreurDate("Les minutes saisies ne sont pas correctes");
    	}
		this.min = min;
	}

	/**
 	* Vérifie si la date passée en paramètre est avant la date active
	* @param CustomDate la date à tester
	* @return true si la date passée en paramètre est avant la date active, false sinon
	* 
 	*/
	public Boolean estAvant(CustomDate Date) {
		if (Date.hashCode() >= this.hashCode()){
			return true;
		}
		return false;
	}

	/**
 	* Vérifie si la date passée en paramètre est après la date active
	* @param CustomDate la date à tester
	* @return true si la date passée en paramètre est après la date active, false sinon
	* 
 	*/
	public Boolean estApres(CustomDate Date) {
		if (Date.hashCode() <= this.hashCode()){
			return true;
		}
		return false;
	}
	
	/**
 	* Converti la CustomDate en Date
	*
	* @return la CustomDate au format sql.Date
	* 
 	*/
	public Date toSQL(){
		LocalDate localDate1 = LocalDate.of(this.annee, this.mois, this.jour);
		return Date.valueOf(localDate1);
	}

	@Override
	public String toString(){
		return annee + "/" + mois + "/" + jour + "  " + heure + ":" + min;
	}

	@Override
	public int hashCode() {
		return (this.annee * 100000000 + this.mois * 1000000 + this.jour * 10000 + this.heure * 100 + this.min);
	}
	
	@Override
	public boolean equals(Object obj){
	  
	  if(obj == null) {
		  return false;
	  }
	  
	  if(obj instanceof CustomDate && this == obj) {
		  return true;
	  }
	  
	  CustomDate CustomDate = (CustomDate)obj;
	  
	  if(this.annee != CustomDate.annee) {
		  return false;
	  }
	  if(this.mois != CustomDate.mois) {
		  return false;
	  }
	  if(this.jour != CustomDate.jour) {
		  return false;
	  }
	  if(this.heure != CustomDate.heure) {
		  return false;
	  }
	  if(this.min != CustomDate.min) {
		  return false;
	  }
	  return true;
	}

	@Override
	public int compareTo(CustomDate c) {
		return this.toSQL().compareTo(c.toSQL());
	}
}