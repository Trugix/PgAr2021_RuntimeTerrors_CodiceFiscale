import java.util.ArrayList;
import java.util.Date;

public class DataProcessing
{
    private static ArrayList <String> codici = new ArrayList<String>();
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
    }

    public static ArrayList<String> getCodici() {
        return codici;
    }

    public static ArrayList<Persona> getPersone() {
        return persone;
    }
   public static Date toDate (String s)
   {
       Date data = new Date(Integer.parseInt(s.substring(0,3), Integer.parseInt(s.substring(5,6),Integer.parseInt(s.substring(8,9)))));
       return data;
   }
}
