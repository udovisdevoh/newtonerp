package newtonERP.module.field;

import newtonERP.module.ModuleException;

/**
 * @author cloutierJo
 * 
 *         exception lancé lorsque le champ ne peut etre trouvé
 */
public class FieldNotFoundException extends ModuleException
{
    /**
     * Constructeur par default pour cette exception
     * 
     * @param fieldName nom du champ
     */
    public FieldNotFoundException(String fieldName)
    {
	super(fieldName + " field doesn't exist");
    }
}
