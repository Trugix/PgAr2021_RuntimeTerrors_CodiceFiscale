import it.unibs.fp.mylib.*;

public class Main {
    public static void main(String[] args)
    {
        Reader.readInputPersone();
        System.out.println(DataProcessing.getPersone().get(0).getNome());
    }
}
