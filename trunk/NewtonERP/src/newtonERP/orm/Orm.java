package newtonERP.orm;

/**
 * 
 * @author r3hallejo
 * 
 * Basic class for the orm. It is used to put the objects in the databse using SqLite3 and it's java binding.
 * In general what it's gonna do else than initializing the connection to the database or closing it, the orm
 * will receive an entity from which the orm will perform various tasks. How dows he determine which task to do?
 * He uses the OrmActions enum to find out if this is a research or a deletion etc...
 */
public class Orm {
	private OrmActions[] ormActions = OrmActions.values(); // Takes all the values that contains the enum
}
