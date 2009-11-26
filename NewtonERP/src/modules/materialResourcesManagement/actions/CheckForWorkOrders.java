package modules.materialResourcesManagement.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.WorkOrder;
import modules.materialResourcesManagement.entityDefinitions.WorkOrderStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * Check if a work order is needed for a product and if yes then it creates it
 * 
 * @author r3hallejo
 */
public class CheckForWorkOrders extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CheckForWorkOrders() throws Exception
    {
	super(new Product());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	/*
	 * FIXME: Refaire la calcul du work order. ce n'est pas le bon
	 */
	Product searchProduct = (Product) entity;
	Product product = (Product) Orm.selectUnique(searchProduct);

	if (product.getData("isProduced").equals(true))
	{
	    if ((Integer) product.getData("quantityInStock") < (Integer) product
		    .getData("reorderPoint"))
	    {
		WorkOrder wo = new WorkOrder();
		wo.setData(new Product().getForeignKeyName(), product
			.getPrimaryKeyValue());
		wo.setData("quantity", (Integer) product.getData("maxInStock")
			- (Integer) product.getData("quantityInStock"));
		wo.setData("creationDate", new GregorianCalendar());

		WorkOrderStatus wos = new WorkOrderStatus();
		wos.setData("status", "Nouveau");

		WorkOrderStatus usedWos = (WorkOrderStatus) Orm
			.selectUnique(wos);

		wo.setData(new WorkOrderStatus().getForeignKeyName(), usedWos
			.getPrimaryKeyValue());
		wo.newE();
	    }
	}

	return null;
    }
}
