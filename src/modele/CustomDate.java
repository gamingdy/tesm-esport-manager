package modele;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class CustomDate implements Comparable<CustomDate> {
	private LocalDateTime date;

	/**
	 * Crée une CustomDate correspondant à partir d'un Timestamp
	 *
	 * @param date la date au format java.sql.Timestamp
	 */
	public CustomDate(Timestamp date) {
		this.date = date.toLocalDateTime();
	}

	/**
	 * Crée une CustomDate correspondant au premier jour de l'année passé en paramètre
	 *
	 * @param annee L'année correspondant à la date
	 **/
	public CustomDate(int annee) {
		this(annee, 1, 1, 0, 0);
	}

	/**
	 * Crée une CustomDate correspondant à minuit de la date passée en paramètre
	 *
	 * @param annee L'année correspondant à la date
	 * @param mois  Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	 * @param jour  Le jour correspondant à la date
	 * @throws DateTimeException si la date n'est pas valide
	 **/
	public CustomDate(int annee, int mois, int jour) throws DateTimeException {
		this(annee, mois, jour, 0, 0);
	}

	/**
	 * Créer une CustomDate correspondant aux paramètres
	 *
	 * @param annee L'année correspondant à la date
	 * @param mois  Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	 * @param jour  Le jour correspondant à la date
	 * @param heure L'heure correspondant à la date
	 * @param min   Le nombre de minutes correspondant à la date
	 * @throws DateTimeException si la date n'est pas valide
	 **/
	public CustomDate(int annee, int mois, int jour, int heure, int min) throws DateTimeException {
		this.date = LocalDateTime.of(annee, mois, jour, heure, min);
	}

	public static CustomDate now() {
		return new CustomDate(Timestamp.valueOf(LocalDateTime.now()));
	}

	/**
	 * Retourne l'année correspondant à la Date
	 *
	 * @return l'année, en Integer
	 **/
	public int getAnnee() {
		return this.date.getYear();
	}

	/**
	 * Modifie l'année de la Date en fonction du paramètre
	 *
	 * @param annee L'année correspondant à la date
	 **/
	public void setAnnee(int annee) {
		this.date = this.date.withYear(annee);
	}

	/**
	 * Retourne le mois correspondant à la Date
	 *
	 * @return le mois, en Integer (1 pour janvier, ...)
	 **/
	public int getMois() {
		return this.date.getMonthValue();
	}

	/**
	 * Modifie le mois de la Date en fonction du paramètre
	 *
	 * @param mois Le mois correspondant à la date, en Integer (1 pour janvier, ...)
	 * @throws DateTimeException si le mois est trop grand (>12) ou trop petit (<12)
	 **/
	public void setMois(int mois) throws DateTimeException {
		this.date = this.date.withMonth(mois);
	}

	/**
	 * Retourne le jour correspondant à la Date
	 *
	 * @return le jour, en Integer
	 **/
	public int getJour() {
		return this.date.getDayOfMonth();
	}

	/**
	 * Modifie le jour de la Date en fonction du paramètre
	 *
	 * @param jour Le jour correspondant à la date
	 * @throws DateTimeException si le jour est négatif ou trop grand
	 */
	public void setJour(int jour) throws DateTimeException {
		this.date = this.date.withDayOfMonth(jour);
	}

	/**
	 * Retourne l'heure correspondant à la Date
	 *
	 * @return l'heure, en Integer
	 **/
	public int getHeure() {
		return this.date.getHour();
	}

	/**
	 * Modifie l'heure de la Date en fonction du paramètre
	 *
	 * @param heure L'heure correspondant à la date
	 * @throws DateTimeException si l'heure est négative ou trop grande (>23)
	 **/
	public void setHeure(int heure) throws DateTimeException {
		this.date = this.date.withHour(heure);
	}

	/**
	 * Retourne le nombre de minutes correspondant à la Date
	 *
	 * @return les minutes, en Integer
	 **/
	public int getMinute() {
		return this.date.getMinute();
	}

	/**
	 * Modifie les minutes de la Date en fonction du paramètre
	 *
	 * @param min le nombre de minutes correspondant à la date
	 * @throws DateTimeException si le nombre de minutes est trop grand (>59) ou trop petit (<0)
	 **/
	public void setMinute(int min) throws DateTimeException {
		this.date = this.date.withMinute(min);
	}

	/**
	 * Vérifie si la date passée en paramètre est après la date active
	 *
	 * @param date la date à tester
	 * @return true si la date passée en paramètre est après la date active, false sinon
	 **/
	public Boolean estAvant(CustomDate date) {
		return this.date.isBefore(date.date);
	}

	/**
	 * Vérifie si la date passée en paramètre est avant la date active
	 *
	 * @param date la date à tester
	 * @return true si la date passée en paramètre est avant la date active, false sinon
	 **/
	public Boolean estApres(CustomDate date) {
		return this.date.isAfter(date.date);
	}

	/**
	 * Converti la CustomDate en Date
	 *
	 * @return la CustomDate au format java.sql.Timestamp
	 **/
	public Timestamp toSQL() {
		return Timestamp.valueOf(this.date);
	}

	@Override
	public int compareTo(CustomDate c) {
		return this.date.compareTo(c.date);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CustomDate)) {
			return false;
		}

		CustomDate dateComparable = (CustomDate) obj;

		return this.date.equals(dateComparable.date);
	}

	@Override
	public String toString() {
		String heure = String.format("%02d", this.getHeure());
		String minute = String.format("%02d", this.getMinute());
		String jour = String.format("%02d", this.getJour());
		String mois = String.format("%02d", this.getMois());
		return heure + "h" + minute + " " + jour + "/" + mois + "/" + this.getAnnee();

	}
}
