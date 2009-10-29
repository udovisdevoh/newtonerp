/**
 * 
 */
package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.customerVendor.entityDefinitions.Customer;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * @author Gabriel
 * 
 */
public class GetCustumerEntity extends AbstractAction
{

    /**
     * constructeur par default
     */
    public GetCustumerEntity()
    {
	super(new Customer());
    }

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// TODO Auto-generated method stub
	Customer lookingForCustomer = new Customer();
	lookingForCustomer.setData(lookingForCustomer.getPrimaryKeyName(),
		((AbstractOrmEntity) entity).getPrimaryKeyValue());
	Orm.select(lookingForCustomer).get(0);// il peux seulement en avoir 1;
	return null;
    }

}
