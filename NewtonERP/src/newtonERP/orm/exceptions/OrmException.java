package newtonERP.orm.exceptions; 
 // TODO: clean up that file

/**
 * OrmException super class for all the other classes
 * 
 * @author r3lacasgu, r3hallejo
 */
public class OrmException extends RuntimeException {
	/**
     * 
     */
    private static final long serialVersionUID = 2631213877222753163L;

	/**
	 * @param message the message of the exception
	 */
	public OrmException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public OrmException() {
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public OrmException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public OrmException(Throwable cause) {
		super(cause);
	}

}
