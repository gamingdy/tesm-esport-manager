package modele;

import exceptions.ErreurDate;

public class mainClass {
	public static void main(String[] args) throws ErreurDate {
		CustomDate d = new CustomDate(2022, 01, 1);
		CustomDate d2 = new CustomDate(2000);
		System.out.println(d.toString());
		System.out.println(d2.toString());
	}
}
