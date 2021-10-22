package co.com.mercadolibre.mutantes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutantes.model.Subject;

public interface SubjectRepository extends MongoRepository<Subject, String>{
	
	Long countBySubjectType(String subjectType);

}
