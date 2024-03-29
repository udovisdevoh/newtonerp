package newtonERP.module.exception; 
 // TODO: clean up that file

/**
 * @author cloutierJo exception lancé lorsque le module ne peut etre trouvé
 */
public class ActionNotFoundException extends ModuleException {
	/**
     * 
     */
    private static final long serialVersionUID = 4934815493311711418L;

	/**
	 * Constructeur par default pour cette exception
	 * 
	 * @param actionName nom de l'action non trouvé
	 * @param moduleName nom du module
	 */
	public ActionNotFoundException(String actionName) {
		super(actionName + " action doesn't exist");
	}

	/**
	 * 
	 */
	public ActionNotFoundException() {
		super();
	}

	/**
	 * @param actionName the message
	 * @param cause the cause
	 */
	public ActionNotFoundException(String actionName, Throwable cause) {
		super(actionName + " action doesn't exist", cause);
	}

	/**
	 * @param cause the cause
	 */
	public ActionNotFoundException(Throwable cause) {
		super(cause);
	}
}
