package newtonERP.module;

import java.text.ParseException;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.ListModule;
import newtonERP.module.field.Field;
import newtonERP.module.field.Fields;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.FlagPoolManager;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 */
public abstract class AbstractOrmEntity extends AbstractEntity
{
    private Vector<String> naturalKeyNameList;

    public AbstractOrmEntity() throws Exception
    {
	super();
    }

    private Hashtable<String, FlagPool> flagPoolList;

    // oblige le redefinition pour les sous-classe de AbstractOrmEntity
    public abstract Fields initFields() throws Exception;

    /**
     * @return data ormizable
     */
    public final Hashtable<String, String> getOrmizableData()
    {
	return getFields().getHashTableFrom();
    }

    /**
     * @param parameters la hashTable de parametre qui sera transphormé en
     *            entity
     * @throws ParseException an exception that can occur when parsing dates
     */
    public final void setOrmizableData(Hashtable<String, Object> parameters)
	    throws ParseException
    {
	getFields().setFromHashTable(parameters);
    }

    /**
     * BaseAction New
     * 
     * @param parameters parametre suplementaire
     * @return l'entity qui a été entré en DB (incluant la primaryKey)
     * @throws Exception remonte
     */
    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	AbstractOrmEntity entity = this.getClass().newInstance();
	entity.getFields().setDefaultValue();

