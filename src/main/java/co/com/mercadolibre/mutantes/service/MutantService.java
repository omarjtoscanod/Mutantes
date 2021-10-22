package co.com.mercadolibre.mutantes.service;

import co.com.mercadolibre.mutantes.exception.ServiceException;

/**
 * Clase que informa si el DNA analizado es mutante o humano
 * @author omar
 *
 */
public interface MutantService {
	
	/**
	 * Método que se encarga de validar si un ADN es
	 * humano o mutante
	 * @param dna Arreglo con la secuencia ADN
	 * @return true si es mutante, false si es humano
	 * @throws ServiceException
	 */
	boolean isMutant(String[] dna) throws ServiceException;
}
