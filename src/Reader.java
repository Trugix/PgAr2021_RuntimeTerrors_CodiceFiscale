import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;

public class Reader {
	private static String filenameCod = "codiciFiscali.xml";
	private static String filenamePers = "inputPersone.xml";

	/**
	 * legge il file dei codici fiscali e aggiunge il codice alla lista codici
	 */
	public static void readCodiciFiscali() {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;

		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/" + filenameCod));
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
					String s = xmlr.getText();
					if (!s.contains("\n")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n  "
						DataProcessing.addToCodici(s);
				}
				xmlr.next();
			}
		} catch (Exception e) {
			System.out.println("Errore in readCodiciFiscali");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * legge il file delle persone, crea un'istanza di persona e la adda alla lista che le contiene
	 */
	public static void readInputPersone() {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;

		try {
			int id = 0;
			String tagName = "";
			String nome = "", cognome = "", comune = "", sesso = "";
			Data dataNascita = null;
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/" + filenamePers));
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType()) { // switch sul tipo di evento
					case XMLStreamConstants.START_ELEMENT: // inizio di un elemento
						tagName = xmlr.getLocalName();
						if (tagName.equals("persona"))
							id = Integer.parseInt(xmlr.getAttributeValue(0));
						break;
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento
						if (xmlr.getLocalName().equals("persona"))
							DataProcessing.addToPersone(new Persona(nome, cognome, comune, sesso, dataNascita, id)); //quando arriva all'endtag costruisce la persona
						break;
					case XMLStreamConstants.CHARACTERS:
						String s = xmlr.getText();
						if (s.contains("\n")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n "
							break;
						switch (tagName) {
							case "nome":
								nome = s;
								break;
							case "cognome":
								cognome = s;
								break;
							case "sesso":
								sesso = s;
								break;
							case "comune_nascita":
								comune = s;
								break;
							case "data_nascita":
								dataNascita = DataProcessing.toDate(s);
								break;
						}
						break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			System.out.println("Errore in Input Persone");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * legge il file comuni e crea una nuova istanza di comuni e la adda alla lista comuni
	 */
	public static void readComuni() {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;

		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(new FileInputStream("XML/input/comuni.xml"));
			String nome = "", codice = "", tagName = "";
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType()) { // switch sul tipo di evento
					case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
						tagName = xmlr.getLocalName();
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						if (xmlr.getLocalName().equals("comune"))
							DataProcessing.addToComuni(new Comune(nome, codice));
						break;
					case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
						String s = xmlr.getText();
						if (s.contains("\n")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n "
							break;
						switch (tagName) {
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
			System.out.println("Errore in comuni");
			System.out.println(e.getMessage());
		}
	}

	public static void setFilenameCod(String name) {
		Reader.filenameCod = name;
	}

	public static void setFilenamePers(String name) {
		Reader.filenamePers = name;
	}
}