package it.unibs.fp.mylib;

public class Utility {
	/**
	 * Pulisce lo schermo
	 */
	public static void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
	}

	/**
	 * Controlla se la stringa passata è un numero
	 *
	 * @param s
	 * @return
	 */
	public static boolean isNumero(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isDigit(s.charAt(i))))
				return false;
		}
		return true;
	}

	/**
	 * Controlla se la stringa è una lettera
	 *
	 * @param s
	 * @return
	 */
	public static boolean isLettera(String s) {

		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isLetter(s.charAt(i))))
				return false;
		}
		return true;
	}

	/**
	 * Controlla se il carattere è una vocale
	 *
	 * @param c
	 * @return
	 */
	public static boolean isVocale(char c) {
		char[] Vocali = {'A', 'E', 'I', 'O', 'U'};
		for (int i = 0; i < Vocali.length; i++) {
			if (c == Vocali[i])
				return true;
		}
		return false;
	}
}
