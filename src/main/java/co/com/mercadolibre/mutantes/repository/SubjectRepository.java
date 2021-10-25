package co.com.mercadolibre.mutantes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutantes.model.Subject;

/**
 * Interfaz que extiende la clase MongoRepository
 * @author omar
 *
 */
public interface SubjectRepository extends MongoRepository<Subject, String>{
	
	/**
	 * Obtiene el número de sujetos por tipo
	 * @param subjectType Tipo del sujeto
	 * @return Número de sujetos
	 */
	Long countBySubjectType(String subjectType);

}
