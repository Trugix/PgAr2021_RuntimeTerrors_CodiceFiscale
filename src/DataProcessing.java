import java.util.ArrayList;
import java.util.Date;

public class DataProcessing
{
    private static ArrayList <String> codici = new ArrayList<String>();
    private static ArrayList <String> codiciValidi = new ArrayList<String>();
    private static ArrayList <String> codiciPresenti = new ArrayList<String>();
    private static ArrayList <String> codiciSpaiati = new ArrayList<String>();
    private static ArrayList <String> codiciInvalidi = new ArrayList<String>();
    private static ArrayList <Persona> persone = new ArrayList<Persona>();
    private static ArrayList <Comune> comuni = new ArrayList<Comune>();

   public static void addToCodici(String s)
   {
        codici.add(s);
   }

    public static void addToComuni(Comune c)
    {
        comuni.add(c);
    }
   public static void addToPersone(Persona p)
    {
        persone.add(p);
        p.generaCodice();
    }

    public static ArrayList<Comune> getComuni()
    {
        return comuni;
    }

    public static ArrayList<String> getCodici() {
        return codici;
    }

    public static ArrayList<Persona> getPersone() {
        return persone;
    }

   public static Data toDate (String s) //data una stringa la trasforma in una data
   {
       int anno, mese, giorno;
       anno= Integer.parseInt(s.substring(0,4));
       mese=  Integer.parseInt(s.substring(5,7));
       giorno = Integer.parseInt(s.substring(8,10));
       Data data = new Data(anno, mese, giorno);
       return data;
   }

   public static void codeSorting()
   {
       for (String c: codiciValidi)
       {
           for (Persona p: persone)
           {
               if (p.getCodiceFiscale().equals(c)) codiciPresenti.add(c);
               else codiciSpaiati.add(c);
           }
       }
   }
}
