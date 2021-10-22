package co.com.mercadolibre.mutantes.exception;


/**
 * Clase que lanza una excepción de tipo ServiceException
 * cuando ocurre un error en las clases Service
 * @author omar
 *
 */

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ServiceException(String message) {
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
