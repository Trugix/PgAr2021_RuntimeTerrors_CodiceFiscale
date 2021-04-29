public class Persona {

	private String nome, cognome, luogoNascita, sesso, codiceFiscale;
	private Data data;
	private boolean matched = false;
	private int id;

	public Persona(String nome, String cognome, String luogoNascita, String sesso, Data data, int id)    //costruttore di Persona
	{
		this.nome = nome;
		this.cognome = cognome;
		this.luogoNascita = luogoNascita;
		this.sesso = sesso;
		this.data = data;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public Data getData() {
		return data;
	}

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean b) {
		this.matched = b;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}