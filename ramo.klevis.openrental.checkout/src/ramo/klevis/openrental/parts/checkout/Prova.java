package ramo.klevis.openrental.parts.checkout;


public class Prova {

	public static String getME(String s) {
		System.err.println(s.charAt(0));

		return s;
	}

	public static void main(String[] args) {
		boolean b = false;

		Prova.getME(b ? null : new String(" "));
		
		
	}
}
