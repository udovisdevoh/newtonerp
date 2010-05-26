package modules.materialResourcesManagement.actions;

import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Product;
import modules.materialResourcesManagement.entityDefinitions.WorkOrder;
import modules.materialResourcesManagement.entityDefinitions.WorkOrderStatus;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action to close a work order
 * 
 * @author r3hallejo
 */
public class CloseWorkOrder extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public CloseWorkOrder() throws Exception
    {
	super(new WorkOrder());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// Le woStatus en fermeture
	WorkOrderStatus workOrderStatus = new WorkOrderStatus();
	workOrderStatus.setData("status", "En fermeture");
	WorkOrderStatus retWoStatus = (WorkOrderStatus) Orm
		.selectUnique(workOrderStatus);

	// Le woStatus fermée
	WorkOrderStatus workOrderStatus1 = new WorkOrderStatus();
	workOrderStatus1.setData("status", "Fermée");
	WorkOrderStatus retWoStatusClosed = (WorkOrderStatus) Orm
		.selectUnique(workOrderStatus1);

	// Le work order
	WorkOrder searchWo = new WorkOrder();
	searchWo.setData(searchWo.getPrimaryKeyName(),
		((AbstractOrmEntity) entity).getPrimaryKeyValue());
	WorkOrder retWo = (WorkOrder) Orm.selectUnique(searchWo);

	// Donc si le work order est en fermeture
	if (retWo.getData(new WorkOrderStatus().getForeignKeyName()).equals(
		retWoStatus.getPrimaryKeyValue()))
	{
	    retWo.setData(new WorkOrderStatus().getForeignKeyName(),
		    retWoStatusClosed.getPrimaryKeyValue());

	    Product searchProduct = new Product();
	    searchProduct.setData(searchProduct.getPrimaryKeyName(), retWo
		    .getData(new Product().getForeignKeyName()));
	    Product retProduct = (Product) Orm.selectUnique(searchProduct);

	    retProduct.setData("quantityInStock", (Integer) retProduct
		    .getData("quantityInStock")
		    + (Integer) retWo.getData("quantity"));

	    retProduct.save();
	    retWo.save();
	}

	return null;
    }
}
