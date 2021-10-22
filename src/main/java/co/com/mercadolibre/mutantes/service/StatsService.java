package co.com.mercadolibre.mutantes.service;

import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.Statistics;

/**
 * Interfaz para el servicio estadístico
 * @author omar
 *
 */
public interface StatsService {
	
	/**
	 * Obtiene las estadísticas
	 * @return Estadisticas
	 * @throws ServiceException
	 */
	Statistics getStats() throws ServiceException;
}
