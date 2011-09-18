package newtonERP.orm.entity;

import newtonERP.orm.relation.AbstractRelation;

/**
 * The Interface RelationEntity specify that an Entity can be used in a relation
 */
public interface RelationEntity extends OrmEntity {

	/**
	 * Gets the id of the current record.
	 * 
	 * @return the id
	 */
	public int getId();

	/**
	 * Gets the relation to a specific entity.
	 * 
	 * @param relatedEntityClass the related entity class
	 * @return the relation
	 */
	public AbstractRelation getRelation(Class<? extends RelationEntity> relatedEntityClass);

	/**
	 * Add a new relation to the entity.
	 * 
	 * @param newRelation the new relation
	 */
	public void addNewRelation(AbstractRelation newRelation);
}
