package newtonERP.orm.testClasses;

import java.util.Vector;

import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * 
 * @author r3hallejo
 * 
 *         Test class for execution
 */
public class MainExecuteClass
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
	Vector<String> searchCriterias = new Vector<String>();

	searchCriterias.add("name like '%marcel%'");
	searchCriterias.add(" AND age like '%17%';");

	// TODO Auto-generated method stub
	try
	{
	    // Orm.insert(new TestEntity());
	    Orm.select(new TestEntity(), searchCriterias);
	    // Orm.delete(new TestEntity(), searchCriterias);
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
