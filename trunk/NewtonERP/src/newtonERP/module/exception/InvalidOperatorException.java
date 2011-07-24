package newtonERP.module.exception; 
 // TODO: clean up that file

/**
 * @author r3hallejo When an operator for a field is not valid
 */
public class InvalidOperatorException extends ModuleException {
	/**
     * 
     */
    private static final long serialVersionUID = 7735164355767841316L;

	/**
	 * Default constructor
	 * 
	 * @param message the message
	 */
	public InvalidOperatorException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public InvalidOperatorException() {
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public InvalidOperatorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public InvalidOperatorException(Throwable cause) {
		super(cause);
	}
}
