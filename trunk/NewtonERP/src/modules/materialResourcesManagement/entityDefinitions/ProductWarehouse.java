package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;

/**
 * A warehouse product entity
 * 
 * @author r3hallejo
 */
public class ProductWarehouse extends AbstractOrmEntity
{
    @SuppressWarnings("unused")
    private static Product productDefinition;

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ProductWarehouse() throws Exception
    {
	productDefinition = new Product();
    }

    /**
     * Constructor
     * 
     * @param warehouse the warehouse entity
     * @param product the product entity
     * @throws Exception a general exception
     */
    public ProductWarehouse(Warehouse warehouse, Product product)
	    throws Exception
    {
	getFields().setData(warehouse.getForeignKeyName(),
		warehouse.getPrimaryKeyValue());
	getFields().setData(product.getForeignKeyName(),
		product.getPrimaryKeyValue());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro de warehouse", "warehouseID"));
	fieldsInit.add(new FieldInt("Numéro de produit", "productID"));
	return new Fields(fieldsInit);
    }

}
