public class Data {
	private int anno;
	private int mese;
	private int giorno;

	public Data(int anno, int mese, int giorno) {
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	/**
	 * Converte una data in una stringa
	 * @return
	 */
	public String dataToString() {
		return this.anno + "-" + this.mese + "-" + this.giorno;
	}

}
