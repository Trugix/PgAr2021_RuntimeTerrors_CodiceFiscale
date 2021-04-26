import java.util.ArrayList;
import java.util.Date;

public class DataProcessing
{
    private static ArrayList <String> codici = new ArrayList<String>();
    private static ArrayList <Persona> persone = new ArrayList<Persona>();

    public static boolean matchCode(String s)
    {
        return false;
    }

   public static void addToCodici(String s)
   {
        codici.add(s);
   }
    public static void addToPersone(Persona p)
    {
        persone.add(p);
        p.generaCodice();
    }

    public static ArrayList<String> getCodici() {
        return codici;
    }

    public static ArrayList<Persona> getPersone() {
        return persone;
    }
   public static Date toDate (String s)
   {
       int anno, mese, giorno;
       anno= Integer.parseInt(s.substring(0,4));
       mese=  Integer.parseInt(s.substring(6,7));
       giorno = Integer.parseInt(s.substring(8,10));
       Date data = new Date(anno-1900, mese-1, giorno); //commento assurdo da fare E.
       return data;
   }
}
