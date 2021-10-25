package co.com.mercadolibre.mutantes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase que realiza el análisis estadístico
 * @author omar
 *
 */
public class Statistics {
	
	private Long countMutantDna;
	
	private Long countHumanDna;
	
	private Long ratio;
	
	/**
	 * Constructor de la clase Statistics
	 * @param countMutantDna Número de ADN mutante
	 * @param countHumanDna Número de ADN humano
	 */
	public Statistics(Integer countMutantDna, Integer countHumanDna) {
		super();
		this.countMutantDna = Long.valueOf(countMutantDna);
		this.countHumanDna = Long.valueOf(countHumanDna);
		ratio = (this.countHumanDna.compareTo(0L) != 0) ? 
				this.countMutantDna / (this.countHumanDna) 
				: 0L;
	}
	
	/**
	 * Obtiene el número de ADN Mutante
	 * @return Número de ADN mutante
	 */
	public Long getCountMutantDna() {
		return countMutantDna;
	}
	
	/**
	 * Set de número de ADN Mutante
	 * @param countMutantDna Número de ADN mutante
	 */
	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	
	/**
	 * Obtiene el número de ADN humano
	 * @return Número de ADN Humano
	 */
	public Long getCountHumanDna() {
		return countHumanDna;
	}
	
	/**
	 * Set de número de ADN humano
	 * @param countHumanDna Número de ADN humano
	 */
	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	
	/**
	 * Obtiene el ratio
	 * @return ratio
	 */
	public Long getRatio() {
		return ratio;
	}
	
	/**
	 * Set de ratio
	 * @param ratio ratio
	 */
	public void setRatio(Long ratio) {
		this.ratio = ratio;
	}


		
}
