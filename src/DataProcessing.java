import java.util.ArrayList;

public class DataProcessing {
	private static ArrayList<String> codici = new ArrayList<String>();
	private static ArrayList<String> codiciValidi = new ArrayList<String>();
	private static ArrayList<String> codiciInvalidi = new ArrayList<String>();
	private static ArrayList<String> codiciAppaiati = new ArrayList<String>();
	private static ArrayList<String> codiciSpaiati = new ArrayList<String>();
	private static ArrayList<Persona> persone = new ArrayList<Persona>();
	private static ArrayList<Comune> comuni = new ArrayList<Comune>();

	public static void addToCodici(String s) {
		codici.add(s);
	}

	public static void addToComuni(Comune c) {
		comuni.add(c);
	}

	public static void addToPersone(Persona p) {
		persone.add(p);
		p.setCodiceFiscale(generaCodice(p));
	}

	public static ArrayList<Comune> getComuni() {
		return comuni;
	}

	public static ArrayList<String> getCodici() {
		return codici;
	}

	public static ArrayList<Persona> getPersone() {
		return persone;
	}

	public static ArrayList<String> getCodiciInvalidi() {
		return codiciInvalidi;
	}

	public static ArrayList<String> getCodiciSpaiati() {
		return codiciSpaiati;
	}

	/**
	 * data una stringa la trasforma in una data
	 *
	 * @param s
	 * @return
	 */
	public static Data toDate(String s) {
		int anno, mese, giorno;
		anno = Integer.parseInt(s.substring(0, 4));
		mese = Integer.parseInt(s.substring(5, 7));
		giorno = Integer.parseInt(s.substring(8, 10));
		return new Data(anno, mese, giorno);
	}

	/**
	 * sorta i codici nelle liste corrispondenti
	 */
	public static void codeSorting() {
		boolean find;
		for (String s : codici) {
			if (check(s)) codiciValidi.add(s);
			else codiciInvalidi.add(s);
		}
		for (String s : codiciValidi) {
			find = false;
			for (Persona p : persone) {
				if (p.getCodiceFiscale().equals(s)) {
					codiciAppaiati.add(s);
					find = true;
					p.setMatched(true);
					break;
				}
			}
			if (!find) codiciSpaiati.add(s);
		}
	}

	/**
	 * controlla la validità di un codice fiscale
	 *
	 * @param codice
	 * @return
	 */
	public static boolean check(String codice) {
		codice = codice.toUpperCase();
		if (codice.length() != 16) return false;
		String cognome = codice.substring(0, 2);
		String nome = codice.substring(3, 5);
		String anno = codice.substring(6, 7);
		String mese = Character.toString(codice.charAt(8));
		String giorno = codice.substring(9, 10);
		String luogoLettera = Character.toString(codice.charAt(11));
		String luogoNum = codice.substring(12, 14);
		String controllo = Character.toString(codice.charAt(15));

		return isLettera(cognome) && isLettera(nome) && isNumero(anno) && isLettera(mese) && isNumero(giorno) && isNumero(luogoNum) && isLettera(luogoLettera) && isLettera(controllo);
	}

	/**
	 * controlla se la stringa passata è un numero
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
	 * controlla se la stringa è una lettera
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
	 * controlla se il carattere è una vocale
	 *
	 * @param c
	 * @return
	 */
	public static boolean isVocale(char c) {
		char[] Vocali = {'A', 'E', 'I', 'O', 'U'};
		for (char value : Vocali) {
			if (c == value)
				return true;
		}
		return false;
	}

	/**
	 * genera il codice fiscale della persona chiamando le varie codifiche
	 */
	public static String generaCodice(Persona p) {
		String codice = "";
		codice += codificaCognome(p.getCognome());
		codice += codificaNome(p.getNome());
		codice += codificaAnno(p.getData().getAnno());
		codice += codificaMese(p.getData().getMese());
		codice += codificaGiorno(p.getData().getGiorno(), p.getSesso());
		codice += codificaLuogo(p.getLuogoNascita());
		codice = codice.toUpperCase();
		codice += codificaControllo(codice);
		if (controlloOmocodia(codice))
			codice = cambiaCodice(codice);
		return codice;
	}

