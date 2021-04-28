import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

public class Writer
{
    public static void writeOutput()
    {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try{
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("XML/output/codiciPersone.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
            String[] autori = {"Edoardo", "Ruggero", "Andrea"};

                xmlw.writeStartElement("output"); // scrittura del tag radice <output>
                for (int i = 0; i < autori.length; i++)
                {
                    xmlw.writeStartElement("autore"); // scrittura del tag autore...
                    xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
                    xmlw.writeCharacters(autori[i]); // ...e content dato
                    xmlw.writeEndElement(); // chiusura di </autore>
                }
                xmlw.writeStartElement("persone");
                for (Persona p: DataProcessing.getPersone())
                {
                    xmlw.writeStartElement("persona");
                    xmlw.writeAttribute("id", Integer.toString(p.getId()));
                    xmlw.writeStartElement("nome");
                    xmlw.writeCharacters(p.getNome());
                    xmlw.writeEndElement(); //</nome>
                    xmlw.writeStartElement("cognome");
                    xmlw.writeCharacters(p.getCognome());
                    xmlw.writeEndElement(); //</cognome>
                    xmlw.writeStartElement("sesso");
                    xmlw.writeCharacters(p.getSesso());
                    xmlw.writeEndElement();//</sesso>
                    xmlw.writeStartElement("comune_nascita");
                    xmlw.writeCharacters(p.getLuogoNascita());
                    xmlw.writeEndElement();//</luogo_nascita>
                    xmlw.writeStartElement("data_nascita");
                    xmlw.writeCharacters(p.getData().dataToString());
                    xmlw.writeEndElement();//</data_nascita>

                    xmlw.writeStartElement("codice_fiscale");
                    if (p.isMatched()) xmlw.writeCharacters(p.getCodiceFiscale());
                    else xmlw.writeCharacters("ASSENTE");
                    xmlw.writeEndElement(); //</codice_fiscale>
                    xmlw.writeEndElement(); //</persona>
                }
                xmlw.writeEndElement(); // </persone>
                xmlw.writeStartElement("codici");

                xmlw.writeStartElement("invalidi");
                int i=0;
                for (String s: DataProcessing.getCodiciInvalidi())
                {
                    xmlw.writeStartElement("codice");
                    xmlw.writeAttribute("id", Integer.toString(i));
                    xmlw.writeCharacters(s);
                    xmlw.writeEndElement(); //</codice>
                    i++;
                }
                xmlw.writeEndElement(); //</invalidi>
                xmlw.writeStartElement("spaiati");
                i=0;
                for (String s: DataProcessing.getCodiciSpaiati())
                {
                    xmlw.writeStartElement("codice");
                    xmlw.writeAttribute("id", Integer.toString(i));
                    xmlw.writeCharacters(s);
                    xmlw.writeEndElement();//</codice>
                    i++;
                }
                xmlw.writeEndElement();//</spaiati>
                xmlw.writeEndElement();//</codici>
                xmlw.writeEndElement();//</output>
                xmlw.writeEndDocument(); // scrittura della fine del documento
                xmlw.flush(); // svuota il buffer e procede alla scrittura
                xmlw.close(); // chiusura del documento e delle risorse impiegate
        } catch(Exception e){
            System.out.println("Errore nel writer:");
            System.out.println(e.getMessage());
        }
    }
}
