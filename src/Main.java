import it.unibs.fp.mylib.*;

public class Main
{
    public static void main(String[] args)
    {
        Reader.readCodiciFiscali();
        Reader.readComuni();
        Reader.readInputPersone(); //Chiamo il metodo che legge tutto il file delle persone e le cariche in memoria E.
        for (Persona p : DataProcessing.getPersone())
        {
            System.out.println(p.getCodiceFiscale());
        }
    }
}
