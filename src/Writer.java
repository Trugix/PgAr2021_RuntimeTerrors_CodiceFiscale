import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

public class Writer {
	/**
	 * scrive tutte sul file xml formattato in modo circa decente
	 */
	public static void writeOutput() {
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("XML/output/codiciPersone.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("output"); // scrittura del tag radice <output>
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("persone");
			xmlw.writeAttribute("number", Integer.toString(DataProcessing.getPersone().size()));
			xmlw.writeCharacters("\n    ");
			for (Persona p : DataProcessing.getPersone()) {
				xmlw.writeStartElement("persona");
				xmlw.writeAttribute("id", Integer.toString(p.getId()));
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(p.getNome());
				xmlw.writeEndElement(); //</nome>
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(p.getCognome());
				xmlw.writeEndElement(); //</cognome>
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(p.getSesso());
				xmlw.writeEndElement();//</sesso>
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(p.getLuogoNascita());
				xmlw.writeEndElement();//</luogo_nascita>
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("data_nascita");
				xmlw.writeCharacters(p.getData().dataToString());
				xmlw.writeEndElement();//</data_nascita>
				xmlw.writeCharacters("\n        ");
				xmlw.writeStartElement("codice_fiscale");
				if (p.isMatched()) xmlw.writeCharacters(p.getCodiceFiscale());
				else xmlw.writeCharacters("ASSENTE");
				xmlw.writeEndElement(); //</codice_fiscale>
				xmlw.writeCharacters("\n    ");
				xmlw.writeEndElement(); //</persona>
				xmlw.writeCharacters("\n    ");
			}
			xmlw.writeEndElement(); // </persone>
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("codici");
			xmlw.writeAttribute("numero", Integer.toString(DataProcessing.getCodici().size()));
			xmlw.writeCharacters("\n    ");

			xmlw.writeStartElement("invalidi");
			xmlw.writeAttribute("numero", Integer.toString(DataProcessing.getCodiciInvalidi().size()));
			xmlw.writeCharacters("\n        ");
			int i = 0;
			for (String s : DataProcessing.getCodiciInvalidi()) {
				xmlw.writeStartElement("codice");
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeCharacters(s);
				xmlw.writeEndElement(); //</codice>
				xmlw.writeCharacters("\n        ");
				i++;
			}
			xmlw.writeEndElement(); //</invalidi>
			xmlw.writeCharacters("\n    ");
			xmlw.writeStartElement("spaiati");
			xmlw.writeAttribute("numero", Integer.toString(DataProcessing.getCodiciSpaiati().size()));
			xmlw.writeCharacters("\n        ");
			i = 0;
			for (String s : DataProcessing.getCodiciSpaiati()) {
				xmlw.writeStartElement("codice");
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeCharacters(s);
				xmlw.writeEndElement();//</codice>
				xmlw.writeCharacters("\n        ");
				i++;
			}
			xmlw.writeEndElement();//</spaiati>
			xmlw.writeCharacters("\n    ");
			xmlw.writeEndElement();//</codici>
			xmlw.writeCharacters("\n");
			xmlw.writeEndElement();//</output>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
			System.out.println("Output generato, arrivederci");
		} catch (Exception e) {
			System.out.println("Errore nel writer:");
			System.out.println(e.getMessage());
		}
	}
}
