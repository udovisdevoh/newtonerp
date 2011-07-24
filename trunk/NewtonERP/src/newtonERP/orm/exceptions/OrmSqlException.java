package newtonERP.orm.exceptions; 
 // TODO: clean up that file

/**
 * Basic exception class representing an sql exception that can be thrown from the orm
 * 
 * @author r3hallejo
 */
public class OrmSqlException extends OrmException {
	/**
     * 
     */
    private static final long serialVersionUID = -8868841083237755991L;

	/**
	 * @param message the message of the exception
	 */
	public OrmSqlException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public OrmSqlException() {
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public OrmSqlException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public OrmSqlException(Throwable cause) {
		super(cause);
	}
}
