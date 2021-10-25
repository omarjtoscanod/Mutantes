package co.com.mercadolibre.mutantes.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa la coleccion subjects
 * @author omar
 *
 */
@Document(collection = "subjects")
public class Subject {
	
	/** Tipo de sujeto */
	public String subjectType;
	
	/** Secuencia de ADN */
    @Indexed(unique = true)
    private String dnaSequence;
    
    /**
     * Constructor de la clase Subject
     * @param dnaSequence Secuencia de ADN
     * @param subjectType Tipo de sujeto
     */
    public Subject(String[] dnaSequence, String subjectType) {
        this.dnaSequence = String.join("", dnaSequence);
        this.subjectType = subjectType;
    }
}
