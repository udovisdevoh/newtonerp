package newtonERP.orm.entity;

import java.util.List;

/**
 * The Interface OrmEntity is the interface representing all action needed for an entity to be used by the ORM
 */
public interface OrmEntity {

	/**
	 * Sets the data.
	 * 
	 * @param fieldName the field name
	 * @param data the data
	 */
	void setData(String fieldName, Object data);

	/**
	 * Gets the.
	 * 
	 * @return the list<? extends relation entity>
	 */
	List<? extends RelationEntity> get();
}
