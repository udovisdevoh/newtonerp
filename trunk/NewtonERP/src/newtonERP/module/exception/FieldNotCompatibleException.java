package newtonERP.module.exception;

/**
 * exception lancé lorsque le type du champ ne correspond pas
 * 
 * @author cloutierJo
 */
public class FieldNotCompatibleException extends ModuleException
{
    /**
     * Constructeur par default pour cette exception
     * 
     * @param fieldName nom du champ
     * @param data la donne nom compatible
     */
    public FieldNotCompatibleException(String fieldName, Object data)
    {
	super(
		fieldName + " field is not compatible with " + data != null ? data
			.getClass().getSimpleName()
			: "null");
    }

    /**
     * @param message the message
     */
    public FieldNotCompatibleException(String message)
    {
	super(message);
    }
}
