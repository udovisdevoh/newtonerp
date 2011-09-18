package newtonERP.module;

// TODO: clean up that file

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import newtonERP.module.exception.EntityException;
import newtonERP.orm.Orm;
import newtonERP.orm.entity.OrmEntity;
import newtonERP.orm.entity.RelationEntity;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.VolatileFields;
import newtonERP.orm.fields.field.DynamicFieldCache;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.relation.AbstractRelation;

/**
 * @author r3lemaypa, r3lacasgu, Jonatan Cloutier
 */
public abstract class AbstractEntity implements RelationEntity {
	protected Fields fields;
	protected Module currentModule;
	private AbstractAction currentAction;
	private String detailedDescription = null;
	private HashMap<Class<? extends RelationEntity>, AbstractRelation> relationMap;

	/**
	 * construit une entity ne comportant aucun champ
	 */
	public AbstractEntity() {
		fields = preInitFields();
	}

	protected Fields preInitFields() {
		Vector<Field> fieldsData = new Vector<Field>();
		Fields originalField = initFields();

		fieldsData.addAll(originalField.getFields());

		try{
			Vector<AbstractOrmEntity> dataField = DynamicFieldCache.tryGetDataForEntity(getSystemName());

			if(dataField == null){
				EntityEntity entitySearch = new EntityEntity();
				entitySearch.setData("systemName", getSystemName());
				entitySearch = (EntityEntity) entitySearch.get().get(0);
				FieldEntity search = new FieldEntity();
				search.setData(entitySearch.getForeignKeyName(), entitySearch.getPrimaryKeyValue());
				search.setData("dynamicField", true);
				dataField = search.get();
				DynamicFieldCache.add(dataField, getSystemName());
			}

			fieldsData.addAll(initFieldsFromDb(dataField));
		}catch(Exception e){
			// ne rien faire ici, n'arrive que dans le cas du premier build de
			// la DB et c'Est normale ou d'un entity nonOrmilizer
		}

		if(originalField instanceof VolatileFields){
			return new VolatileFields(fieldsData);
		}
		return new Fields(fieldsData);

	}

	/**
	 * initialise les champ de l'entity, doit etre overider si l'entity contient des champs, sinon initialise une liste
	 * de champ vide
	 * 
	 * @return le Fields initialiser
	 */
	public Fields initFields() {
		return new Fields();
	}

	private Vector<Field> initFieldsFromDb(Vector<AbstractOrmEntity> dataField) {
		FieldEntity field;
		Vector<Field> fieldsData = new Vector<Field>();

		for(AbstractOrmEntity abstractField : dataField){
			field = (FieldEntity) abstractField;
			fieldsData.add(field.getFieldInstance());
		}

		return fieldsData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentAction == null) ? 0 : currentAction.hashCode());
		result = prime * result + ((currentModule == null) ? 0 : currentModule.hashCode());
		result = prime * result + ((detailedDescription == null) ? 0 : detailedDescription.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(getClass() != obj.getClass()){
			return false;
		}
		AbstractEntity other = (AbstractEntity) obj;
		if(currentAction == null){
			if(other.currentAction != null){
				return false;
			}
		}else if(!currentAction.equals(other.currentAction)){
			return false;
		}
		if(currentModule == null){
			if(other.currentModule != null){
				return false;
			}
		}else if(!currentModule.equals(other.currentModule)){
			return false;
		}
		if(detailedDescription == null){
			if(other.detailedDescription != null){
				return false;
			}
		}else if(!detailedDescription.equals(other.detailedDescription)){
			return false;
		}
		if(fields == null){
			if(other.fields != null){
				return false;
			}
		}else if(!fields.equals(other.fields)){
			return false;
		}
		return true;
	}

	@SuppressWarnings("unused")
	private void setFields(Fields fields) {
		// must not be implemented
	}

	/**
	 * @return the fields
	 */
	public Fields getFields() {
		return fields;
	}

	@Override
	public String toString() {
		return fields.toString();
	}

	/**
	 * methode d'Accces rapide au donnees sous forme de string
	 * 
	 * @param shortName le nom du champ voulu
	 * @return la valeur sous forme de string
	 */
	public String getDataString(String shortName) {
		return getFields().getField(shortName).getDataString();
	}

	/**
	 * @param shortName le nom du champ voulu
	 * @return the data
	 */
	public Object getData(String shortName) {
		return getFields().getField(shortName).getData();
	}

	/**
	 * @param shortName le nom du champ voulu
	 * @param data the data to set
	 */
	@Override
	public void setData(String shortName, Object data) {
		getFields().setData(shortName, data);
	}

	/**
	 * @return action courante
	 */
	public AbstractAction getCurrentAction() {
		return currentAction;
	}

	/**
	 * @return module courant
	 */
	public Module getCurrentModule() {
		if(currentModule == null){
			throw new EntityException("Vous devez setter le module courrant dans le viewer avec setCurrentModule()");
		}

		return currentModule;
	}

	/**
	 * @param currentModule Défini le module utilisé en ce moment pour cette entité
	 */
	public final void setCurrentModule(Module currentModule) {
		this.currentModule = currentModule;
	}

	/**
	 * @param currentAction Action qui sera utilisée
	 */
	public final void setCurrentAction(AbstractAction currentAction) {
		this.currentAction = currentAction;
	}

	/**
	 * @return nom de l'entité dans le système (normalement nom de la classe mais peut être overridé si c'est une entité
	 *         dynamique)
	 */
	public String getSystemName() {
		return getClass().getSimpleName();
	}

	/**
	 * @param inputName nom d'un field
	 * @return nom complete d'un field
	 */
	public String getLabelName(String inputName) {
		return getFields().getField(inputName).getName();
	}

	/**
	 * remet l'etat de l'entity a ses valeur initial
	 */
	public void reset() {
		fields.reset();
	}

	/**
	 * @return description détaillée facultative
	 */
	public String getDetailedDescription() {
		return detailedDescription;
	}

	/**
	 * @param detailedDescription description détaillée facultative
	 */
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Override
	public int getId() {
		return (Integer) getData("id");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends RelationEntity> get() {
		// TODO: replace by query ASAP
		Vector<OrmEntity> entities = new Vector<OrmEntity>();
		entities.add(this);
		return Orm.getInstance().select(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractRelation getRelation(Class<? extends RelationEntity> relatedEntityClass) {
		return relationMap.get(relatedEntityClass);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNewRelation(AbstractRelation newRelation) {
		relationMap.put(newRelation.getRelatedEntityClass(), newRelation);
	}
}
