import java.util.ArrayList;
import java.util.Date;

public class DataProcessing
{
    private static ArrayList <String> codici = new ArrayList<String>();
    public static boolean matchCode(String s)
    {
        return false;
    }

   public static void addToList(String s)
   {
        codici.add(s);
   }

   public static Date toDate (String s)
   {
       Date data = new Date(Integer.parseInt(s.substring(0,3), Integer.parseInt(s.substring(5,6),Integer.parseInt(s.substring(8,9)))));
       return data;
   }
}
