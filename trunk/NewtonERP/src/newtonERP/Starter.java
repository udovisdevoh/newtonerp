package newtonERP;

import newtonERP.logging.Logger;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;

/**
 * @author JoCloutier
 * 
 */
public class Starter
{
    /**
     * lance l'aplication et effectue toute les action d'initialisation
     * 
     * @param args aucun
     */
    public static void main(String[] args)
    {
	// For testing
	// Vector<String> searchCriterias = new Vector<String>();
	// searchCriterias.add("name like '%marcel%'");
	// searchCriterias.add(" AND age like '%17%';");

	// ListModule.initAllModule();

	try
	{
	    Orm.connect();

	    // Orm.insert(new TestEntity());
	    // Orm.select(new TestEntity(), searchCriterias);
	    // Orm.delete(new TestEntity(), searchCriterias);
	    // Orm.update(new TestEntity(), new TestEntityModified(),
	    // searchCriterias);
	} catch (OrmException e)
	{
	    Logger.log(e.getMessage(), Logger.State.ERROR);
	}

	new Servlet(47098);

    }
}
