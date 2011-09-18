package newtonERP.orm.relation;

import newtonERP.orm.entity.OrmEntity;
import newtonERP.orm.entity.RelationEntity;

/**
 * A factory for creating Relation between entity.
 */
public class RelationFactory {

	/**
	 * New relation one to one.
	 * 
	 * @param firstEntity the first entity
	 * @param secondEntity the second entity
	 */
	public static void createRelationOneToOne(RelationEntity firstEntity, RelationEntity secondEntity) {
		firstEntity.addNewRelation(new RelationOneToOne(firstEntity, secondEntity.getClass()));
		secondEntity.addNewRelation(new RelationOneToOne(secondEntity, firstEntity.getClass()));
	}

	/**
	 * Creates a new Relation one to many.
	 * 
	 * @param oneEntity the entity on the 'one' side
	 * @param manyEntity the entity on the 'many' side
	 */
	public static void createRelationOneToMany(RelationEntity oneEntity, RelationEntity manyEntity) {
		oneEntity.addNewRelation(new RelationOneToMany(oneEntity, manyEntity.getClass()));
		manyEntity.addNewRelation(new RelationOneToOne(manyEntity, oneEntity.getClass()));
	}

	/**
	 * New relation many to many.
	 * 
	 * @param firstEntity the first entity
	 * @param secondEntity the second entity
	 */
	public static void createRelationManyToMany(RelationEntity firstEntity, RelationEntity secondEntity) {
		firstEntity.addNewRelation(new RelationManyToMany(firstEntity, secondEntity.getClass()));
		secondEntity.addNewRelation(new RelationManyToMany(secondEntity, firstEntity.getClass()));
	}

	/**
	 * New relation many to many with relation data.
	 * 
	 * @param firstEntity the first entity
	 * @param secondEntity the second entity
	 * @param relationDataEntityClass the relation data entity class
	 */
	public static void createRelationManyToMany(RelationEntity firstEntity, RelationEntity secondEntity,
			Class<? extends OrmEntity> relationDataEntityClass) {
		firstEntity.addNewRelation(new RelationManyToMany(firstEntity, secondEntity.getClass(), relationDataEntityClass));
		secondEntity.addNewRelation(new RelationManyToMany(secondEntity, firstEntity.getClass(), relationDataEntityClass));
	}
}
