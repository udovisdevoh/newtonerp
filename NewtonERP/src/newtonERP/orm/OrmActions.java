package newtonERP.orm;

/**
 * 
 * @author r3hallejo
 *
 * Used by the orm to find which action it has to do. Althought it is being used by the orm it is also passed
 * to the orm by the program
 */
public enum OrmActions {
	SEARCH,
	ADD,
	DELETE,
	UPDATE;
}
