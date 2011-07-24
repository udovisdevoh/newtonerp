package newtonERP.module.generalEntity; 
 // TODO: clean up that file

import java.util.Vector;

import newtonERP.common.NaturalMap;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.viewers.viewables.SelectBoxViewable;

/**
 * List of value for the viewers
 * 
 * @author Guillaume Lacasse
 */

@SuppressWarnings("deprecation")
public class ListOfValue implements SelectBoxViewable {
	private String labelName;
	private AbstractOrmEntity sourceEntity;
	private AbstractOrmEntity foreignEntity;

	/**
	 * @param sourceEntity source entity
	 * @param labelName the label name
	 * @param foreignPrimaryKey the foreign primary key naturelle de l'entité étrangere
	 * @param foreignEntity the foreign entity
	 */
	public ListOfValue(AbstractOrmEntity sourceEntity, String labelName, AbstractOrmEntity foreignEntity) {
		this.sourceEntity = sourceEntity;
		this.labelName = labelName;
		this.foreignEntity = foreignEntity;
	}

	@Override
	public NaturalMap<String, String> getElements() {
		Vector<AbstractOrmEntity> entityList = Orm.getInstance().select(foreignEntity, null);

		NaturalMap<String, String> elementList = new NaturalMap<String, String>();

		for(AbstractOrmEntity entity : entityList){
			elementList.put(entity.getPrimaryKeyValue() + "", entity.getNaturalKeyDescription());
		}

		return elementList;
	}

	@Override
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param currentForeignPrimaryKey the primary key
	 * @return the foreign value of the primary key
	 */
	public String getForeignValue(String currentForeignPrimaryKey) {
		try{
			Vector<String> criterias = new Vector<String>();
			criterias.add(foreignEntity.getPrimaryKeyName() + "=" + currentForeignPrimaryKey);
			Vector<AbstractOrmEntity> entityList = Orm.getInstance().select(foreignEntity, criterias);
			AbstractOrmEntity resultEntity = entityList.get(0);

			return resultEntity.getNaturalKeyDescription();
		}catch(Exception e){
			return "- " + foreignEntity.getVisibleName() + " invalide - ";
		}
	}

	/**
	 * @return définition pour entité étrangère
	 */
	public AbstractOrmEntity getForeignEntityDefinition() {
		return foreignEntity;
	}

	/**
	 * @return entité qui a une listOfValue
	 */
	public AbstractOrmEntity getSourceEntityDefinition() {
		return sourceEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foreignEntity == null) ? 0 : foreignEntity.hashCode());
		result = prime * result + ((labelName == null) ? 0 : labelName.hashCode());
		result = prime * result + ((sourceEntity == null) ? 0 : sourceEntity.hashCode());
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
		ListOfValue other = (ListOfValue) obj;
		if(foreignEntity == null){
			if(other.foreignEntity != null){
				return false;
			}
		}else if(!foreignEntity.equals(other.foreignEntity)){
			return false;
		}
		if(labelName == null){
			if(other.labelName != null){
				return false;
			}
		}else if(!labelName.equals(other.labelName)){
			return false;
		}
		if(sourceEntity == null){
			if(other.sourceEntity != null){
				return false;
			}
		}else if(!sourceEntity.equals(other.sourceEntity)){
			return false;
		}
		return true;
	}
}
