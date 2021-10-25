package co.com.mercadolibre.mutantes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.Statistics;
import co.com.mercadolibre.mutantes.service.StatsService;

/**
 * Clase controlador de servicio stats
 * @author omar
 *
 */
@RestController
public class StatsController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatsController.class);
	
	@Autowired
    private StatsService statssService;
	
	/**
	 * Obtiene las estadísticas del servicio
	 * @return Estadísticas
	 */
	@GetMapping(value = "/stats", produces = "application/json; charset=UTF-8")
    public Statistics getStatics() {

        Statistics stats = null;

        try {
            stats = statssService.getStats();
            logger.info("La cantidad de Humanos es: {}, la de Mutantes es: {} y el Ratio es de: {}  ", 
            		statssService.getStats().getCountHumanDna(), 
            		statssService.getStats().getCountMutantDna(), 
            		statssService.getStats().getRatio());
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        return stats;
    }
}
