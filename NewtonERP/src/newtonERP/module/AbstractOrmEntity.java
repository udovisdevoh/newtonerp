package newtonERP.module;

import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.FlagPoolManager;
import newtonERP.orm.associations.GateWay;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.associations.PluralAccessorManager;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.viewers.secondStep.MoneyViewer;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ListViewerData;
import newtonERP.viewers.viewerData.PageSelector;
import newtonERP.viewers.viewerData.PromptViewerData;
import newtonERP.viewers.viewerData.SearchBar;

/**
 * @author cloutierJo
 * 
 */
public abstract class AbstractOrmEntity extends AbstractEntity
{
    private String visibleName;
    private TreeMap<String, PluralAccessor> pluralAccessorList;
    private TreeMap<String, AbstractOrmEntity> singleAccessorList;
    private Vector<GateWay> gateWayList;
    private Vector<String> accessorNameList;

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
	getFields().setDefaultValue(false);

	return editUI(parameters);
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
     * @throws Exception remonte
     */
    public final void delete(String whereClause) throws Exception
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add(whereClause);
	Orm.delete(this, whereParameter);
    }

    /**
     * supprime l'entity en DB, la clause where est definie par this
     * 
     * @throws Exception remonte
     */
    public final void delete() throws Exception
    {
	delete(this);
    }

    /**
     * supprime l'entity en DB corespondant au whereClause
     * 
     * @param whereClause the where clause for the query
     * 
     * @throws Exception remonte
     */
    public final void delete(AbstractOrmEntity whereClause) throws Exception
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
     * @throws Exception remonte
     * @see
     */
    public final void delete(Vector<AbstractOrmEntity> whereClause)
	    throws Exception
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
    public BaseViewerData editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	PromptViewerData promptData = new PromptViewerData();

	AbstractOrmEntity retEntity;
	AbstractOrmEntity searchEntity = this.getClass().newInstance();
	if (getPrimaryKeyValue() != 0)
	{
	    String primaryKeyName = getPrimaryKeyName();
	    Integer primaryKeyValue = getPrimaryKeyValue();
	    searchEntity.setData(primaryKeyName, primaryKeyValue);

	    // il ne peu y avoir plus d'une entity (search par primaryKey)
	    Vector<AbstractOrmEntity> result = get(searchEntity);
	    retEntity = result.get(0);
	}
	else
	{
	    retEntity = this;
	}
	retEntity.setCurrentAction(new BaseAction("Edit", this));

	if (parameters != null && parameters.containsKey("submit")
		&& !fields.isErrorState())
	{
	    if (getPrimaryKeyValue() == 0)
	    {
		newE();
	    }
	    else
	    {
		edit(getPrimaryKeyName() + "='" + getPrimaryKeyValue() + "'");
	    }

	    FlagPoolManager.applyFlagPoolChanges(this,
		    getPositiveFlagPoolList().values(), parameters);

	    retEntity = (AbstractOrmEntity) ((PromptViewerData) editUI(new Hashtable<String, String>()))
		    .getData();
	    promptData.addNormalMessage("Changements accomplis");
	}

	// On doit ajouter les paramètres spécifiés par défaut lors de la
	// création de l'entité.
	if (parameters != null)
	    for (String key : getFields().getKeyList())
		if (parameters.get(key) != null)
		    setData(key, parameters.get(key));

	promptData.setData(retEntity);
	promptData.setButtonAction(new ActionLink("Enregistrer",
		new BaseAction("Edit", this)));
	promptData.setBackLink(new ActionLink("Voir Liste", new BaseAction(
		"GetList", this)));
	promptData.setTitle(retEntity.getVisibleName());
	for (Field<?> fld : getFields())
	{
	    promptData.addAlertMessage(fld.getErrorMessage());
	}

	return promptData;
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @throws Exception remonte
     */
    public final void edit(String whereClause) throws Exception
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
     * @throws Exception remonte
     */
    public final void edit(AbstractOrmEntity changeToApply) throws Exception
    {
	edit(this, changeToApply);
    }

    /**
     * met a jour l'entity en DB, l'ID doit etre présent
     * 
     * @param whereClause the where clause for the query
     * @param changeToApply the chang to apply to entity matching the where
     *            clause
     * @throws Exception remonte
     */
    public final void edit(AbstractOrmEntity whereClause,
	    AbstractOrmEntity changeToApply) throws Exception
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
     * @throws Exception remonte
     */
    public final void edit(Vector<AbstractOrmEntity> whereClause,
	    AbstractOrmEntity changeToApply) throws Exception
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
    public final ListViewerData getList() throws Exception
    {
	return getList(new Hashtable<String, String>());
    }

    /**
     * @param parameters parametres de sélection (à venir)
     * @return une liste d'entité de ce type
     * @throws Exception lorsque la requête d'obtention de liste échoue
     */
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	Vector<AbstractOrmEntity> resultSet;
	AbstractOrmEntity searchEntity = this.getClass().newInstance();
	ListViewerData entityList = new ListViewerData(searchEntity);
	String searchEntry = parameters.get("searchEntry");
	String orderBy = parameters.get("orderBy");
	Vector<String> searchParameters = null;

	int limit = ListViewerData.BuildLimit(parameters,
		getItemLimitListPerPage());

	int offset = ListViewerData.BuildOffset(parameters, limit);

	// On ajoute les critères de recherche de la barre de recherche
	if (searchEntry != null && searchEntry.length() > 0
		&& !searchEntry.equals("null"))
	{
	    searchParameters = new Vector<String>();
	    String currentParameter;
	    for (Field<?> field : getFields())
	    {
		currentParameter = field.getShortName() + " like '%"
			+ searchEntry + "%'";
		if (searchParameters.size() > 0)
		    currentParameter = "or " + currentParameter;
		searchParameters.add(currentParameter);
	    }
	}

	resultSet = Orm.select(searchEntity, searchParameters, limit, offset,
		orderBy);

	int totalRowCount = Orm.count(searchEntity, searchParameters);
	if (limit < totalRowCount)
	    entityList.setPageSelector(new PageSelector(limit, offset,
		    totalRowCount, "/" + getCurrentModule().getSystemName()
			    + "/GetList/" + getSystemName(), searchEntry,
		    orderBy));
	else
	    entityList.setPageSelector(null);

	entityList.setSearchBar(new SearchBar("/"
		+ getCurrentModule().getSystemName() + "/GetList/"
		+ getSystemName(), parameters.get("searchEntry"), this,
		orderBy, limit, offset));

	for (AbstractOrmEntity entity : resultSet)
	    entityList.addEntity(entity);

	entityList.setCurrentModule(getCurrentModule());
	entityList.setTitle(getVisibleName());
	entityList.addGlobalActions(new ActionLink("Nouveau "
		+ getVisibleName(), new BaseAction("New", this)));

	// ajout des action specifique
	ActionLink specAction = new ActionLink("Modifier", new BaseAction(
		"Edit", this));
	specAction.addParameters(getPrimaryKeyName(), "&");
	entityList.addSpecificActionButtonList(specAction);

	specAction = new ActionLink("Effacer", new BaseAction("Delete", this));
	specAction.addParameters(getPrimaryKeyName(), "&");
	specAction.setConfirm(true);
	entityList.addSpecificActionButtonList(specAction);

	return entityList;
    }

    /**
     * @return Nombre d'item par page
     */
    public int getItemLimitListPerPage()
    {
	return 15;
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

	    int firstDot = fullClassName.indexOf('.');
	    int secondDot = fullClassName.indexOf('.', firstDot + 1);

	    String modulePackageName = fullClassName.substring(firstDot + 1,
		    secondDot);

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
	String firstLetter = (getSystemName().charAt(0) + "").toLowerCase();

	return "PK" + firstLetter + getSystemName().substring(1) + "ID";
    }

    /**
     * @return nom de la clef étrangère pour cette entité lorsque les autres
     *         entités l'utilisent
     */
    public String getForeignKeyName()
    {
	String firstLetter = (getSystemName().charAt(0) + "").toLowerCase();
	return firstLetter + getSystemName().substring(1) + "ID";
    }

    /**
     * @return Valeur de la clef primaire
     */
    public final Integer getPrimaryKeyValue()
    {
	String primaryKeyName = getPrimaryKeyName();
	Fields fieldsList = getFields();
	Field<?> field = fieldsList.getField(primaryKeyName);

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
     * @return Nom visible de l'entités
     */
    public final String getVisibleName()
    {
	if (visibleName == null)
	    visibleName = getSystemName();

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
	Vector<String> naturalKeyNameList = new Vector<String>();

	for (Field<?> field : getFields())
	    if (field.isNaturalKey())
		naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1) // 1er Comportement par default si
	    // clef
	    // naturelle vide
	    for (Field<?> field : getFields())
		if (field.getShortName().toLowerCase().contains("name"))
		    naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1) // 2er Comportement par default si
	    // clef
	    // naturelle vide
	    for (Field<?> field : getFields())
		if (field.getShortName().toLowerCase().contains("nom"))
		    naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1) // 3e Comportement par default si
	    // clef
	    // naturelle vide
	    for (Field<?> field : getFields())
		if (!field.getShortName().equals(getPrimaryKeyName()))
		    naturalKeyNameList.add(field.getShortName());

	if (naturalKeyNameList.size() < 1)// 4e Comportement par default si clef
	    // naturelle vide
	    naturalKeyNameList.add(getPrimaryKeyName());

	return naturalKeyNameList;
    }

    /**
     * @return Description d'une entité par la valeur de sa clef naturelle
     * @throws Exception si obtention de description fail
     */
    public String getNaturalKeyDescription() throws Exception
    {
	String description = "";
	String currentFieldValue;
	for (String naturalKeyName : getNaturalKeyNameList())
	{

	    if (getFields().containsFieldName(naturalKeyName))
	    {
		currentFieldValue = getDataString(naturalKeyName);

		if (getFields().getField(naturalKeyName) instanceof FieldCurrency)
		    currentFieldValue = MoneyViewer
			    .getHtmlCode(currentFieldValue);

		ListOfValue listOfValue = tryMatchListOfValue(naturalKeyName);
		if (listOfValue == null)
		{
		    description += " " + currentFieldValue;
		}
		else
		{
		    description += " "
			    + listOfValue.getForeignValue(currentFieldValue);
		}
	    }
	    else
	    {
		description += " -";
	    }

	}

	return description.trim();
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
    public final TreeMap<String, PluralAccessor> getPluralAccessorList()
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
     * @param accessorName nom de l'accessor
     * @return accesseur singulier
     * @throws Exception si obtention fail
     */
    public final AbstractOrmEntity getSingleAccessor(String accessorName)
	    throws Exception
    {
	return AccessorManager.getSingleAccessor(this, accessorName);
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

    /**
     * @param accessorName non de l'accesseur
     * @return accesseur multiple voulu
     * @throws Exception si obtention fail
     */
    public PluralAccessor getPluralAccessor(String accessorName)
	    throws Exception
    {
	return PluralAccessorManager.getPluralAccessor(this, accessorName);
    }

    /**
     * @param accessorName nom de l'accesseur
     * @param searchCriteriaEntity entité de recherche pour accesseur multiple
     *            critérié
     * @return accesseur multiple critérié
     * @throws Exception si obtention fail
     */
    public PluralAccessor getPluralAccessor(String accessorName,
	    AbstractOrmEntity searchCriteriaEntity) throws Exception
    {
	PluralAccessor pluralAccessor = PluralAccessorManager
		.getPluralAccessor(this, accessorName, searchCriteriaEntity);

	if (pluralAccessor != null)
	    return pluralAccessor;

	PluralAccessor uncleanedList = getPluralAccessor(accessorName);
	PluralAccessor cleanList = new PluralAccessor(uncleanedList
		.getInternalEntityDefinition());

	for (AbstractOrmEntity entityFromList : uncleanedList)
	    if (entityFromList.matchesCriteriasFrom(searchCriteriaEntity))
		cleanList.add(entityFromList);

	return cleanList;
    }

    private boolean matchesCriteriasFrom(AbstractOrmEntity searchCriteriaEntity)
    {
	String fieldValue, fieldValueToMatch;
	for (Field<?> field : searchCriteriaEntity.getFields())
	{
	    fieldValue = getDataString(field.getShortName());
	    fieldValueToMatch = field.getDataString();

	    if (fieldValueToMatch != null && !fieldValueToMatch.equals("null")
		    && !fieldValueToMatch.equals(fieldValue))
	    {
		return false;
	    }
	}
	return true;
    }

    /**
     * @param foreignEntity entité étrangère
     * @throws Exception si assignation
     */
    public void assign(AbstractOrmEntity foreignEntity) throws Exception
    {
	String foreignEntityForeignKeyName = foreignEntity.getForeignKeyName();
	String localEntityForeignKeyName = getForeignKeyName();

	if (getFields().containsFieldName(foreignEntityForeignKeyName))
	{
	    setData(foreignEntityForeignKeyName, foreignEntity
		    .getPrimaryKeyValue());
	}
	else if (foreignEntity.getFields().containsFieldName(
		localEntityForeignKeyName))
	{
	    foreignEntity.setData(localEntityForeignKeyName,
		    getPrimaryKeyValue());
	}
	else
	{
	    throw new Exception("Impossible d'assigner " + getNaturalKeyName()
		    + " à " + foreignEntity.getNaturalKeyName());
	}
    }

    /**
     * @throws Exception si enregistrement fail
     */
    public void save() throws Exception
    {
	if (getPrimaryKeyValue() == null)
	    throw new Exception("Aucune valeur de clef primaire disponible");

	edit(getPrimaryKeyName() + "='" + getPrimaryKeyValue() + "'");
    }

    /**
     * @return nom de tous les accesseurs
     */
    public Vector<String> getAccessorNameList()
    {
	if (accessorNameList == null)
	    accessorNameList = new Vector<String>();
	return accessorNameList;
    }

    /**
     * @return liste des gateways (accesseurs passant par un autre)
     */
    public Vector<GateWay> getGateWayList()
    {
	if (gateWayList == null)
	    gateWayList = new Vector<GateWay>();
	return gateWayList;
    }
}