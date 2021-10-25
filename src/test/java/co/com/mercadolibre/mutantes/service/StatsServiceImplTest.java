package co.com.mercadolibre.mutantes.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mercadolibre.mutantes.dao.DAOService;
import co.com.mercadolibre.mutantes.exception.DaoException;
import co.com.mercadolibre.mutantes.exception.ServiceException;

@SpringBootTest
public class StatsServiceImplTest {
	
	@InjectMocks
    private StatsServiceImpl statsService;
	
	@Mock
    private DAOService daoService;
	
	/**
	 * Validación estadísticas
	 * @throws DbException
	 * @throws ServiceException
	 */
	@Test
    public void testStatsOk() throws DaoException, ServiceException {

        Mockito.when(daoService.getMutantsCount()).thenReturn(1);
        
        Mockito.when(daoService.getHumansCount()).thenReturn(10);

        
        assertNotNull(statsService.getStats());
    }
}
