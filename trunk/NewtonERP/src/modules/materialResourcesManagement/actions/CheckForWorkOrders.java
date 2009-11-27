package modules.materialResourcesManagement.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.WorkOrder;
import modules.materialResourcesManagement.entityDefinitions.WorkOrderStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
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
	// The product
	Product searchProduct = (Product) entity;
	Product product = (Product) Orm.selectUnique(searchProduct);

	// The Nouveau work order status
	WorkOrderStatus wos = new WorkOrderStatus();
	wos.setData("status", "Nouveau");
	WorkOrderStatus usedWos = (WorkOrderStatus) Orm.selectUnique(wos);

	// The search work order
	WorkOrder searchWo = new WorkOrder();
	searchWo.setData(new Product().getForeignKeyName(), product
		.getPrimaryKeyValue());
	searchWo.setData(new WorkOrderStatus().getForeignKeyName(), usedWos
		.getPrimaryKeyValue());

	if (Orm.select(searchWo).size() == 0)
	{
	    if (product.getData("isProduced").equals(true))
	    {
		if (((Integer) product.getData("quantityInStock") - (Integer) product
			.getData("reorderPoint")) <= 0)
		{
		    WorkOrder wo = new WorkOrder();
		    wo.setData(new Product().getForeignKeyName(), product
			    .getPrimaryKeyValue());
		    wo.setData("quantity", (Integer) product
			    .getData("maxInStock")
			    - (Integer) product.getData("quantityInStock"));
		    wo.setData("creationDate", new GregorianCalendar());
		    wo.setData(new WorkOrderStatus().getForeignKeyName(),
			    usedWos.getPrimaryKeyValue());
		    wo.newE();
		}
	    }
	}
	else
	{
	    int inProduction = 0;
	    Vector<AbstractOrmEntity> workOrders = Orm.select(searchWo);

	    for (AbstractOrmEntity workOrder : workOrders)
	    {
		inProduction += (Integer) workOrder.getData("quantity");
	    }

	    int toProduce = (Integer) product.getData("reorderPoint")
		    - (inProduction + (Integer) product
			    .getData("quantityInStock"));

	    if (toProduce > 0)
	    {
		WorkOrder wo = new WorkOrder();
		wo.setData(new Product().getForeignKeyName(), product
			.getPrimaryKeyValue());
		wo.setData("quantity", toProduce);
		wo.setData("creationDate", new GregorianCalendar());
		wo.setData(new WorkOrderStatus().getForeignKeyName(), usedWos
			.getPrimaryKeyValue());
		wo.newE();
	    }
	}
	return null;
    }
}
