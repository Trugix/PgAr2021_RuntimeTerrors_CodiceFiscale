import it.unibs.fp.mylib.*;

public class Menu {
	private final static String BENVENUTO = "\nBenvenuto!\n\nProgramma di Edoardo Cesari, Andrea Carrara e Ruggero Signoroni\n";
	private final static String SCELTA_FILE = "Vuoi utilizzare i file di input predefiniti?: ";
	private final static String SCELTA_PERSONE = "Inserire 1 per cambiare il file delle persone.";
	private final static String SCELTA_CODICI = "Inserire 2 per cambiare il file dei codici.";
	private final static String SCELTA_ESCI = "Inserire 3 per confermare e continuare il programma.";
	private final static String INSERIMENTO = "Inserire il nome del nuovo file (Ricordarsi di mettere .xml): ";
	private final static String SCELTA_INSERIMENTO = "Inserire la scelta: ";
	private final static String RUNTIME_TERRORS = "Errore, codice non riconosciuto.";


	public static void stampaMenu() {
		boolean leave = false;
		System.out.println(BENVENUTO);
		if (!(InputDati.yesOrNo(SCELTA_FILE))) {
			System.out.println("");
			do {
				System.out.println(SCELTA_PERSONE);
				System.out.println(SCELTA_CODICI);
				System.out.println(SCELTA_ESCI);

				switch (InputDati.leggiInteroPositivo(SCELTA_INSERIMENTO)) {
					case 1:
						Reader.setFilenamePers(InputDati.leggiStringaNonVuota(INSERIMENTO));
						Utility.clearScreen();
						break;
					case 2:
						Reader.setFilenameCod(InputDati.leggiStringaNonVuota(INSERIMENTO));
						Utility.clearScreen();
						break;
					case 3:
						leave = true;
						break;
					default:
						System.out.println(RUNTIME_TERRORS);
						Utility.clearScreen();
				}
			}
			while (!leave);
		}
		System.out.println("");
		Reader.readCodiciFiscali(); // Chiamo il metodo che carica in memoria i codici fiscali
		Reader.readComuni(); // Chiamo il metodo che carica in memoria i comuni
		Reader.readInputPersone(); //Chiamo il metodo che legge tutto il file delle persone e le cariche in memoria
		DataProcessing.codeSorting(); // Chiamo il metodo che smista i vari array presenti in dataProcessing
		Writer.writeOutput(); // Chiamo il metodo che genera l'XML di output
	}
}