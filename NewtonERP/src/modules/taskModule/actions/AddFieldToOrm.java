package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Ajoute un field à l'Orm
 * @author Guillaume
 */
public class AddFieldToOrm extends AbstractAction
{

    @Override
    public BaseViewerData doAction(AbstractEntity sourceFieldEntity,
	    Hashtable<String, String> parameters) throws Exception
    {
	FieldEntity fieldEntity = (FieldEntity) Orm
		.selectUnique((AbstractOrmEntity) sourceFieldEntity);

	EntityEntity entityEntity = (EntityEntity) fieldEntity
		.getSingleAccessor("EntityEntity");

	Orm.addColumnToTable(entityEntity.getEntityDefinition(), fieldEntity
		.getFieldInstance());

	return fieldEntity.editUI(parameters);
    }
}
