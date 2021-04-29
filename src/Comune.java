public class Comune {
        private String nome;
        private String codice;

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
