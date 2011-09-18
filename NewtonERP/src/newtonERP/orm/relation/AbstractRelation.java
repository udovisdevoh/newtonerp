package newtonERP.orm.relation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import newtonERP.orm.entity.RelationEntity;
import newtonERP.orm.exceptions.OrmException;

/**
 * The AbstractRelation class specify the base action for any relation that might exist between two entity.
 * 
 * @author Jonatan Cloutier
 */
public abstract class AbstractRelation {

	/** The related entity class. */
	protected Class<? extends RelationEntity> relatedEntityClass;

	/** The parent entity. */
	protected RelationEntity parentEntity;

	/**
	 * Instantiates a new abstract relation.
	 * @param parentEntity the parent entity
	 * @param relatedEntityClass the related entity class
	 */
	public AbstractRelation(RelationEntity parentEntity, Class<? extends RelationEntity> relatedEntityClass) {
		this.relatedEntityClass = relatedEntityClass;
		this.parentEntity = parentEntity;
	}

	/**
	 * Set or add directly a new relation.
	 * 
	 * @param otherRelation the other relation
	 */
	protected abstract void newDirectRelation(AbstractRelation otherRelation);

	/**
	 * Removes directly relation.
	 * 
	 * @param otherEntity the other entity
	 */
	protected abstract void removeDirectRelation(RelationEntity otherEntity);

	/**
	 * New relation by id.
	 * 
	 * @param relatedId the related id
	 */
	public abstract void newRelation(int relatedId);

	/**
	 * New relation by entity.
	 * 
	 * @param newRelatedEntity the new related entity
	 */
	public abstract void newRelation(RelationEntity newRelatedEntity);

	/**
	 * Removes the relation by id.
	 * 
	 * @param id the id
	 */
	public abstract void removeRelation(int id);

	/**
	 * Removes the relation by entity.
	 * 
	 * @param relatedEntity the related entity
	 */
	public abstract void removeRelation(RelationEntity relatedEntity);

	/**
	 * Gets the parent entity.
	 * 
	 * @return the parent entity
	 */
	public RelationEntity getParentEntity() {
		return parentEntity;
	}

	/**
	 * Gets the related entity class.
	 * 
	 * @return the related entity class
	 */
	public Class<? extends RelationEntity> getRelatedEntityClass() {
		return relatedEntityClass;
	}

	/**
	 * The Class relationInfo. This inner class to AbstractRelation contain the needed information about a related
	 * entity.
	 */
	protected class RelationInfo {

		/** The relate entity. */
		private RelationEntity relateEntity = null;

		/** The related id. */
		private int relatedId = 0;

		/** The inverse relation. */
		private AbstractRelation inverseRelation = null;

		/**
		 * Gets the relate entity. If not already loaded get it from database then return it.
		 * 
		 * @return the relateEntity
		 */
		public final RelationEntity getRelatedEntity() {
			queryRelation();
			return relateEntity;
		}

		/**
		 * Sets the relate entity.
		 * 
		 * @param relateEntity the relateEntity to set
		 */
		public final void setRelateEntity(RelationEntity relateEntity) {
			if(!relatedEntityClass.isAssignableFrom(relateEntity.getClass())){
				throw new OrmException("cannot assigned a related entity of type "
						+ relateEntity.getClass().getSimpleName() + " current relation can only accept "
						+ relatedEntityClass.getSimpleName());
			}
			this.relateEntity = relateEntity;
		}

		/**
		 * Gets the related id.
		 * 
		 * @return the relatedId
		 */
		public final int getRelatedId() {
			return relatedId;
		}

		/**
		 * Sets the related id.
		 * 
		 * @param relatedId the relatedId to set
		 */
		public final void setRelatedId(int relatedId) {
			this.relatedId = relatedId;
		}

		/**
		 * Gets the inverse relation.
		 * 
		 * @return the inverseRelation
		 */
		public final AbstractRelation getInverseRelation() {
			queryRelation();
			return inverseRelation;
		}

		/**
		 * Sets the inverse relation.
		 * 
		 * @param inverseRelation the inverseRelation to set
		 */
		public final void setInverseRelation(AbstractRelation inverseRelation) {
			this.inverseRelation = inverseRelation;
		}

		/**
		 * Checks if the entity has bean queried or manually setup.
		 * 
		 * @return true when both relatedEntityClass and inverseRelation are initialized
		 */
		public final boolean isQueried() {
			return relatedEntityClass != null && inverseRelation != null;
		}

