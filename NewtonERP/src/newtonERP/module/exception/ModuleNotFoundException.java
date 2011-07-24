package newtonERP.module.exception; 
 // TODO: clean up that file

/**
 * @author cloutierJo exception lancé lorsque le module ne peut etre trouvé
 */
public class ModuleNotFoundException extends ModuleException {
	/**
     * 
     */
    private static final long serialVersionUID = -8744376040273790389L;

	/**
	 * Constructeur par default pour cette exception
	 * 
	 * @param moduleName nom du module
	 */
	public ModuleNotFoundException(String moduleName) {
		super(moduleName + " module doesn't exist");
	}

	/**
	 * 
	 */
	public ModuleNotFoundException() {
		super();
	}

	/**
	 * @param moduleName the module name
	 * @param cause the cause
	 */
	public ModuleNotFoundException(String moduleName, Throwable cause) {
		super(moduleName + " module doesn't exist", cause);
	}

	/**
	 * @param cause the cause
	 */
	public ModuleNotFoundException(Throwable cause) {
		super(cause);
	}
}
