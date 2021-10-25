package co.com.mercadolibre.mutantes.dao;

import co.com.mercadolibre.mutantes.exception.DaoException;
import co.com.mercadolibre.mutantes.model.Subject;

/**
 * Clase que maneja la interacción con la base de datos
 * @author omar
 *
 */
public interface DAOService {
	
	/**
	 * Guarda la información del adn del sujeto: Humano o Mutante
	 * @param subject Objecto que guarda el tipo y la secuencia ADN
	 * @throws DaoException Excepción de base de datos
	 */
	void insert(Subject subject) throws DaoException;
	
	/**
	 * Obtiene el número de secuencias de Humanos
	 * @return Número de secuencia de humanos analizadas
	 * @throws DaoException Excepción de base de datos
	 */
	int getHumansCount() throws DaoException;
	
	/**
	 * Obtiene el número de secuencias de Mutantes
	 * @return Número de secuencia de mutantes analizadas
	 * @throws DaoException Excepción de base de datos
	 */
	int getMutantsCount() throws DaoException;
	
	
}