	/**
	 * Codifica le prime tre lettere del codice fiscale partendo da una stringa
	 *
	 * @param cognome l'attributo cognome della persona
	 * @return
	 */
	private static String codificaCognome(String cognome)                                                                                  //metodo per la codifica della terna del cognome E.
	{
		int num = 0;
		String terna = "";
		for (int i = 0; i < cognome.length(); i++) {
			if (!DataProcessing.isVocale(cognome.charAt(i)))                 //verifica che il carattere sia una consonante
			{
				terna += cognome.charAt(i);
				num++;
				if (num == 3)                                  //se sono stati presi 3 caratteri termina
					break;
			}
		}
		if (num < 3)                         // se sono stati presi meno di tre caratteri va a prenere n vocali fino a raggiungere la terna
		{
			for (int i = 0; i < cognome.length(); i++) {
				if (DataProcessing.isVocale(cognome.charAt(i))) {
					terna += cognome.charAt(i);
					num++;
					if (num == 3)
						break;
				}
			}
		}
		return terna;
	}

	/**
	 * codifica le tre lettere corrispondenti al nome da una stringa
	 *
	 * @param nome
	 * @return
	 */
	private static String codificaNome(String nome)          //metodo per la codifica della terna di caratteri nel codice fiscale (per nome e cognome)
	{
		int num = 0, consonanti = 0;
		String terna = "";
		for (int i = 0; i < nome.length(); i++) {
			if (!DataProcessing.isVocale(nome.charAt(i)))
				consonanti++;
		}
		if (consonanti >= 4) {
			for (int i = 0; i < nome.length(); i++) {
				if (!DataProcessing.isVocale(nome.charAt(i)))          //verifica che il carattere sia una consonante
				{
					if (i == 2) continue;
					terna += nome.charAt(i);
					num++;
					if (num == 3)          //se sono stati presi 3 caratteri termina
						break;
				}
			}
			return terna;
		} else {
			return codificaCognome(nome);
		}
	}

	/**
	 * codifica le due cifre dell'anno
	 *
	 * @param anno
	 * @return
	 */
	private static String codificaAnno(int anno) {
		anno = anno % 100;
		if (anno <= 10) return ("0" + anno);
		return Integer.toString(anno);
	}

	/**
	 * metodo che restituisce un carattere dato il mese(int)
	 *
	 * @param mese
	 * @return
	 */
	private static char codificaMese(int mese) {
		char m = ' ';
		switch (mese) {
			case 1:
				m = 'A';
				break;
			case 2:
				m = 'B';
				break;
			case 3:
				m = 'C';
				break;
			case 4:
				m = 'D';
				break;
			case 5:
				m = 'E';
				break;
			case 6:
				m = 'H';
				break;
			case 7:
				m = 'L';
				break;
			case 8:
				m = 'M';
				break;
			case 9:
				m = 'P';
				break;
			case 10:
				m = 'R';
				break;
			case 11:
				m = 'S';
				break;
			case 12:
				m = 'T';
				break;
		}
		return m;
	}

	/**
	 * metodo che codifica il giorno dato il giorno e il sesso
	 *
	 * @param giorno
	 * @param sesso
	 * @return
	 */
	private static String codificaGiorno(int giorno, String sesso) {
		String g;
		if (sesso.equals("F") || sesso.equals("f")) {
			giorno += 40;
		}
		if (giorno < 10) {
			g = Integer.toString(giorno);
			g = "0" + g;
		} else {
			g = Integer.toString(giorno);
		}
		return g;
	}

	/**
	 * dato il nome del luogo lo cerca nella lista dei comuni e ritorna il codice
	 *
	 * @param luogo
	 * @return
	 */
	public static String codificaLuogo(String luogo) {
		for (Comune c : DataProcessing.getComuni()) {
			if (c.getNome().equals(luogo))
				return c.getCodice();
		}
		return "FUCK";
	}

