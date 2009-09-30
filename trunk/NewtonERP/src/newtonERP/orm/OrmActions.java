package newtonERP.orm;

/**
 * 
 * @author r3hallejo
 * 
 *         This enum is only describing the type of actions the orm can do. It
 *         is only used internally so when we execute the request in the
 *         SgbdSqlite we pass an action in parameter because the method to
 *         execute the statement in sql is not the same for an insert statement
 *         than a select statement for example
 */
public enum OrmActions
{
    // Possible actions
    SEARCH, UPDATE, DELETE, INSERT;
}
