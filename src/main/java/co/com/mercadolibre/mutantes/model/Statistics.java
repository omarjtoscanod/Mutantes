package co.com.mercadolibre.mutantes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase que realiza el análisis estadístico
 * @author omar
 *
 */
public class Statistics {
	
	private BigDecimal countMutantDna;
	
	private BigDecimal countHumanDna;
	
	private BigDecimal ratio;

	public Statistics(Integer countMutantDna, Integer countHumanDna) {
		super();
		this.countMutantDna = new BigDecimal(countMutantDna);
		this.countHumanDna = new BigDecimal(countHumanDna);
		ratio = (this.countHumanDna.compareTo(BigDecimal.ZERO) != 0) ? 
				this.countMutantDna.divide(this.countHumanDna, 2, RoundingMode.UNNECESSARY) 
				: new BigDecimal("0.00");
	}


	public BigDecimal getCountMutantDna() {
		return countMutantDna;
	}



	public void setCountMutantDna(BigDecimal countMutantDna) {
		this.countMutantDna = countMutantDna;
	}



	public BigDecimal getCountHumanDna() {
		return countHumanDna;
	}



	public void setCountHumanDna(BigDecimal countHumanDna) {
		this.countHumanDna = countHumanDna;
	}



	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
}
