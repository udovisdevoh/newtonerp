package modules.taskModule;

import java.util.Vector;

import modules.taskModule.actions.DbGraph;
import modules.taskModule.entityDefinitions.AccessorEntity;
import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EffectEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import modules.taskModule.entityDefinitions.FieldTypeEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.taskModule.entityDefinitions.Parameter;
import modules.taskModule.entityDefinitions.SearchCriteria;
import modules.taskModule.entityDefinitions.SearchCriteriaOperator;
import modules.taskModule.entityDefinitions.SearchEntity;
import modules.taskModule.entityDefinitions.Specification;
import modules.taskModule.entityDefinitions.TaskEntity;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.field.Field;

/**
 * Module des tasks
 * @author Guillaume Lacasse
 */
public class TaskModule extends Module
{
    /**
     * @throws Exception si création fail
     */
    public TaskModule() throws Exception
    {
	super();
	setDefaultAction(new BaseAction("GetList", new TaskEntity()));
	setVisibleName("Système");
	addGlobalActionMenuItem("Modules", new BaseAction("GetList",
		new ModuleEntity()));
	addGlobalActionMenuItem("Tâches", new BaseAction("GetList",
		new TaskEntity()));
	addGlobalActionMenuItem("Spécification", new BaseAction("GetList",
		new Specification()));
	addGlobalActionMenuItem("Effet", new BaseAction("GetList",
		new EffectEntity()));
	addGlobalActionMenuItem("Entités de recherches", new BaseAction(
		"GetList", new SearchEntity()));
	addGlobalActionMenuItem("Critères de recherches", new BaseAction(
		"GetList", new SearchCriteria()));
	addGlobalActionMenuItem("Paramètres", new BaseAction("GetList",
		new Parameter()));
	addGlobalActionMenuItem("Voir le shcéma de DB", new DbGraph());
    }

    public void initDB() throws Exception
    {
	super.initDB();
	initActionsAndEntities();
	initSearchCriteriaOperators();
	// initTasks();
    }

    @SuppressWarnings("unused")
    private static void initTasks() throws Exception
    {
	// NOS TÂCHES AUTOMATISÉES
	initEmployeeNewUserTask();
	initInvoiceLineTask();
	initInvoiceTaxLineTask();
	initNewSupplierTransactionTask();
	intiDynamicFieldTask();
	initWorkOrderTask();
	initWorkOrderClosingTask();
	initShippingTask();
    }

