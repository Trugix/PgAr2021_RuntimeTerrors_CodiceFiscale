public class Main
{
    public static void main(String[] args)
    {
        Reader.readCodiciFiscali();
        Reader.readComuni();
        Reader.readInputPersone(); //Chiamo il metodo che legge tutto il file delle persone e le cariche in memoria E.
        DataProcessing.codeSorting();
        Writer.writeOutput();
        for (Persona p : DataProcessing.getPersone())
        {
            System.out.println(p.getCodiceFiscale());
        }
    }
}
