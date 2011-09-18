package newtonERP.orm.relation;

import java.util.List;

import newtonERP.orm.entity.RelationEntity;

/**
 * The Class RelationOneToMany.
 * 
 * @author Jonatan Cloutier
 */
public class RelationOneToMany extends AbstractRelation {

	/** The relation information. */
	private RelationInfoList<RelationInfo> relationInfos;

	/**
	 * Instantiates a new relation from one to many entity.
	 * 
	 * @param parentEntity the parent entity of the relation
	 * @param relatedEntityClass the related entity class
	 */
	public RelationOneToMany(RelationEntity parentEntity, Class<? extends RelationEntity> relatedEntityClass) {
		super(parentEntity, relatedEntityClass);
		relationInfos = new RelationInfoList<AbstractRelation.RelationInfo>();
	}

	/**
	 * Adds the related by id.
	 * 
	 * @param relatedId the related id
	 */
	@Override
	public void newRelation(int relatedId) {
		if(relationInfos.contains(relatedId)){
			throw new RuntimeException("cannot add relation that already exist.");
		}
		RelationInfo newRelationInfo = new RelationInfo();
		newRelationInfo.setRelatedId(relatedId);
		relationInfos.add(newRelationInfo);
	}

	/**
	 * Add a relation by entity.
	 * 
	 * @param newRelatedEntity the related entity
	 */
	@Override
	public void newRelation(RelationEntity newRelatedEntity) {
		AbstractRelation inverseRelation = newRelatedEntity.getRelation(parentEntity.getClass());
		inverseRelation.newDirectRelation(this);
		newDirectRelation(inverseRelation);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void newDirectRelation(AbstractRelation otherRelation) {
		if(relationInfos.contains(otherRelation.parentEntity)){
			throw new RuntimeException("cannot add relation that already exist.");
		}
		RelationInfo newRelationInfo = new RelationInfo();
		newRelationInfo.setRelateEntity(otherRelation.parentEntity);
		newRelationInfo.setRelatedId(otherRelation.parentEntity.getId());
		newRelationInfo.setInverseRelation(otherRelation);
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
	 * Removes the relation.
	 * 
	 * @param id the related id to remove
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
	 * @param relatedEntity the related entity to remove
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
	protected void removeDirectRelation(RelationEntity relatedEntity) {
		relationInfos.remove(relatedEntity);
	}

}
