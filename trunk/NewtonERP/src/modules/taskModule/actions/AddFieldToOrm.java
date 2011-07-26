package modules.taskModule.actions;

// TODO: clean up that file

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.fields.field.DynamicFieldCache;
import newtonERP.orm.fields.field.Field;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Ajoute un field à l'Orm
 * 
 * @author Guillaume
 */
public class AddFieldToOrm extends AbstractAction {
	/**
	 */
	public AddFieldToOrm() {
		super(new FieldEntity());
	}

	@Override
	public BaseViewerData doAction(AbstractEntity sourceFieldEntity, Hashtable<String, String> parameters) {
		FieldEntity fieldEntity = (FieldEntity) Orm.getInstance().selectUnique((AbstractOrmEntity) sourceFieldEntity);

		EntityEntity entityEntity = (EntityEntity) fieldEntity
		        .getSingleAccessor(new EntityEntity().getForeignKeyName());

		Field field = fieldEntity.getFieldInstance();
		BaseViewerData editUI = fieldEntity.editUI(parameters);

		try{
			AbstractOrmEntity entity = entityEntity.getEntityDefinition();

			try{
				Orm.getInstance().addColumnToTable(entity, field);
				Orm.getInstance().createIndex(entity, field);
				editUI.addNormalMessage("Le champ a été inséré dans la base de donnée");
			}catch(OrmException e){
				editUI.addNormalMessage("Champ déjà dans la base de donnée");
			}finally{
				DynamicFieldCache.clear(entity.getSystemName());
			}

		}catch(Exception e){
			editUI.addNormalMessage("Impossible de trouver l'entité dans la base de donnée. Si c'est une nouvelle entité pas encore générée, ce message est normal.");
		}

		return editUI;
	}
}
