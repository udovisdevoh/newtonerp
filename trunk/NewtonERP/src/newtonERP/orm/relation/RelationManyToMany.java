package newtonERP.orm.relation;

import java.util.List;

import newtonERP.orm.entity.OrmEntity;
import newtonERP.orm.entity.RelationEntity;
import newtonERP.orm.exceptions.OrmException;

/**
 * The Class RelationManyToMany.
 * 
 * @author Jonatan Cloutier
 */
public class RelationManyToMany extends AbstractRelation {

	/** The related entity. */
	private RelationInfoList<RelationInfoData> relationInfos;

	/** The related entity class. */
	protected Class<? extends OrmEntity> relationDataEntityClass;

	/**
	 * Instantiates a new relation many to many.
	 * 
	 * @param parentEntity the parent entity
	 * @param relatedEntityClass the related entity class
	 */
	public RelationManyToMany(RelationEntity parentEntity, Class<? extends RelationEntity> relatedEntityClass) {
		super(parentEntity, relatedEntityClass);
		relationDataEntityClass = null;
		relationInfos = new RelationInfoList<RelationInfoData>();
	}

	/**
	 * Instantiates a new relation many to many.
	 * 
	 * @param parentEntity the parent entity
	 * @param relatedEntityClass the related entity class
	 * @param relationDataEntityClass the relation data entity class
	 */
	public RelationManyToMany(RelationEntity parentEntity, Class<? extends RelationEntity> relatedEntityClass,
			Class<? extends OrmEntity> relationDataEntityClass) {
		super(parentEntity, relatedEntityClass);
		this.relationDataEntityClass = relationDataEntityClass;
		relationInfos = new RelationInfoList<RelationInfoData>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newRelation(int relatedId) {
		if(relationInfos.contains(relatedId)){
			throw new RuntimeException("cannot add relation that already exist.");
		}
		RelationInfoData newRelationInfo = new RelationInfoData();
		newRelationInfo.setRelatedId(relatedId);
		try{
			newRelationInfo.setRelationDataEntity(relationDataEntityClass.newInstance());
		}catch(Exception e){
			throw new OrmException(e);
		}
		relationInfos.add(newRelationInfo);
	}

	/**
	 * New relation with relation data.
	 * 
	 * @param relatedId the related id
	 * @param relationData the relation data
	 */
	public void newRelation(int relatedId, OrmEntity relationData) {
		if(relationInfos.contains(relatedId)){
			throw new RuntimeException("cannot add relation that already exist.");
		}
		RelationInfoData newRelationInfo = new RelationInfoData();
		newRelationInfo.setRelatedId(relatedId);
		newRelationInfo.setRelationDataEntity(relationData);
		relationInfos.add(newRelationInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newRelation(RelationEntity newRelatedEntity) {
		RelationManyToMany inverseRelation = (RelationManyToMany) newRelatedEntity.getRelation(parentEntity.getClass());
		inverseRelation.newDirectRelation(this, null);
		newDirectRelation(inverseRelation);
	}

	/**
	 * Add a relation by entity.
	 * 
	 * @param newRelatedEntity the related entity
	 * @param relationData the relation data
	 */
	public void newRelation(RelationEntity newRelatedEntity, OrmEntity relationData) {
		RelationManyToMany inverseRelation = (RelationManyToMany) newRelatedEntity.getRelation(parentEntity.getClass());
		inverseRelation.newDirectRelation(this, relationData);
		newDirectRelation(inverseRelation);
	}

	/**
	 * should not be use on a many to many relation because it should be created with relation data
	 * 
	 * @throws OrmException every time this method is called
	 */
	@Override
	protected void newDirectRelation(AbstractRelation otherRelation) {
		throw new OrmException("cannot create a new direct relation without relation data on a many to many relation");
	}

	/**
	 * Set or add directly a new relation many to many.
	 * 
	 * @param otherRelation the other relation
	 * @param relationData the relation data
	 */
	protected void newDirectRelation(AbstractRelation otherRelation, OrmEntity relationData) {
		if(relationInfos.contains(otherRelation.parentEntity)){
			throw new RuntimeException("cannot add relation that already exist.");
		}
		RelationInfoData newRelationInfo = new RelationInfoData();
		newRelationInfo.setRelateEntity(otherRelation.parentEntity);
		newRelationInfo.setRelatedId(otherRelation.parentEntity.getId());
		newRelationInfo.setInverseRelation(otherRelation);
		newRelationInfo.setRelationDataEntity(relationData);
		relationInfos.add(newRelationInfo);
	}

	/**
	 * Gets the related id.
	 * 
	 * @return the related id
	 */
	public final List<Integer> getRelatedIds() {
		return relationInfos.getAllIds();
	}

	/**
	 * Gets the related entity.
	 * 
	 * @return the related entity
	 */
	public final List<? extends RelationEntity> getRelatedEntities() {
		return relationInfos.getAllEntity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeRelation(int id) {
		RelationInfo relationInfo = relationInfos.get(id);
		if(relationInfo == null){
			return;
		}

		if(relationInfo.isQueried()){
			relationInfo.getInverseRelation().removeDirectRelation(parentEntity);
			removeDirectRelation(relationInfo.getRelatedEntity());
		}else{
			relationInfos.remove(id);
		}
	}

	/**
	 * Removes the relation.
	 * 
	 * @param relatedEntity the related entity
	 */
	@Override
	public void removeRelation(RelationEntity relatedEntity) {
		RelationInfo relationInfo = relationInfos.get(relatedEntity);
		if(relationInfo == null){
			return;
		}

		relationInfo.getInverseRelation().removeDirectRelation(parentEntity);
		removeDirectRelation(relatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeDirectRelation(RelationEntity otherEntity) {
		relationInfos.remove(otherEntity);

	}

	/**
	 * The Class RelationInfoData.
	 */
	protected class RelationInfoData extends RelationInfo {

		/** The relation data entity. */
		private OrmEntity relationDataEntity = null;

		/**
		 * Gets the relation data entity.
		 * 
		 * @return the relation data entity
		 */
		public OrmEntity getRelationDataEntity() {
			return relationDataEntity;
		}

		/**
		 * Sets the relation data entity.
		 * 
		 * @param relationDataEntity the new relation data entity
		 */
		public void setRelationDataEntity(OrmEntity relationDataEntity) {
			if(relationDataEntityClass == null){
				throw new OrmException(
						"cannot assigned a relation data entity, this relation doesnt contain relation data");
			}
			if(!relationDataEntityClass.isAssignableFrom(relationDataEntity.getClass())){
				throw new OrmException("cannot assigned a relation data entity of type "
						+ relationDataEntity.getClass().getSimpleName() + " current relation can only accept "
						+ relationDataEntityClass.getSimpleName());
			}
			this.relationDataEntity = relationDataEntity;
		}

	}

}
