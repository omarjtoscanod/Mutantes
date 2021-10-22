package co.com.mercadolibre.mutantes.exception;

/**
 * Clase que lanza una excepción de tipo MutantException
 * cuando no se cumplen las validaciones previas al análisis del DNA
 * @author omar
 *
 */
public class MutantException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	

	public MutantException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
