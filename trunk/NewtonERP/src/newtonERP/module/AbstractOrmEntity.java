package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 */
public abstract class AbstractOrmEntity extends AbstractEntity implements
	Ormizable
{

    // oblige le redefinition pour les sous-classe de AbstractOrmEntity
    public abstract Fields initFields();

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#getOrmizableData()
     */
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	return getHashTableFromEntity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#setOrmizableData(java.util.Hashtable)
     */
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	setEntityFromHashTable(parameters);
    }

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

    public abstract AbstractEntity deleteUI(Hashtable<String, String> parameters);

    /**
     * supprime l'entity en DB
     * 
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

    public abstract AbstractEntity editUI(Hashtable<String, String> parameters);

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
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

    public abstract AbstractEntity getUI(Hashtable<String, String> parameters);

    /**
     * trouve l'entity selon les critere disponible, retourne le premier trouve
     * 
     * @return this
     */
    public AbstractEntity get(String whereClause)
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	User retUser = new User();
	try
	{
	    // todo: should we throw an exception if retUserList.size() is
	    // bigger than 1 ?? -> r3hallejo dit : Pourquoi devrait-on? Si on
	    // cherche des users ayant comme nom Réjean Grosjean y peut en avoir
	    // des dizaines....

	    Vector<Ormizable> retUserList = Orm.select(new User(),
		    whereParameter);
	    retUser = (User) retUserList.get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return retUser;
    }

}
