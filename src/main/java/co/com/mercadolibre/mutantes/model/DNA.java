package co.com.mercadolibre.mutantes.model;

/**
 * Clase que describe el DNA de un mutante o humano
 * @author omar
 *
 */
public class DNA {
	
	private String[] dna;
	
	/**
	 * Obtiene el DNA Humano o mutante
	 * @return Arreglo con el ADN humano o mutante
	 */
	public String[] getDna() {
		return dna;
	}
	
	/**
	 * Set de DNA humano o mutante
	 * @param dna Arreglo con el ADN humano o mutante
	 */
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	
}