	return entity.editUI(parameters);
    }

    /**
     * enregistre l'entity en DB
     * 
     * @return this
     * @throws Exception remonte
     */
    public final AbstractOrmEntity newE() throws Exception
    {
	if (getFields().getKeyList().contains(getPrimaryKeyName()))
	    setData(getPrimaryKeyName(), Orm.insert(this));
	else
	    Orm.insert(this);
	return this;
    }

    /**
     * BaseAction Delete
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
     * @throws Exception remonte
     */
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	delete(getPrimaryKeyName() + "='" + getDataString(getPrimaryKeyName())
		+ "'");

	return getList();
    }

    /**
     * supprime l'entity en DB
     * 
     * @param whereClause the where clause for the query
     * 
     * @throws OrmException remonte
     */
    public final void delete(String whereClause) throws OrmException
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	Orm.delete(this, whereParameter);
    }

    /**
     * BaseAction Edit
     * 
     * @param parameters parametre suplementaire
     * @return todo: qu'Est-ce que l'on devrai retourné en general?
     * @throws Exception remonte
     */
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	AbstractOrmEntity retEntity;
	AbstractOrmEntity searchEntity = this.getClass().newInstance();
	if (getPrimaryKeyValue() != 0)
	{
	    searchEntity.setData(getPrimaryKeyName(), getPrimaryKeyValue());

	    // il ne peu y avoir plus d'une entity (search par primaryKey)
	    retEntity = get(searchEntity).get(0);
	}
	else
	{
	    retEntity = this;
	}
	retEntity.setCurrentAction(new BaseAction("Edit", this));

	if (parameters != null && parameters.containsKey("submit"))
	{
	    if (getPrimaryKeyValue() == 0)
	    {
		newE();
	    }
	    else
	    {// todo: faire un edit sans param qui ne se base que sur la cle
		// Primaire
		edit(getPrimaryKeyName() + "='" + getPrimaryKeyValue() + "'");
	    }

	    FlagPoolManager.applyFlagPoolChanges(this, getFlagPoolList()
		    .values(), parameters);

	    return getList();
	}
	return retEntity;
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @return this
     * @throws OrmException remonte
     */
    public final AbstractEntity edit(String whereClause) throws OrmException
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	Orm.update(this, whereParameter);
	return this;
    }

    /**
     * BaseAction Get
     * 
     * @param parameters parametre suplementaire
     * @return entity qui doit etre read only
     * @throws Exception remonte
     */
    public AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	return editUI(parameters); // TODO: rendre readOnly, néscéssaire pour
	// une gestion de droit sufisante
    }

    public final AbstractEntity getList() throws Exception
    {
	return getList(new Hashtable<String, String>());
    }

    public AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	Vector<AbstractOrmEntity> resultSet;

	AbstractOrmEntity searchEntity = this.getClass().newInstance();

	try
	{
	    resultSet = Orm.select(searchEntity, null);

	} catch (OrmException e)
	{
	    resultSet = new Vector<AbstractOrmEntity>();
	}

	EntityList entityList = new EntityList(searchEntity);

	for (AbstractOrmEntity entity : resultSet)
	    entityList.addEntity(entity);

	// TODO: Problème: c'est très bizare de devoir recréer un objet de
	// module pour spécifier quel module sera responsable des actions -
	// Guillaume
	entityList.setCurrentModule(getCurrentModule());

	return entityList;
    }

    /**
     * @return Module par default
     * @throws Exception remonte
     */
    @Override
    public Module getCurrentModule() throws Exception
    {
	if (currentModule == null)
	{
	    String fullClassName = getClass().getName();

	    String[] wordList = fullClassName.split("\\.");

	    String modulePackageName = wordList[wordList.length - 3];

	    String moduleName = ("" + modulePackageName.charAt(0))
		    .toUpperCase()
		    + modulePackageName.substring(1);

	    return ListModule.getModule(moduleName);
	}

	return currentModule;
    }

    private void updateForeignFlagPoolData(FlagPool flagPool)
    {
	// TODO Auto-generated method stub
    }

    /**
     * trouve l'entity selon les critere disponible, retourne le premier trouve
     * 
     * @param whereClause the where clause for the query
     * @return this
     * @throws Exception remonte
     */
    public final Vector<AbstractOrmEntity> get(String whereClause)
	    throws Exception
    {
	Vector<AbstractOrmEntity> retEntityList = null;
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	retEntityList = Orm.select(this.getClass().newInstance(),
		whereParameter);

	return retEntityList;
    }

    /**
     * @param entities the entities from which we are going to select our data
     *            (where clause)
     * @return the selected entities
     * @throws OrmException remonte
     */
    public final Vector<AbstractOrmEntity> get(
	    Vector<AbstractOrmEntity> entities) throws OrmException
    {
	Vector<AbstractOrmEntity> retEntities = null;
	retEntities = Orm.select(entities);

	return retEntities;
    }

    /**
     * @param entity the search entity
     * @return the selected entities
     * @throws OrmException remonte
     */
    public final Vector<AbstractOrmEntity> get(AbstractOrmEntity entity)
	    throws OrmException
    {
	Vector<AbstractOrmEntity> entities = new Vector<AbstractOrmEntity>();
	entities.add(entity);
	return get(entities);
    }

    /**
     * @return Nom de la clef primaire
     */
    public final String getPrimaryKeyName()
    {
	String firstLetter = (getClass().getSimpleName().charAt(0) + "")
		.toLowerCase();

	return "PK" + firstLetter + getClass().getSimpleName().substring(1)
		+ "ID";
    }

    public String getForeignKeyName()
    {
	String firstLetter = (getClass().getSimpleName().charAt(0) + "")
		.toLowerCase();
	return firstLetter + getClass().getSimpleName().substring(1) + "ID";
    }

    /**
     * @return Valeur de la clef primaire
     */
    public final Integer getPrimaryKeyValue()
    {
	String primaryKeyName = getPrimaryKeyName();
	Fields fieldsList = getFields();
	Field field = fieldsList.getField(primaryKeyName);

	if (field == null || field.getData() == null)
	    return null;

	int value = (Integer) field.getData();

	return value;
    }

    /**
     * Implémentation par default
     * @return Caption par default d'un bouton de modification pour cette entité
     */
    public String getButtonCaption()
    {
	return "Enregistrer";
    }

    /**
     * Implémentation par default pouvant être overridée dans l'entité
     * @return Description
     */
    @Override
    public String getPromptMessage()
    {
	if (promptMessage == null)
	    promptMessage = "Profil d'un " + getClass().getSimpleName();
	return promptMessage;
    }

    /**
     * @return the flag pool list
     */
    public Hashtable<String, FlagPool> getFlagPoolList()
    {
	if (flagPoolList == null)
	    flagPoolList = new Hashtable<String, FlagPool>();

	return flagPoolList;
    }

    /**
     * @param sourceEntityDefinition Definition de l'entité de source, exemple:
     *            groupe
     * @param visibleDescription Description visible du flag pool
     * @param intermediateEntityDefinition Entité de table intermédiaire,
     *            exemple: GroupsRight
     * @param intermediateKeyIn Colonne d'entré de table intermédiaire, exemple:
     *            groupID
     * @param intermediateKeyOut Colonne de sortie de table intermédiaire,
     *            exemple: rightID
     * @param foreignEntityDefinition entité de table étrangère, exemple: Right
     * @param foreignKey clef d'identification de table étrangère, exemple:
     *            PKrightID
     * @param foreignDescriptionUiControls liste de colonne de description de
     *            table étrangère, exemple: Action, Module
     */

    public void setFlagPoolList(Hashtable<String, FlagPool> flagPoolList)
    {
	this.flagPoolList = flagPoolList;
    }

    /**
     * @return Liste des noms de champs correspondant à la clef naturelle de
     *         l'entité
     */
    public Vector<String> getNaturalKeyNameList()

    {
	if (naturalKeyNameList == null)
	    naturalKeyNameList = new Vector<String>();

	if (naturalKeyNameList.size() < 1) // 1er Comportement par default si
	    // clef
	    // naturelle vide
	    for (Field field : getFields())
		if (field.getShortName().toLowerCase().contains("name"))
		    naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1) // 2e Comportement par default si
	    // clef
	    // naturelle vide
	    for (Field field : getFields())
		if (!field.getShortName().equals(getPrimaryKeyName()))
		    naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1)// 3e Comportement par default si clef
	    // naturelle vide
	    naturalKeyNameList.add(getPrimaryKeyName());

	return naturalKeyNameList;
    }

    /**
     * @return Description d'une entité par sa clef naturelle
     */
    public String getNaturalKeyDescription()
    {
	String description = "";
	for (String naturalKeyName : getNaturalKeyNameList())
	    description += " " + getDataString(naturalKeyName);

	return description.trim();
    }

    /**
     * @param keyName Ajoute une clef naturelle à l'entité
     */
    public final void addNaturalKey(String keyName)
    {
	if (naturalKeyNameList == null)
	    naturalKeyNameList = new Vector<String>();
	naturalKeyNameList.add(keyName);
    }
}