		/**
		 * Query relation. This will query the data source to get the relatedEntity and will initialise it's relation
		 */
		public void queryRelation() {
			if(isQueried() == false){
				try{
					// TODO : change that awful way of getting relation
					RelationEntity relatedEntityInstance = relatedEntityClass.newInstance();
					relatedEntityInstance.setData("ID", relatedId);
					relateEntity = relatedEntityInstance.get().get(0);
					inverseRelation = relateEntity.getRelation(parentEntity.getClass());
					inverseRelation.newDirectRelation(AbstractRelation.this);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * The Class RelationInfoList is an utility class that wrap a list of RelationInfo to have direct access to inner
	 * RelationInfo without having to search for it first.
	 * 
	 * @param <T> the generic type
	 */
	protected class RelationInfoList<T extends RelationInfo> implements Iterable<T> {

		/** The relation info list. */
		private List<T> relationInfoList;

		/**
		 * @param e element to be appended to this list
		 * @return true (as specified by Collection.add(E))
		 * @see java.util.List#add(java.lang.Object)
		 */
		public boolean add(T e) {
			return relationInfoList.add(e);
		}

		/**
		 * Adds all element from c.
		 * 
		 * @param c collection containing elements to be added to this list
		 * @return true if this list changed as a result of the call
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		public boolean addAll(Collection<? extends T> c) {
			return relationInfoList.addAll(c);
		}

		/**
		 * @see java.util.List#clear()
		 */
		public void clear() {
			relationInfoList.clear();
		}

		/**
		 * checks if the list contains a relation info by id.
		 * 
		 * @param id the id
		 * @return true if this list contains the specified element
		 * @see java.util.List#contains(java.lang.Object)
		 */
		public boolean contains(int id) {
			return get(id) != null;
		}

		/**
		 * checks if the list contains a relation info by entity.
		 * 
		 * @param entity the entity
		 * @return true if this list contains the specified element
		 * @see java.util.List#contains(java.lang.Object)
		 */
		public boolean contains(RelationEntity entity) {
			return get(entity) != null;
		}

		/**
		 * Gets the relation info by id.
		 * 
		 * @param id the id
		 * @return the relation info or null if not present
		 */
		public T get(int id) {
			for(T relationInfo : relationInfoList){
				if(relationInfo.getRelatedId() == id){
					return relationInfo;
				}
			}
			return null;
		}

		/**
		 * Gets the relation info by entity.
		 * 
		 * @param entity the entity
		 * @return the relation info or null if not present
		 */
		public T get(RelationEntity entity) {
			for(T relationInfo : relationInfoList){
				if(relationInfo.getRelatedEntity().equals(entity)){
					return relationInfo;
				}
			}
			return null;
		}

		/**
		 * Gets all the id.
		 * 
		 * @return the related id list
		 */
		public List<Integer> getAllIds() {
			List<Integer> entityList = new ArrayList<Integer>(size());
			for(T relationInfo : relationInfoList){
				entityList.add(relationInfo.getRelatedId());
			}
			return entityList;
		}

		/**
		 * Gets all the entity.
		 * 
		 * @return the all entity
		 */
		public List<? extends RelationEntity> getAllEntity() {
			List<RelationEntity> entityList = new ArrayList<RelationEntity>(size());
			for(T relationInfo : relationInfoList){
				entityList.add(relationInfo.getRelatedEntity());
			}
			return entityList;
		}

		/**
		 * Checks if is empty.
		 * 
		 * @return true if this list contains no elements
		 * @see java.util.List#isEmpty()
		 */
		public boolean isEmpty() {
			return relationInfoList.isEmpty();
		}

		/**
		 * Iterator.
		 * 
		 * @return an iterator over the elements in this list in proper sequence
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<T> iterator() {
			return relationInfoList.iterator();
		}

		/**
		 * List iterator.
		 * 
		 * @return a list iterator over the elements in this list (in proper sequence)
		 * @see java.util.List#listIterator()
		 */
		public ListIterator<T> listIterator() {
			return relationInfoList.listIterator();
		}

		/**
		 * List iterator.
		 * 
		 * @param index index of the first element to be returned from the list iterator (by a call to next)
		 * @return a list iterator over the elements in this list (in proper sequence), starting at the specified
		 *         position in the list
		 * @see java.util.List#listIterator(int)
		 */
		public ListIterator<T> listIterator(int index) {
			return relationInfoList.listIterator(index);
		}

		/**
		 * Removes the relation info by id.
		 * 
		 * @param id the id
		 * @return true, if successful
		 * @see java.util.List#remove(java.lang.Object)
		 */
		public boolean remove(int id) {
			return relationInfoList.remove(get(id));
		}

		/**
		 * Removes the relation info by entity.
		 * 
		 * @param entity the entity
		 * @return true, if successful
		 * @see java.util.List#remove(java.lang.Object)
		 */
		public boolean remove(RelationEntity entity) {
			return relationInfoList.remove(get(entity));
		}

		/**
		 * Size.
		 * 
		 * @return the int
		 * @see java.util.List#size()
		 */
		public int size() {
			return relationInfoList.size();
		}

	}
}
