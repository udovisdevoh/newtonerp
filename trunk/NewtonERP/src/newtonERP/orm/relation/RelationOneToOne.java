package newtonERP.orm.relation;

import newtonERP.orm.entity.RelationEntity;


/**
 * The Class RelationToOneEntity.
 * 
 * @author Jonatan Cloutier
 */
public class RelationOneToOne extends AbstractRelation {

	/** The relation information. */
	private RelationInfo relationInfo;

	/**
	 * Instantiates a new relation one to one.
	 * @param parentEntity the parent entity
	 * @param relatedEntityClass the related entity class
	 */
	public RelationOneToOne(RelationEntity parentEntity, Class<? extends RelationEntity> relatedEntityClass) {
		super(parentEntity, relatedEntityClass);
		relationInfo = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newRelation(int relatedId) {
		if(relationInfo != null){
			throw new RuntimeException("cannot set new relation entity on a 'to one' relation has it already has one.");
		}
		relationInfo = new RelationInfo();
		relationInfo.setRelatedId(relatedId);
	}

	/**
	 * {@inheritDoc}
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
		if(relationInfo != null){
			throw new RuntimeException("cannot set new relation entity on a 'to one' relation has it already has one.");
		}

		relationInfo = new RelationInfo();
		relationInfo.setRelateEntity(otherRelation.parentEntity);
		relationInfo.setRelatedId(otherRelation.parentEntity.getId());
		relationInfo.setInverseRelation(otherRelation);
	}

	/**
	 * Gets the related id.
	 * 
	 * @return the related id
	 */
	public final int getRelatedId() {
		return relationInfo.getRelatedId();
	}

	/**
	 * Gets the related entity.
	 * 
	 * @return the related entity
	 */
	public final RelationEntity getRelatedEntity() {
		return relationInfo.getRelatedEntity();
	}

	@Override
	public void removeRelation(int id) {
		if(relationInfo == null || relationInfo.getRelatedId() != id){
			return;
		}

		if(relationInfo.isQueried()){
			relationInfo.getInverseRelation().removeDirectRelation(parentEntity);
			removeDirectRelation(relationInfo.getRelatedEntity());
		}else{
			relationInfo = null;
		}
	}

	@Override
	public void removeRelation(RelationEntity relatedEntity) {
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
		if(relationInfo == null || !relationInfo.getRelatedEntity().equals(relatedEntity)){
			return;
		}

		relationInfo = null;
	}

}
