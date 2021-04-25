import java.util.Date;

public class Persona {

    String nome, cognome, luogoNascita, sesso;
    Date data = new Date();
    int id;

    public Persona(String nome, String cognome, String luogoNascita, String sesso, Date data, int id)    //costruttore di Persona
    {
        this.nome=nome;
        this.cognome=cognome;
        this.luogoNascita=luogoNascita;
        this.sesso=sesso;
        this.data=data;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getCognome() {
        return cognome;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public Date getData() {
        return data;
    }

    private boolean check(String codice)                                                                            // verifica che il codice fiscale sia stato inserito correttamente
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

    private boolean isNumero(String s) {                                                                            //verifica se la stringa inserita è composta da numeri
        for (int i = 0; i < s.length(); i++)
        {
            if(!(Character.isDigit(s.charAt(i))))
                return false;
        }
        return true;
    }

    private boolean isLettera(String s) {                                                                           //verifica se la stringa inserita è composta da lettere

        for (int i = 0; i < s.length(); i++)
        {
            if(!(Character.isLetter(s.charAt(i))))
                return false;
        }
        return true;
    }

    private String generaCodice(Persona persona)                                                                        //metodo che genera il codice fiscale dati dati iniziali
    {
        String codice = "";
        codice += codificaTerna(persona.getCognome());
        codice += codificaTerna(persona.getNome());
        codice += codificaAnno(persona.getData().getYear());
        codice += codificaMese(persona.getData().getMonth());
        codice += codificaGiorno(persona.getData().getDay(), persona.getSesso());
        //codice += codificaLuogo(persona.luogoNascita);
        codice = codice.toUpperCase();
        codice += codificaControllo(codice);

        return codice;
    }

    private boolean isVocale(char c)                                                                                        //verifica se il carattere inserito è una vocale
    {
        char [] Vocali = {'A', 'E', 'I', 'O', 'U'};
        for(int i=0; i<Vocali.length; i++)
        {
            if(c == Vocali[i])
                return  true;
        }
        return false;
    }

    private String codificaTerna(String s)                                                                                  //metodo per la codifica della terna di caratteri nel codice fiscale (per nome e cognome)
    {
        int num=0;
        String terna = "";
        for(int i=0; i<s.length(); i++)
        {
            if(isVocale(s.charAt(i)) == false)                                                                              //verifica che il carattere sia una consonante
            {
                terna += s.charAt(i);
                num++;
                if(num == 3)                                                                                                //se sono stati presi 3 caratteri termina
                    break;
            }
        }
        if(num<3)                                                                                                             // se sono stati presi meno di tre caratteri va a prenere n vocali fino a raggiungere la terna
        {
            for(int i=0; i<s.length(); i++)
            {
                if(isVocale(s.charAt(i)))
                {
                    terna += s.charAt(i);
                    num++;
                    if(num == 3)
                        break;
                }
            }
        }
        return terna;
    }

    private int codificaAnno(int anno)                                                                                              //metodo che codifica le due cifre dell'anno
    {
        anno = anno%100;
        return anno;
    }

    private char codificaMese(int mese)                                                                                                //metodo che restituisce un carattere dato il mese(int)
    {
        char m = ' ';
        switch (mese)
        {
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

    private String codificaGiorno(int giorno, String sesso)                                                                 //metodo che codifica il giorno dato il giorno e il sesso
    {
        String g = "";
        if(sesso.equals("F") || sesso.equals("f"))
        {
            giorno += 40;
            g = Integer.toString(giorno);
        }
        if(giorno < 10)
        {
            g = Integer.toString(giorno);
            g = "0" + g;
        }
        return g;
    }

    /*private  String codificaLuogo(String luogo)
    {

    }*/

    private String codificaControllo(String cod)                                                                    //metodo per codificare l'ultimo carattere di controllo
    {
        int somma = 0;
        for (int i=0; i<cod.length(); i++)
        {
            somma += Integer.parseInt(carattereControllo(cod.charAt(i), i));
        }
        String carattere = String.valueOf(carattere(somma));
        return carattere;
    }

    private String carattereControllo(char c, int indice)                                                       //metodo che restituisce un valore in base alla posizione(pari o dispari) e al carattere passato
    {
        if(indice%2 == 0)
        {
            int n;
            String s = String.valueOf(c);
            if(isNumero(s))                                                                                    //in caso di indice pari il numero coincide con il suo valore ritornato
            {
                return s;
            }
            else
            {
                for(int i=65; i<=90; i++)
                {
                    if((int) c == i)
                    {
                        n = i - 65;
                        s = Integer.toString(n);
                        return s;
                    }
                }
            }
        }
        else
        {
            switch(c)
            {
                case '0':
                    return "1";
                case '1':
                    return "0";
                case '2':
                    return "5";
                case '3':
                    return "7";
                case '4':
                    return "9";
                case '5':
                    return "13";
                case '6':
                    return "15";
                case '7':
                    return "17";
                case '8':
                    return "19";
                case '9':
                    return "21";
                case 'A':
                    return "1";
                case 'B':
                    return "0";
                case 'C':
                    return "5";
                case 'D':
                    return "7";
                case 'E':
                    return "9";
                case 'F':
                    return "13";
                case 'G':
                    return "15";
                case 'H':
                    return "17";
                case 'I':
                    return "19";
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

    private char carattere(int s)
    {
        int n = 0;
        s = s % 26;
        for(int i=65; i<=90; i++)
        {
            if(n == s) {
                char c = (char) i;
                return c;
            }
            n++;
        }
        return '-';
    }

}