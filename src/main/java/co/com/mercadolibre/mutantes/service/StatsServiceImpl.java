package co.com.mercadolibre.mutantes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.mutantes.dao.DAOService;
import co.com.mercadolibre.mutantes.exception.DaoException;
import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.Statistics;

/**
 * Implementación de la interfaz StatsService
 * @author omar
 *
 */
@Service
public class StatsServiceImpl implements StatsService {
	
	private static final Logger logger = LoggerFactory.getLogger(StatsServiceImpl.class);
	
	@Autowired
    private DAOService daoService;
	
	@Override
	public Statistics getStats() throws ServiceException {
		Statistics stats = null;
		try {
			stats = new Statistics(daoService.getMutantsCount(), 
					daoService.getHumansCount());
		} catch (DaoException e) {
			logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return stats;
	}

}
