package modules.customerVendor.actions;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Cela prend tout les nom des item/service et leur prix
 * 
 * @author gabriel Therrien
 * 
 */
public class GetNamePrice extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// TODO: faire qu'il return un row ittérator d'un facture pis le donne a
	// l'action qui compte le total
	// ((Invoice) entity).getPluralAccessorList().get("item");
	return null;
    }

}
