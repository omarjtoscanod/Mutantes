package co.com.mercadolibre.mutantes.exception;

/**
* Clase que lanza una excepción de tipo DaoException
* cuando ocurre un error en la base de datos
* @author omar
*
*/
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public DaoException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
