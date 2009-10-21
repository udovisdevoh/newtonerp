package newtonERP.module.exception;


/**
 * @author cloutierJo
 * 
 *         exception lancé lorsque le type du champ ne correspond pas
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
	super(fieldName + " field is not compatible with "
		+ data.getClass().getSimpleName());
    }
}
