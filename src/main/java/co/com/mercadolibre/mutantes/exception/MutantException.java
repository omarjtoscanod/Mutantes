package co.com.mercadolibre.mutantes.exception;

/**
 * Clase que lanza una excepción de tipo MutantException
 * cuando no se cumplen las validaciones previas al análisis del DNA
 * @author omar
 *
 */
public class MutantException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/** Mensaje de error a mostrar */
	private String message;
	
	
	/**
	 * Constructor de la clase MutantException
	 * @param message Mensaje de error
	 */
	public MutantException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * Obtiene el mensaje de error
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set del mensaje de error
	 * @param message Mensaje de error
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
