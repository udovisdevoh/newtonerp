package modules.customerVendor.actions;

import java.util.Hashtable;

import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Will be called by tasks. Used when a product quantity gets below the reorder
 * point of that product we just add products to the maxInStock
 * 
 * @author r3hallejo
 */
public class ReorderProducts extends AbstractAction
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ReorderProducts() throws Exception
    {
	super(new Product());
    }

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Product product = (Product) entity;

	product.setData("quantityInStock", product.getData("maxInStock"));

	return null;
    }
}
