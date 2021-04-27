import java.util.ArrayList;

public class DataProcessing {
    private static ArrayList<String> codici = new ArrayList<String>();
    private static ArrayList<String> codiciValidi = new ArrayList<String>();
    private static ArrayList<String> codiciPresenti = new ArrayList<String>();
    private static ArrayList<String> codiciSpaiati = new ArrayList<String>();
    private static ArrayList<String> codiciInvalidi = new ArrayList<String>();
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
        p.generaCodice();
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

    public static Data toDate(String s) //data una stringa la trasforma in una data
    {
        int anno, mese, giorno;
        anno = Integer.parseInt(s.substring(0, 4));
        mese = Integer.parseInt(s.substring(5, 7));
        giorno = Integer.parseInt(s.substring(8, 10));
        Data data = new Data(anno, mese, giorno);
        return data;
    }

    public static void codeSorting() {
        for (String c : codiciValidi) {
            for (Persona p : persone) {
                if (p.getCodiceFiscale().equals(c)) codiciPresenti.add(c);
                else codiciSpaiati.add(c);
            }
        }
    }

    public static boolean check(String codice)                                                                            // verifica che il codice fiscale sia stato inserito correttamente
    {
        codice = codice.toUpperCase();
        String cognome = codice.substring(0, 3);
        String nome = codice.substring(3, 6);
        String anno = codice.substring(6, 8);
        String mese = codice.substring(8, 9);
        String giorno = codice.substring(9, 11);
        String luogoNum = codice.substring(11, 12);
        String luogoLettera = codice.substring(12, 15);
        String controllo = codice.substring(15, 16);

        if (isLettera(cognome) && isLettera(nome) && isNumero(anno) && isLettera(mese) && isNumero(giorno) && isNumero(luogoNum) && isLettera(luogoLettera) && isLettera(controllo))
            return true;
        else
            return false;
    }

    public static boolean isNumero(String s) {                                                                            //verifica se la stringa inserita è composta da numeri
        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isDigit(s.charAt(i))))
                return false;
        }
        return true;
    }

    public static boolean isLettera(String s) {                                                                           //verifica se la stringa inserita è composta da lettere

        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isLetter(s.charAt(i))))
                return false;
        }
        return true;
    }

    public static boolean isVocale(char c)                                                                                        //verifica se il carattere inserito è una vocale
    {
        char[] Vocali = {'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i < Vocali.length; i++) {
            if (c == Vocali[i])
                return true;
        }
        return false;
    }

}

