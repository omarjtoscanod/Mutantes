package co.com.mercadolibre.mutantes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.mutantes.dao.DAOService;
import co.com.mercadolibre.mutantes.exception.ServiceException;
import co.com.mercadolibre.mutantes.model.Subject;
import co.com.mercadolibre.mutantes.utils.MutantUtils;

/**
 * Clase que implementa la interface MutantService
 * Posee un método isMutant que indica si el DNA es
 * mutante o humano
 * @author omar
 *
 */
@Service
public class MutantServiceImpl implements MutantService {
	
	private static final Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
	
	@Autowired
    private DAOService daoService;
	
	/**
	 * Método que se encarga de validar si un ADN es
	 * humano o mutante
	 * @param dna Arreglo con la secuencia ADN
	 * @return true si es mutante, false si es humano
	 * @throws ServiceException
	 */
	@Override
	public boolean isMutant(String[] dna) throws ServiceException {
		boolean isMutant = false;
		char[][] dnaSequence = null;
		try {
			dnaSequence = MutantUtils.validarADNIngresado(dna);
			isMutant = MutantUtils.analizarSecuenciaADN(dnaSequence);
			if (isMutant) {
                Subject mutant = new Subject(dna, MutantUtils.CONS_SUBJECT_MUTANT);
                daoService.insert(mutant);
            } else {
                Subject human = new Subject(dna, MutantUtils.CONS_SUBJECT_HUMAN);
                daoService.insert(human);
            }
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return isMutant;
	}

}
