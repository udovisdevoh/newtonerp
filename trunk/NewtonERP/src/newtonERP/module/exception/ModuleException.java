package newtonERP.module.exception; 
 // TODO: clean up that file

/**
 * @author r3hallejo Exception class representing a module exception
 */
public class ModuleException extends RuntimeException {
	/**
     * 
     */
    private static final long serialVersionUID = -5179180833569225276L;

	/**
	 * Default constructor for the module exception
	 * 
	 * @param message the message
	 */
	public ModuleException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public ModuleException() {
		super();
	}

	/**
	 * @param message the message
	 * @param cause the cause
	 */
	public ModuleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause the cause
	 */
	public ModuleException(Throwable cause) {
		super(cause);
	}
}
