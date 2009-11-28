package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.field.Field;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Ajoute un field à l'Orm
 * @author Guillaume
 */
public class AddFieldToOrm extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public AddFieldToOrm() throws Exception
    {
	super(new FieldEntity());
    }

    @Override
    public BaseViewerData doAction(AbstractEntity sourceFieldEntity,
	    Hashtable<String, String> parameters) throws Exception
    {
	FieldEntity fieldEntity = (FieldEntity) Orm
		.selectUnique((AbstractOrmEntity) sourceFieldEntity);

	EntityEntity entityEntity = (EntityEntity) fieldEntity
		.getSingleAccessor(new EntityEntity().getForeignKeyName());

	AbstractOrmEntity entity = entityEntity.getEntityDefinition();
	Field<?> field = fieldEntity.getFieldInstance();

	BaseViewerData editUI = fieldEntity.editUI(parameters);

	try
	{
	    Orm.addColumnToTable(entity, field);
	    editUI
		    .addNormalMessage("Le champ a été inséré dans la base de donnée");
	} catch (OrmException e)
	{
	    editUI.addNormalMessage("Champ déjà dans la base de donnée");
	}

	return editUI;
    }
}