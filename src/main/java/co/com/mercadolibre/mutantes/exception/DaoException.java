package co.com.mercadolibre.mutantes.exception;

/**
* Clase que lanza una excepción de tipo DaoException
* cuando ocurre un error en la base de datos
* @author omar
*
*/
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Mensaje de error a mostrar */
	private String message;
	
	/**
	 * Constructor de la clase DaoException
	 * @param message Mensaje de error
	 */
	public DaoException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * Set de mensaje de error
	 */
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