    private static void intiDynamicFieldTask() throws Exception
    {
	EntityEntity fieldEntity = new EntityEntity();
	fieldEntity.setData("systemName", "FieldEntity");
	fieldEntity = (EntityEntity) Orm.selectUnique(fieldEntity);

	SearchCriteriaOperator equals = new SearchCriteriaOperator();
	equals.setData("name", "=");
	equals = (SearchCriteriaOperator) Orm.selectUnique(equals);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour field dynamique écrit");
	searchEntity.assign(fieldEntity);
	searchEntity.newE();

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setData("key", "dynamicField");
	searchCriteria.assign(searchEntity);
	searchCriteria.setData("value", "true");
	searchCriteria.assign(equals);
	searchCriteria.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsqu'un field dynamique est écrit");
	specification.assign(searchEntity);
	specification.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "AddFieldToOrm");
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On ajoute ce field dans l'Orm");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initInvoiceLineTask() throws Exception
    {
	EntityEntity invoiceLineEntity = new EntityEntity();
	invoiceLineEntity.setData("systemName", "InvoiceLine");
	invoiceLineEntity = (EntityEntity) Orm.selectUnique(invoiceLineEntity);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque InvoiceLine");
	searchEntity.assign(invoiceLineEntity);
	searchEntity.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "GetAndCalculateAssociatedInvoice");
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On calcule la facture pour chaque InvoiceLine");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsqu'une invoice line a été écrite");
	specification.assign(searchEntity);
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initShippingTask() throws Exception
    {
	EntityEntity shippingEntity = new EntityEntity();
	shippingEntity.setData("systemName", "Shipping");
	shippingEntity = (EntityEntity) Orm.selectUnique(shippingEntity);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque Shipping");
	searchEntity.assign(shippingEntity);
	searchEntity.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "DispatchShippingActions");
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On dispatch les actions reliées");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsqu'un shipping a été écrit");
	specification.assign(searchEntity);
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initNewSupplierTransactionTask() throws Exception
    {
	// A CHAQUE FACTURE CREE ON FAIT UN NEW SUPPLIER TRANSACTION
	ModuleEntity finances = new ModuleEntity();
	finances.setData("systemName", "Finances");
	finances = (ModuleEntity) Orm.selectUnique(finances);

	EntityEntity invoice = new EntityEntity();
	invoice.setData("systemName", "Invoice");
	invoice = (EntityEntity) Orm.selectUnique(invoice);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque Invoice");
	searchEntity.assign(invoice);
	searchEntity.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "NewSupplierTransaction");
	action.assign(finances);
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On fait une new SupplierTransaction");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsque nouveau Invoice");
	specification.assign(searchEntity);
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initWorkOrderTask() throws Exception
    {
	ModuleEntity mrmngmt = new ModuleEntity();
	mrmngmt.setData("systemName", "MaterialResourcesManagement");
	mrmngmt = (ModuleEntity) Orm.selectUnique(mrmngmt);

	EntityEntity product = new EntityEntity();
	product.setData("systemName", "Product");
	product = (EntityEntity) Orm.selectUnique(product);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque Product");
	searchEntity.assign(product);
	searchEntity.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "CheckForWorkOrders");
	action.assign(mrmngmt);
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On fait CheckForWorkOrders");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsque nouveau produit");
	specification.assign(searchEntity);
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initWorkOrderClosingTask() throws Exception
    {

	ModuleEntity mrmngmt = new ModuleEntity();
	mrmngmt.setData("systemName", "MaterialResourcesManagement");
	mrmngmt = (ModuleEntity) Orm.selectUnique(mrmngmt);

	EntityEntity wo = new EntityEntity();
	wo.setData("systemName", "WorkOrder");
	wo = (EntityEntity) Orm.selectUnique(wo);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Pour chaque WorkOrder");
	searchEntity.assign(wo);
	searchEntity.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "CloseWorkOrder");
	action.assign(mrmngmt);
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet = new EffectEntity();
	effet.setData("name", "On fait CloseWorkOrder");
	effet.assign(searchEntity);
	effet.assign(action);
	effet.newE();

	Specification specification = new Specification();
	specification.setData("name", "Lorsque edit / new WorkOrder");
	specification.assign(searchEntity);
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", true);
	task.setData("straightSearch", true);
	task.assign(effet);
	task.assign(specification);
	task.newE();
    }

    private static void initInvoiceTaxLineTask() throws Exception
    {
	// CALCUL AUTOMATIQUE DE FACTURE A PARTIR D'UN TAXLINE
	EntityEntity invoiceTaxLineEntity = new EntityEntity();
	invoiceTaxLineEntity.setData("systemName", "InvoiceTaxLine");
	invoiceTaxLineEntity = (EntityEntity) Orm
		.selectUnique(invoiceTaxLineEntity);

	SearchEntity searchEntity1 = new SearchEntity();
	searchEntity1.setData("name", "Pour chaque TaxLine");
	searchEntity1.assign(invoiceTaxLineEntity);
	searchEntity1.newE();

	ActionEntity action = new ActionEntity();
	action.setData("systemName", "GetAndCalculateAssociatedInvoiceFromTax");
	action = (ActionEntity) Orm.selectUnique(action);

	EffectEntity effet1 = new EffectEntity();
	effet1.setData("name", "On calcule la facture pour chaque TaxLine");
	effet1.assign(searchEntity1);
	effet1.assign(action);
	effet1.newE();

	Specification specification1 = new Specification();
	specification1.setData("name", "Lorsqu'une tax line a été écrite");
	specification1.assign(searchEntity1);
	specification1.newE();

	TaskEntity task1 = new TaskEntity();
	task1.setData("isActive", true);
	task1.setData("straightSearch", true);
	task1.assign(specification1);
	task1.assign(effet1);
	task1.newE();
    }

    private static void initEmployeeNewUserTask() throws Exception
    {
	// les entitées suivantes sont là juste
	// pour tester
	Parameter parameter = new Parameter();
	parameter.setData("key", "message");
	parameter
		.setData("value",
			"Voici votre nouveau compte utilisateur :Employee.firstName:Employee.lastName");
	// parameter.setData("value", ":Employee.firstName:Employee.lastName");
	parameter.newE();

	SearchCriteriaOperator equals = new SearchCriteriaOperator();
	equals.setData("name", "=");
	equals = (SearchCriteriaOperator) Orm.selectUnique(equals);

	EntityEntity entity = new EntityEntity();
	entity.setData("systemName", "Employee");
	entity = (EntityEntity) Orm.selectUnique(entity);

	SearchEntity searchEntity = new SearchEntity();
	searchEntity.setData("name", "Employe sans login");
	searchEntity.setData(entity.getForeignKeyName(), entity
		.getPrimaryKeyValue());
	searchEntity.newE();

	User user = new User();
	user.setData("name", "unLogedUser");
	user = (User) Orm.selectUnique(user);

	SearchCriteria searchCriteria = new SearchCriteria();
	searchCriteria.setData("key", new User().getForeignKeyName());
	searchCriteria.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	searchCriteria.setData(
		new SearchCriteriaOperator().getForeignKeyName(), equals
			.getPrimaryKeyValue());
	searchCriteria.setData("value", user.getPrimaryKeyValue());
	searchCriteria.newE();

	ActionEntity actionEntity = new ActionEntity();
	actionEntity.setData("systemName", "CreateUserForEmployee");
	actionEntity = (ActionEntity) actionEntity.get().get(0);

	EffectEntity effect = new EffectEntity();
	effect.setData("name", "On cre un login");
	effect.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	// effect.setData(actionEntity.getForeignKeyName(), actionEntity
	// .getPrimaryKeyValue());
	effect.assign(actionEntity);
	effect.newE();

	Specification specification = new Specification();
	specification.setData("name", "Si employe pas encore de login");
	specification.setData(searchEntity.getForeignKeyName(), searchEntity
		.getPrimaryKeyValue());
	specification.newE();

	TaskEntity task = new TaskEntity();
	task.setData("isActive", false);
	task.setData("straightSearch", false);
	task.setData(specification.getForeignKeyName(), specification
		.getPrimaryKeyValue());
	task.setData(effect.getForeignKeyName(), effect.getPrimaryKeyValue());
	task.newE();
    }

    private static void initSearchCriteriaOperators() throws Exception
    {
	// Les entitées suivantes sont là pour avoir des données par default
	// qu'on doit garder

	SearchCriteriaOperator searchCriteriaOperator;

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "!=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", ">");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "<");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", ">=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "<=");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "in");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "is");
	searchCriteriaOperator.newE();

	searchCriteriaOperator = new SearchCriteriaOperator();
	searchCriteriaOperator.setData("name", "like");
	searchCriteriaOperator.newE();
    }

    private static void initActionsAndEntities() throws Exception
    {
	// Les entitées suivantes sont là pour avoir des données par default
	// qu'on doit garder

	String moduleForeignKey = new ModuleEntity().getForeignKeyName();

	ModuleEntity moduleEntity;

	moduleEntity = new ModuleEntity();
	moduleEntity.setData("systemName", "...");
	moduleEntity.setData("visibleName", "...");
	moduleEntity.newE();

	ActionEntity actionEntity;

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Get");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "New");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Edit");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "Delete");
	actionEntity.newE();

	actionEntity = new ActionEntity();
	actionEntity.setData(moduleForeignKey, moduleEntity
		.getPrimaryKeyValue());
	actionEntity.setData("systemName", "GetList");
	actionEntity.newE();

	EntityEntity entityEntity;

	Module module = null;
	Vector<String> allModule;
	allModule = new Vector<String>(ListModule.getAllModules().keySet());
	// on s'assure d'avoir créé le userRightModule en premier
	for (String moduleName : allModule)
	{
	    module = ListModule.getModule(moduleName);

	    moduleEntity = new ModuleEntity();
	    moduleEntity.setData("systemName", moduleName);
	    moduleEntity.setData("visibleName", module.getVisibleName());
	    moduleEntity.newE();

	    for (String actionName : module.getActionList().keySet())
	    {
		actionEntity = new ActionEntity();
		actionEntity.setData(moduleForeignKey, moduleEntity
			.getPrimaryKeyValue());
		actionEntity.setData("systemName", actionName);
		actionEntity.newE();
	    }

	    for (String entityName : module.getEntityDefinitionList().keySet())
	    {
		AbstractOrmEntity realEntity = module.getEntityDefinitionList()
			.get(entityName);

		entityEntity = new EntityEntity();
		entityEntity.setData(moduleForeignKey, moduleEntity
			.getPrimaryKeyValue());
		entityEntity.setData("systemName", entityName);
		entityEntity
			.setData("visibleName", realEntity.getVisibleName());
		entityEntity.newE();

		initFieldEntitiesForEntityEntity(entityEntity, realEntity);
		initAccessorEntityListForEntityEntity(entityEntity, realEntity
			.getAccessorNameList());
	    }
	}
    }

    private static void initAccessorEntityListForEntityEntity(
	    EntityEntity entityEntity, Vector<String> accessorNameList)
	    throws Exception
    {
	for (String accessorName : accessorNameList)
	{
	    AccessorEntity accessorEntity = new AccessorEntity();
	    accessorEntity.assign(entityEntity);
	    accessorEntity.setData("foreignEntityName", accessorName);
	    accessorEntity.newE();
	}
    }

    @SuppressWarnings("unchecked")
    private static void initFieldEntitiesForEntityEntity(
	    EntityEntity entityEntity, AbstractOrmEntity realEntity)
	    throws Exception
    {
	realEntity.initFields();

	for (Field field : realEntity.getFields())
	    initFieldEntity(field, entityEntity);
    }

    @SuppressWarnings("unchecked")
    private static void initFieldEntity(Field field, EntityEntity entityEntity)
	    throws Exception
    {
	FieldTypeEntity fieldType = getOrCreateFieldType(field);
	FieldEntity fieldEntity = new FieldEntity();
	fieldEntity.setData("name", field.getShortName());
	fieldEntity.setData("visibleName", field.getName());
	fieldEntity.setData("readOnly", field.isReadOnly());
	fieldEntity.setData("hidden", field.isHidden());
	fieldEntity.setData("naturalKey", field.isNaturalKey());
	fieldEntity.setData("dynamicField", field.isDynamicField());
	fieldEntity.assign(fieldType);
	fieldEntity.assign(entityEntity);
	fieldEntity.newE();
    }

    @SuppressWarnings("unchecked")
    private static FieldTypeEntity getOrCreateFieldType(Field field)
	    throws Exception
    {
	FieldTypeEntity fieldType = new FieldTypeEntity();
	fieldType.setData("systemName", field.getSystemName());

	Vector<AbstractOrmEntity> result = Orm.select(fieldType);

	if (result.size() > 0)
	    return (FieldTypeEntity) result.get(0);

	fieldType.newE();
	return fieldType;
    }
}
