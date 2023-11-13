package modele;

import exceptions.ErreurDate;

public class Custom_Date {
    private int annee;
    private int mois;
    private int jour;
    private int heure;
    private int min;
    
    public Custom_Date(int annee) throws ErreurDate {
    	if (annee < 2000 || annee > 10000) {
    		throw new ErreurDate("L'annee saisie n'est pas correcte");
    	}
        this.annee = annee;
        this.mois = 1;
        this.jour = 1;
    }

    public Custom_Date(int annee, int mois, int jour) throws ErreurDate {
    	if (annee < 2000 || annee > 10000) {
    		throw new ErreurDate("L'annee saisie n'est pas correcte");
    	}
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
    	if ((mois == 2) && (jour > 29) && ((annee % 4 != 0) || ((annee % 100 == 0) && (annee % 400 != 0)))) {
    		throw new ErreurDate("Le jour saisi est supérieur à 29");
    	}
    	if ((mois == 2) && (jour > 28)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 28");
    	}
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }

    public Custom_Date(int annee, int mois, int jour, int heure, int min) throws ErreurDate {
    	if (annee < 2000 || annee > 10000) {
    		throw new ErreurDate("L'annee saisie n'est pas correcte");
    	}
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
    	if ((mois == 2) && (jour > 29) && ((annee % 4 != 0) || ((annee % 100 == 0) && (annee % 400 != 0)))) {
    		throw new ErreurDate("Le jour saisi est supérieur à 29");
    	}
    	if ((mois == 2) && (jour > 28)) {
    		throw new ErreurDate("Le jour saisi est supérieur à 28");
    	}
    	if (heure < 0 || heure > 23) {
    		throw new ErreurDate("L'heure saisie n'est pas correcte");
    	}
    	if (min < 0 || min > 59) {
    		throw new ErreurDate("Les minutes saisies ne sont pas correctes");
    	}
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.heure = heure;
        this.min = min;
    }

	public int getAnnee() {
		return this.annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMois() {
		return this.mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getJour() {
		return this.jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getHeure() {
		return this.heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return this.min;
	}

	public void setMinute(int min) {
		this.min = min;
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
	  
	  if(obj instanceof Custom_Date && this == obj) {
		  return true;
	  }
	  
	  Custom_Date Custom_Date = (Custom_Date)obj;
	  
	  if(this.annee != Custom_Date.annee) {
		  return false;
	  }
	  if(this.mois != Custom_Date.mois) {
		  return false;
	  }
	  if(this.jour != Custom_Date.jour) {
		  return false;
	  }
	  if(this.heure != Custom_Date.heure) {
		  return false;
	  }
	  if(this.min != Custom_Date.min) {
		  return false;
	  }
	  return true;
	}
}