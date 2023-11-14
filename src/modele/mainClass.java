package modele;

import exceptions.ErreurDate;

public class mainClass {
	public static void main(String[] args) throws ErreurDate {
		CustomDate d = new CustomDate(1900, 02, 29);
		CustomDate d2 = new CustomDate(2000);
		System.out.println(d.toSQL());
		System.out.println(d2.toString());
		System.out.println(d2.estAvant(d));
	}
}
