package modele;


public class mainClass {
	public static void main(String[] args) {
		Custom_Date d = new Custom_Date(2022, 01, 1);
		Custom_Date d2 = new Custom_Date(2000);
		System.out.println(d2.toString());
	}
}
