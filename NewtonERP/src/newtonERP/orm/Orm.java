package newtonERP.orm;

import java.util.Vector;

import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.sgbd.SgbdSqlite;
import newtonERP.orm.sgbd.Sgbdable;

/**
 * 
 * @author r3hallejo, r3lacasgu
 * 
 *         Basic class for the orm. It is used to put the objects in the databse
 *         using SqLite3 and it's java binding. The orm will receive an entity
 *         from which the orm will perform various tasks such has generating the
 *         query. Than it's gonna send the query to the SgbdSqlite classe to
 *         execute it.
 */
public class Orm
{
    private Sgbdable sgbd = new SgbdSqlite();

    public static Vector<Ormizable> getSearchResult(Ormizable searchEntity)
    {
	return null;
    }

    public static void add(Ormizable newEntity) throws OrmException
    {
    }

    public static void delete(Ormizable searchEntity) throws OrmException
    {
    }

    public static void update(Ormizable searchEntity,
	    Ormizable entityContainingChanges) throws OrmException
    {
    }
}
