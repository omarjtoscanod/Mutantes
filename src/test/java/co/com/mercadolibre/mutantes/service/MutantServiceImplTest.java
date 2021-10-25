package co.com.mercadolibre.mutantes.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mercadolibre.mutantes.dao.DAOService;
import co.com.mercadolibre.mutantes.exception.DaoException;
import co.com.mercadolibre.mutantes.exception.MutantException;
import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.Subject;

@SpringBootTest
public class MutantServiceImplTest {
	
	private String[] dnaCaracteresIncorrectos = 
			new String[]{"ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA", "TCGCTG"};
	private String[] dnaCaracteresTamano = 
			new String[]{"ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA", "TCGCTG"};
	private String[] dnaMutanteCorrecto = 
			new String[]{"TTTTGA", "ATGTGC", "AGTTGG", "AGATGG", "CCCCTA", "TCGCTG"};
	private String[] dnaHumano = 
			new String[]{"GGGCGA", "CACAGC", "TCAAGG", "CGAAAG", "CCCATA", "TCCCCG"};
	
	@InjectMocks
    private MutantServiceImpl service;
	
	@Mock
    private DAOService daoService;
	
	/**
	 * Simulación de persistencia de adn mutante
	 * @param dna
	 * @return
	 * @throws DaoException
	 */
	private String[] getMutantDNA(String[] dna) throws DaoException	 {

        Subject mutant = new Subject(dna, "mutante");
        Mockito.doNothing().when(daoService).insert(Mockito.eq(mutant));

        return dna;
    }
	
	/**
	 * Simulación de persistencia de adn humano
	 * @param dna
	 * @return
	 * @throws DbException
	 */
	private String[] getHumanDNA(String[] dna) throws DaoException {

        Subject human = new Subject(dna, "human");
        Mockito.doNothing().when(daoService).insert(Mockito.eq(human));

        return dna;
    }
	
	/**
	 * Validación de caracteres permitidos
	 * @throws InputValidationException
	 * @throws ServiceException
	 */
	@Test
    void caracteresPermitidosTest() throws MutantException, ServiceException {
		Assertions.assertThrows(ServiceException.class, () -> {
			service.isMutant(dnaCaracteresIncorrectos);
		  });  
    }
	
	/**
	 * Validación del tamaño del arreglo
	 * @throws MutantException
	 * @throws ServiceException
	 */
	@Test
    void caracteresTamanoTest() throws MutantException, ServiceException {
		Assertions.assertThrows(ServiceException.class, () -> {
			service.isMutant(dnaCaracteresTamano);
		});
    }
	
	/**
	 * Persistencia de ADN Mutante
	 * @throws MutantException
	 * @throws ServiceException
	 * @throws DbException
	 */
	@Test
    void persistenciaMutanteOkTest() throws MutantException, ServiceException, DaoException {

        boolean result = service.isMutant(getMutantDNA(dnaMutanteCorrecto));
        assertTrue(result);
    }
	
	/**
	 * Persistencia de ADN Humano
	 * @throws MutantException
	 * @throws ServiceException
	 * @throws DbException
	 */
	@Test
    void persistenciaHumanoOkTest() throws MutantException, ServiceException, DaoException {

        boolean result = service.isMutant(getHumanDNA(dnaHumano));
        assertTrue(result);
    }

}
