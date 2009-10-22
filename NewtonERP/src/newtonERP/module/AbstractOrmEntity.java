package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 *         An entity that can be saved, updated, selected etc... in the db
 */
public abstract class AbstractOrmEntity extends AbstractEntity
{
    public abstract Fields initFields();

    /**
     * @return the ormizable data
     * @throws OrmException an exception that can occur in the orm
     */
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	return getFields().getHashTableFrom();
    }

    /**
     * @param parameters the data to set
     */
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	getFields().setFromHashTable(parameters);
    }

    /**
     * @param parameters
     * @return
     */
    public abstract AbstractEntity newUI(Hashtable<String, String> parameters);

    /**
     * enregistre l'entity en DB
     * 
     * @return this
     */
    public AbstractEntity newE()
    {
	try
	{
	    Orm.insert(this);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return this;
    }

    /**
     * @param parameters
     * @return
     */
    public abstract AbstractEntity deleteUI(Hashtable<String, String> parameters);

    /**
     * supprime l'entity en DB
     * 
     * @param whereClause the where clause for the query
     * @return this
     */
    public AbstractEntity delete(String whereClause)
    {
	try
	{
	    Vector<String> whereParameter = new Vector<String>();
	    whereParameter.add(whereClause);
	    Orm.delete(this, whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return this; // todo: that a completly non-sense -> r3hallejo dit MDR,
	// true
    }

    /**
     * @param parameters
     * @return
     */
    public abstract AbstractEntity editUI(Hashtable<String, String> parameters);

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause
     * @return this
     */
    public AbstractEntity edit(String whereClause)
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	try
	{
	    Orm.update(this, whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return this;
    }

    /**
     * @param parameters
     * @return
     */
    public abstract AbstractEntity getUI(Hashtable<String, String> parameters);

    /**
     * trouve l'entity selon les critere disponible, retourne le premier trouve
     * 
     * @param whereClause the where clause for query
     * @return this
     */
    public Vector<AbstractOrmEntity> get(String whereClause)
    {
	Vector<AbstractOrmEntity> retUserList = null;
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	try
	{
	    // todo: should we throw an exception if retUserList.size() is
	    // bigger than 1 ?? -> r3hallejo dit : Pourquoi devrait-on? Si on
	    // cherche des users ayant comme nom Réjean Grosjean y peut en avoir
	    // des dizaines....

	    retUserList = Orm.select(new User(), whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return retUserList;
    }

}
