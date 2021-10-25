package co.com.mercadolibre.mutantes.exception;


/**
 * Clase que lanza una excepción de tipo ServiceException
 * cuando ocurre un error en las clases Service
 * @author omar
 *
 */

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/** Mensaje de error a mostrar */
	private String message;
	
	/**
	 * constructor de la clase ServiceException
	 * @param message Mensaje de error
	 */
	public ServiceException(String message) {
		super();
		this.message = message;
	}

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
