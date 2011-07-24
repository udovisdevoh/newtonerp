package newtonERP.module.exception; 
 // TODO: clean up that file

/**
 * @author cloutierJo exception lancé lorsque le champ ne peut etre trouvé
 */
public class FieldNotFoundException extends ModuleException {
	/**
     * 
     */
    private static final long serialVersionUID = -6144082037269318027L;

	/**
	 * Constructeur par default pour cette exception
	 * 
	 * @param fieldName nom du champ
	 */
	public FieldNotFoundException(String fieldName) {
		super(fieldName + " field doesn't exist");
	}

	/**
	 * 
	 */
	public FieldNotFoundException() {
		super();
	}

	/**
	 * @param fieldName the message
	 * @param cause the cause
	 */
	public FieldNotFoundException(String fieldName, Throwable cause) {
		super(fieldName + " field doesn't exist", cause);
	}

	/**
	 * @param cause the cause
	 */
	public FieldNotFoundException(Throwable cause) {
		super(cause);
	}
}
