import it.unibs.fp.mylib.*;

public class Menu {

    private final static  String BENVENUTO = "Benvenuto, questo programma permette di verificare o di generare un codice fiscale";
    private final static String SCEGLI_VERIFICA = "Verifica di un codice fiscale";
    private final static String SCEGLI_GENERA = "Genera un codice fiscale";
    private final static String [] SCELTA={SCEGLI_VERIFICA, SCEGLI_GENERA};
    private final static String TITOLO = "Scegli una delle opzioni seguenti";

    public void stampaMenu()
    {
        MyMenu menu = new MyMenu(TITOLO, SCELTA);
        int scelta;
        System.out.println(BENVENUTO);
        scelta = menu.scegli();
        switch(scelta)
        {
            case 1:
                //check(codice);
                break;
            case 2:
                //genera(persona);
                break;
        }
    }
}
