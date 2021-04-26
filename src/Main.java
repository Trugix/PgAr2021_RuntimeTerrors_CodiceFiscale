import it.unibs.fp.mylib.*;

public class Main
{
    public static void main(String[] args) {
        Reader.readInputPersone(); //Chiamo il metodo che legge tutto il file delle persone e le cariche in memoria E.
        System.out.println(DataProcessing.getPersone().get(0).getNome());
        for (Persona p : DataProcessing.getPersone())
        {
            System.out.println(p.getCodiceFiscale());
        }
    }
}
