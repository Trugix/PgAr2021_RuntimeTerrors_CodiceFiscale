import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class Reader {
   /* private static final String FILE_NAME_CODICI = "codiciFiscali.xml";
    private static final String FILE_NAME_COMUNI = "comuni.xml";
    private static final String FILE_NAME_PERSONE = "inputPersone.xml";*/

    public static void readCodiciFiscali() {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try {
            int size = 0;
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/codiciFiscali.xml"));
            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    DataProcessing.addToCodici(xmlr.getText());
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println("Errore");
            System.out.println(e.getMessage());
        }
    }

    public static void readInputPersone() {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try {
            int id = 0;
            String tagName = "";
            String nome="", cognome="", comune="", sesso="";
            Date dataNascita = null;
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/inputPersone.xml"));
            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlr.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                        tagName = xmlr.getLocalName();
                        if(tagName.equals("persona"))
                            id = Integer.parseInt(xmlr.getAttributeValue(0));
                        break;
                    case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
                       if(xmlr.getLocalName().equals("persona"))
                           DataProcessing.addToPersone(new Persona(nome, cognome, comune, sesso, dataNascita, id));
                        break;
                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                        String s = xmlr.getText();
                        if(s.contains("\n")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n "
                                break;
                        switch (tagName){
                            case "nome":
                                nome = xmlr.getText();
                                break;
                            case "cognome":
                                cognome = xmlr.getText();
                                break;
                            case "sesso":
                                sesso = xmlr.getText();
                                break;
                            case "comune_nascita":
                                comune = xmlr.getText();
                                break;
                            case "data_nascita":
                                dataNascita = DataProcessing.toDate(xmlr.getText());
                                break;
                        }
                        break;
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println("Errore");
            System.out.println(e.getMessage());
        }
    }

    public static void readComuni()
    {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        try
        {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/comuni.xml"));
            String nome="",codice="",tagName="";
            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                switch (xmlr.getEventType()) { // switch sul tipo di evento
                    case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                        tagName = xmlr.getLocalName();
                    case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
                        if(xmlr.getLocalName().equals("comune"))
                            DataProcessing.addToComuni(new Comune(nome, codice));
                        break;
                    case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                        String s = xmlr.getText();
                        if(s.contains("\n")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n "
                            break;
                        switch (tagName)
                        {
                            case "nome":
                                nome = s;
                                break;
                            case "codice":
                                codice = s;
                                break;
                        }
                        break;
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println("Errore");
            System.out.println(e.getMessage());
        }
    }

}