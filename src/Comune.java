public class Comune {           // credo sia abbastanza self-explaining
	final String nome;
	final String codice;

	public Comune(String nome, String codice) {
		this.nome = nome;
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}
}
