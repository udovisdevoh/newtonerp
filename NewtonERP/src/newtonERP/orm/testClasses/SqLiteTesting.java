package newtonERP.orm.testClasses;

import newtonERP.orm.Orm;
import newtonERP.orm.OrmActions;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.sgbd.SgbdSqlite;
import newtonERP.orm.sgbd.Sgbdable;

/**
 * @author r3hallejo
 * 
 *         Class used for testing
 * 
 *         Create table for users :"CREATE TABLE User ( PKuserID INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, password STRING, GroupID INTEGER);"
 *         Create table for right :"CREATE TABLE Right ( PKrightID INTEGER PRIMARY KEY AUTOINCREMENT, moduleName STRING, actionName STRING);"
 *         Create table for group right:
 *         "CREATE TABLE GroupRight ( groupID INTEGER, rightID INTEGER);" Create
 *         table for groups :"CREATE TABLE Groups ( PKgroupID INTEGER PRIMARY KEY AUTOINCREMENT, groupName STRING);"
 */
public class SqLiteTesting
{
    private static Sgbdable sgbd = new SgbdSqlite();

    /**
     * @param args the arguments
     */
    public static void main(String[] args)
    {
	try
	{
	    Orm.connect();

	    String sqlQuery = "";

	    sgbd.execute(sqlQuery, OrmActions.CREATE);

	    Orm.disconnect();
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

    }

}
