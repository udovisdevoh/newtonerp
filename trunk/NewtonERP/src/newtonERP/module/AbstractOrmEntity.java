package newtonERP.module;

import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.ListModule;
import newtonERP.module.field.Field;
import newtonERP.module.field.Fields;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.FlagPoolManager;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.MoneyViewer;

/**
 * @author cloutierJo
 * 
 */
public abstract class AbstractOrmEntity extends AbstractEntity
{
    private Vector<String> naturalKeyNameList;

    private String visibleName;

    private TreeMap<String, Vector<AbstractOrmEntity>> pluralAccessorList;

    private TreeMap<String, AbstractOrmEntity> singleAccessorList;

    /**
     * @throws Exception lorsque la création de l'entité échoue
     */
    public AbstractOrmEntity() throws Exception
    {
	super();
    }

    private Hashtable<String, FlagPool> positiveFlagPoolList;

    private static Hashtable<String, FlagPool> negativeFlagPoolList;

    // oblige le redefinition pour les sous-classe de AbstractOrmEntity
    public abstract Fields initFields() throws Exception;

    /**
     * @param parameters la hashTable de parametre qui sera transphormé en
     *            entity
     * @throws Exception lorsque l'insertion de données échoue
     */
    public final void setOrmizableData(Hashtable<String, Object> parameters)
	    throws Exception
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
     * supprime l'entity en DB, la clause where est definie par this
     * 
     * @throws OrmException remonte
     */

    public final void delete() throws OrmException
    {
	delete(this);
    }

    /**
     * supprime l'entity en DB corespondant au whereClause
     * 
     * @param whereClause the where clause for the query
     * 
     * @throws OrmException remonte
     */
    public final void delete(AbstractOrmEntity whereClause) throws OrmException
    {
	Vector<AbstractOrmEntity> whereParameter = new Vector<AbstractOrmEntity>();
	whereParameter.add(whereClause);
	delete(whereParameter);
    }

