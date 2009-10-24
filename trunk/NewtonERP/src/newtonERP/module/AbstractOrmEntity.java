package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.module.field.Field;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 */
public abstract class AbstractOrmEntity extends AbstractEntity
{

    // oblige le redefinition pour les sous-classe de AbstractOrmEntity
    public abstract Fields initFields();

    /**
     * @return data ormizable
     */
    public Hashtable<String, String> getOrmizableData()
    {
	return getFields().getHashTableFrom();
    }

    /**
     * @param parameters la hashTable de parametre qui sera transphormé en
     *            entity
     */
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	getFields().setFromHashTable(parameters);
    }

    /**
     * BaseAction New
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
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
     * BaseAction Delete
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
     */
    public abstract AbstractEntity deleteUI(Hashtable<String, String> parameters);

    /**
     * supprime l'entity en DB
     * 
     * @param whereClause the where clause for the query
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

    /**
     * BaseAction Edit
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
     */
    public abstract AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException;

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
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
     * BaseAction Get
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
     * @throws InvalidOperatorException if a wrong operator is set for the field
     *             datatype
     */
    public abstract AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException;

    /**
     * trouve l'entity selon les critere disponible, retourne le premier trouve
     * 
     * @param whereClause the where clause for the query
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

    /**
     * @param entities the entities from which we are going to select our data
     *            (where clause)
     * @return the selected entities
     */
    public Vector<AbstractOrmEntity> get(Vector<AbstractOrmEntity> entities)
    {
	Vector<AbstractOrmEntity> retEntities = null;

	try
	{
	    retEntities = Orm.select(entities);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return retEntities;
    }

    /**
     * @param entity
     * @return the selected entities
     */
    public Vector<AbstractOrmEntity> get(AbstractOrmEntity entity)
    {
	Vector<AbstractOrmEntity> entities = new Vector<AbstractOrmEntity>();
	entities.add(entity);
	return get(entities);
    }

    public Hashtable<String, String> getInputList() throws OrmException
    {
	return getFields().getHashTableFromWithLongNames();
    }

    public String getPrimaryKeyName()
    {
	String firstLetter = (getClass().getSimpleName().charAt(0) + "")
		.toLowerCase();

	return "PK" + firstLetter + getClass().getSimpleName().substring(1)
		+ "ID";
    }

    public String getPrimaryKeyValue()
    {
	String primaryKeyName = getPrimaryKeyName();
	Fields fields = getFields();
	Field field = fields.getField(primaryKeyName);

	if (field == null)
	    return null;

	String value = field.getDataString();
	return value;
    }
}
