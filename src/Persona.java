import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Persona {

    String nome, cognome, luogoNascita;
    char sesso;
    Date data = new Date();

    public Persona(String nome, String cognome, String luogoNascita, String sesso, Date data, int id)
    {
        this.nome=nome;
        this.cognome=cognome;
        this.luogoNascita=luogoNascita;
        this.sesso=sesso;
        this.data=data;
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

    private boolean check(String codice)
    {
        codice.toUpperCase();
        String cognome = codice.substring(0, 3);
        String nome = codice.substring(3, 6);
        String anno = codice.substring(6, 8);
        String mese = codice.substring(8, 9);
        String giorno = codice.substring(9, 11);
        String luogoNum = codice.substring(11, 12);
        String luogoLettera = codice.substring(12, 15);
        String controllo = codice.substring(15, 16);

        boolean risultato = false;

        if (isLettera(cognome) && isLettera(nome) && isNumero(anno) && isLettera(mese) && isNumero(giorno) && isNumero(luogoNum) && isLettera(luogoLettera) && isLettera(controllo))
            return true;
        else
            return false;
    }

    private boolean isNumero(String s) {

        char c;
        for (int i = 0; i < s.length(); i++)
        {
            if(!(Character.isDigit(s.charAt(i))))
                return false;
        }
        return true;
    }

    private boolean isLettera(String s) {

        for (int i = 0; i < s.length(); i++)
        {
            if(!(Character.isLetter(s.charAt(i))))
                return false;
        }
        return true;
    }

    private String generaCodice(Persona persona)
    {
        String codice = "";
        codice += codificaTerna(persona.getCognome());
        codice += codificaTerna(persona.getNome());
        return codice;
    }

    private boolean isVocale(char c)
    {
        char [] Vocali = {'A', 'E', 'I', 'O', 'U'};
        for(int i=0; i<Vocali.length; i++)
        {
            if(c == Vocali[i])
                return  true;
        }
        return false;
    }

    private String codificaTerna(String s)
    {
        int num=0;
        String terna = "";
        for(int i=0; i<s.length(); i++)
        {
            if(isVocale(s.charAt(i)) == false)
            {
                terna += s.charAt(i);
                num++;
                if(num == 3)
                    break;
            }
        }
        if(num<3)
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
}