	/**
	 * metodo per codificare l'ultimo carattere di controllo
	 *
	 * @param cod
	 * @return
	 */
	private static String codificaControllo(String cod) {
		int somma = 0;
		for (int i = 0; i < cod.length(); i++) {
			somma += Integer.parseInt(carattereControllo(cod.charAt(i), i));
		}
		return String.valueOf(carattere(somma));
	}

	/**
	 * metodo che restituisce un valore in base alla posizione(pari o dispari) e al carattere passato
	 *
	 * @param c
	 * @param indice
	 * @return
	 */
	private static String carattereControllo(char c, int indice) {
		if (indice % 2 != 0) {
			int n;
			String s = String.valueOf(c);
			if (DataProcessing.isNumero(s))                  //in caso di indice pari il numero coincide con il suo valore ritornato
			{
				return s;
			} else {
				for (int i = 65; i <= 90; i++)             //in caso di indice pari l'incremento è lineare(A-Z = 0-25) quindi è possibile restituire il valore tramite conversione del codice ASCII
				{
					if ((int) c == i) {
						n = i - 65;
						s = Integer.toString(n);
						return s;
					}
				}
			}
		} else {
			switch (c)           //caso di indice dispari
			{
				case '0':
				case 'A':
					return "1";
				case '1':
				case 'B':
					return "0";
				case '2':
				case 'C':
					return "5";
				case '3':
				case 'D':
					return "7";
				case '4':
				case 'E':
					return "9";
				case '5':
				case 'F':
					return "13";
				case '6':
				case 'G':
					return "15";
				case '7':
				case 'H':
					return "17";
				case '8':
				case 'I':
					return "19";
				case '9':
				case 'J':
					return "21";
				case 'K':
					return "2";
				case 'L':
					return "4";
				case 'M':
					return "18";
				case 'N':
					return "20";
				case 'O':
					return "11";
				case 'P':
					return "3";
				case 'Q':
					return "6";
				case 'R':
					return "8";
				case 'S':
					return "12";
				case 'T':
					return "14";
				case 'U':
					return "16";
				case 'V':
					return "10";
				case 'W':
					return "22";
				case 'X':
					return "25";
				case 'Y':
					return "24";
				case 'Z':
					return "23";
			}
		}
		return "-1";
	}

	/**
	 * metodo che calcola il resto e restituisce il carattere corrispondente tramite conversione del codice ASCII
	 *
	 * @param s
	 * @return
	 */
	private static char carattere(int s) {
		int n = 0;
		s = s % 26;
		for (int i = 65; i <= 90; i++) {
			if (n == s) {
				return (char) i;
			}
			n++;
		}
		return '-';
	}

	/**
	 * controlla se esistono codici uguali nella lista
	 *
	 * @param cod
	 * @return
	 */
	private static boolean controlloOmocodia(String cod) {
		boolean omocodia = false;
		for (int i = 0; i < DataProcessing.getCodici().size() - 1; i++) {
			omocodia = DataProcessing.getCodici().get(i).equals(cod);
		}
		return omocodia;
	}

	/**
	 * in caso di omonimia cambia il codice alfanumerico del comune poi rigenera il carattere di controllo
	 *
	 * @param cod
	 * @return
	 */
	private static String cambiaCodice(String cod) {
		String s;
		s = cod.substring(12, 14);
		s=s.replace('0', 'L');
		s=s.replace('1', 'M');
		s=s.replace('2', 'N');
		s=s.replace('3', 'P');
		s=s.replace('4', 'Q');
		s=s.replace('5', 'R');
		s=s.replace('6', 'S');
		s=s.replace('7', 'T');
		s=s.replace('8', 'U');
		s=s.replace('9', 'V');
		cod = eliminaUltimo(cod);
		cod=cod.replace(cod.substring(12, 14), s);
		cod += codificaControllo(cod);
		return cod;
	}

	private static String eliminaUltimo(String s) {
		String c;
		c = s.substring(0, s.length() - 1);
		return c;
	}


}

