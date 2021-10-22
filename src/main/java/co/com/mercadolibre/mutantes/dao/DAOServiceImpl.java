package co.com.mercadolibre.mutantes.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.mutantes.exception.DaoException;
import co.com.mercadolibre.mutantes.model.Subject;
import co.com.mercadolibre.mutantes.repository.SubjectRepository;
import co.com.mercadolibre.mutantes.utils.MutantUtils;

@Service
public class DAOServiceImpl implements DAOService {
	
	private static final Logger logger = LoggerFactory.getLogger(DAOServiceImpl.class);
	
	@Autowired
    private SubjectRepository subjectRepository;
	
	/**
	 * Guarda la información del adn del sujeto: Humano o Mutante
	 * @param subject Objecto que guarda el tipo y la secuencia ADN
	 * @throws DaoException
	 */
	@Override
	public void insert(Subject subject) throws DaoException {
		subjectRepository.save(subject);
        logger.info("Se persistio en la BD el siguiente sujeto:" + subject);
	}
	
	/**
	 * Obtiene el número de secuencias de Humanos
	 * @return Número de secuencia de humanos analizadas
	 * @throws DaoException
	 */
	@Override
	public int getHumansCount() throws DaoException {
		return subjectRepository.countBySubjectType(MutantUtils.CONS_SUBJECT_HUMAN).intValue();
	}
	
	/**
	 * Obtiene el número de secuencias de Mutantes
	 * @return Número de secuencia de mutantes analizadas
	 * @throws DaoException
	 */
	@Override
	public int getMutantsCount() throws DaoException {
		return subjectRepository.countBySubjectType(MutantUtils.CONS_SUBJECT_MUTANT).intValue();
	}

}