    /**
     * supprime l'entity en DB corespondant aux whereClauses en utilisant le
     * whereBuilder
     * 
     * @param whereClause the where clause for the query
     * 
     * @throws OrmException remonte
     * @see
     */
    public final void delete(Vector<AbstractOrmEntity> whereClause)
	    throws OrmException
    {
	Orm.delete(whereClause);
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

	    FlagPoolManager.applyFlagPoolChanges(this,
		    getPositiveFlagPoolList().values(), parameters);

	    return getList();
	}
	return retEntity;
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @throws OrmException remonte
     */
    public final void edit(String whereClause) throws OrmException
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	Orm.update(this, whereParameter);
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent, le whereclause
     * correspond a this
     * 
     * @param whereClause the where clause for the query
     * @param changeToApply the chang to apply to entity matching the where
     *            clause
     * @throws OrmException remonte
     */
    public final void edit(AbstractOrmEntity changeToApply) throws OrmException
    {
	edit(this, changeToApply);
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @param changeToApply the chang to apply to entity matching the where
     *            clause
     * @throws OrmException remonte
     */
    public final void edit(AbstractOrmEntity whereClause,
	    AbstractOrmEntity changeToApply) throws OrmException
    {
	Vector<AbstractOrmEntity> whereParameter = new Vector<AbstractOrmEntity>();
	whereParameter.add(whereClause);
	edit(whereParameter, changeToApply);
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @param changeToApply the chang to apply to entity matching the where
     *            clause
     * @throws OrmException remonte
     */
    public final void edit(Vector<AbstractOrmEntity> whereClause,
	    AbstractOrmEntity changeToApply) throws OrmException
    {
	Orm.update(whereClause, changeToApply);
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

    /**
     * @return ne liste d'entité de ce type
     * @throws Exception lorsque la requête d'obtention de liste échoue
     */
    public final AbstractEntity getList() throws Exception
    {
	return getList(new Hashtable<String, String>());
    }

    /**
     * @param parameters parametres de sélection (à venir)
     * @return une liste d'entité de ce type
     * @throws Exception lorsque la requête d'obtention de liste échoue
     */
    public AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	Vector<AbstractOrmEntity> resultSet;

	AbstractOrmEntity searchEntity = this.getClass().newInstance();

	resultSet = Orm.select(searchEntity, null);

	EntityList entityList = new EntityList(searchEntity);

	for (AbstractOrmEntity entity : resultSet)
	    entityList.addEntity(entity);

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
     * fais un get sur un criter simple exprimé par l'entity this
     * @return the selected entities
     * @throws OrmException remonte
     */
    public final Vector<AbstractOrmEntity> get() throws OrmException
    {
	return get(this);
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
     * @return Nom de la clef primaire
     */
    public final String getPrimaryKeyName()
    {
	String firstLetter = (getClass().getSimpleName().charAt(0) + "")
		.toLowerCase();

	return "PK" + firstLetter + getClass().getSimpleName().substring(1)
		+ "ID";
    }

    /**
     * @return nom de la clef étrangère pour cette entité lorsque les autres
     *         entités l'utilisent
     */
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
    public final String getPromptMessage()
    {
	if (promptMessage == null)
	    promptMessage = getVisibleName();
	return promptMessage;
    }

    /**
     * @return Nom visible de l'entités
     */
    public final String getVisibleName()
    {
	if (visibleName == null)
	    visibleName = this.getClass().getSimpleName();

	return visibleName;
    }

    /**
     * @return the flag pool list
     */
    public final Hashtable<String, FlagPool> getPositiveFlagPoolList()
    {
	if (positiveFlagPoolList == null)
	    positiveFlagPoolList = new Hashtable<String, FlagPool>();

	return positiveFlagPoolList;
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
     * @return Description d'une entité par la valeur de sa clef naturelle
     */
    public String getNaturalKeyDescription()
    {
	String description = "";
	String currentFieldValue;
	for (String naturalKeyName : getNaturalKeyNameList())
	{
	    currentFieldValue = getDataString(naturalKeyName);

	    if (isMatchCurrencyFormat(naturalKeyName))
		currentFieldValue = MoneyViewer.getHtmlCode(currentFieldValue);

	    description += " " + currentFieldValue;
	}

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

    /**
     * @return Nom de la clef naturelle
     */
    public String getNaturalKeyName()
    {
	String name = getVisibleName();
	for (String naturalKeyName : getNaturalKeyNameList())
	    name += " " + getFields().getField(naturalKeyName).getName();

	return name.trim();
    }

    /**
     * @param visibleName nom visible de l'entité
     */
    public final void setVisibleName(String visibleName)
    {
	this.visibleName = visibleName;
    }

    /**
     * @return la liste des accessors plusieurs à plusieurs pour cette entité
     * @throws Exception si obtention de liste d'accessor fail
     */
    public final TreeMap<String, Vector<AbstractOrmEntity>> getPluralAccessorList()
	    throws Exception
    {
	if (pluralAccessorList == null)
	    pluralAccessorList = AccessorManager.getPluralAccessorList(this);

	return pluralAccessorList;
    }

    /**
     * @return la liste des accessors 1 à 1 ou plusieurs à 1 pour cette entité
     * @throws Exception si obtention fail
     */
    public final TreeMap<String, AbstractOrmEntity> getSingleAccessorList()
	    throws Exception
    {
	if (singleAccessorList == null)
	    singleAccessorList = AccessorManager.getSingleAccessorList(this);

	return singleAccessorList;
    }

    /**
     * @return liste des clefs des champs par ordre d'insertion
     */
    public Vector<String> getOrderedFieldNameList()
    {
	return fields.getOrderedFieldNameList();
    }

    /**
     * @param inputName nom du field
     * @return valeur contenue dans le field
     */
    public String getInputValue(String inputName)
    {
	return fields.getField(inputName).getDataString();
    }

    /**
     * @param visibleDescription description visible du flagPool
     * @param flagPool le flag pool
     */
    public void addPositiveFlagPool(String visibleDescription, FlagPool flagPool)
    {
	getPositiveFlagPoolList().put(visibleDescription, flagPool);
    }

    /**
     * @param visibleDescription description du flag pool
     * @param flagPool flag pool
     */
    public void addNegativeFlagPool(String visibleDescription, FlagPool flagPool)
    {
	getNegativeFlagPoolList().put(visibleDescription, flagPool);
    }

    /**
     * @return liste des référence de flag pool inversées
     */
    public Hashtable<String, FlagPool> getNegativeFlagPoolList()
    {
	if (negativeFlagPoolList == null)
	    negativeFlagPoolList = new Hashtable<String, FlagPool>();
	return negativeFlagPoolList;
    }
}