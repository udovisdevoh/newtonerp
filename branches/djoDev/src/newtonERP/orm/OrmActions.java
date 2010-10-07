package newtonERP.orm;

/**
 * This enum is only describing the type of actions the orm can do. It is only
 * used internally so when we execute the request in the SgbdSqlite we pass an
 * action in parameter because the method to execute the statement in sql is not
 * the same for an insert statement than a select statement for example
 * 
 * @author r3hallejo
 */
public enum OrmActions
{
	SEARCH, /**
	 * Represents a select statement in the Orm
	 */

	UPDATE, /**
	 * Represents an update statement in the orm
	 */

	DELETE, /**
	 * Represents a delete statement in the orm
	 */

	INSERT, /**
	 * Represents an insert statement in the orm
	 */

	CREATE, /**
	 * Represents a create table statement in the orm
	 */

	OTHER, /**
	 * Anything else
	 */
	;
}
