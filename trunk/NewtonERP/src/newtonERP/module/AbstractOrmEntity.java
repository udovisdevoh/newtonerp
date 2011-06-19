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
 */
public abstract class AbstractOrmEntity extends AbstractEntity {
	private String visibleName;
	private TreeMap<String, PluralAccessor> pluralAccessorList;
	private TreeMap<String, AbstractOrmEntity> singleAccessorList;
	private Vector<GateWay> gateWayList;
	private Vector<String> accessorNameList;

	/**
	 */
	public AbstractOrmEntity() {
		super();
	}

	private Hashtable<String, FlagPool> positiveFlagPoolList;

	private static Hashtable<String, FlagPool> negativeFlagPoolList;

	@Override
	public abstract Fields initFields();

	/**
	 * @param parameters la hashTable de parametre qui sera transphormé en entity
	 */
	public final void setOrmizableData(Hashtable<String, Object> parameters)

	{
		getFields().setFromHashTable(parameters);
	}

	/**
	 * BaseAction New
	 * 
	 * @param parameters parametre suplementaire
	 * @return l'entity qui a été entré en DB (incluant la primaryKey)
	 */
	public AbstractEntity newUI(Hashtable<String, String> parameters)

	{
		getFields().setDefaultValue(false);

		return editUI(parameters, false);
	}

	/**
	 * enregistre l'entity en DB
	 * 
	 * @return this
	 */
	public final AbstractOrmEntity newE() {
		if(getFields().getKey().contains(getPrimaryKeyName())){
			setData(getPrimaryKeyName(), Orm.getInstance().insert(this));
		}else{
			Orm.getInstance().insert(this);
		}
		return this;
	}

	/**
	 * BaseAction Delete
	 * 
	 * @param parameters parametre suplementaire
	 * @return todo: qu'Est-ce que l'on devrai retourné en general?
	 */
	public AbstractEntity deleteUI(Hashtable<String, String> parameters)

	{
		delete(getPrimaryKeyName() + "='" + getDataString(getPrimaryKeyName()) + "'");

		return getList();
	}

	/**
	 * supprime l'entity en DB
	 * 
	 * @param whereClause the where clause for the query
	 */
	@Deprecated
	public final void delete(String whereClause) {
		Vector<String> whereParameter = new Vector<String>();
		whereParameter.add(whereClause);
		Orm.getInstance().delete(this, whereParameter);
	}

	/**
	 * supprime l'entity en DB, la clause where est definie par this
	 */
	public final void delete() {
		delete(this);
	}

	/**
	 * supprime l'entity en DB corespondant au whereClause
	 * 
	 * @param whereClause the where clause for the query
	 */
	public final void delete(AbstractOrmEntity whereClause) {
		Vector<AbstractOrmEntity> whereParameter = new Vector<AbstractOrmEntity>();
		whereParameter.add(whereClause);
		delete(whereParameter);
	}

	/**
	 * supprime l'entity en DB corespondant aux whereClauses en utilisant le whereBuilder
	 * 
	 * @param whereClause the where clause for the query
	 */
	public final void delete(Vector<AbstractOrmEntity> whereClause) {
		Orm.getInstance().delete(whereClause);
	}

	/**
	 * BaseAction Edit
	 * 
	 * @param parameters parametre suplementaire
	 * @return todo: qu'Est-ce que l'on devrai retourné en general?
	 */
	public BaseViewerData editUI(Hashtable<String, String> parameters) {
		return editUI(parameters, false);
	}

	/**
	 * BaseAction Edit
	 * 
	 * @param parameters parametre suplementaire
	 * @param isReadOnly si on est en mode readOnly: true, sinon, false (default: false)
	 * @return todo: qu'Est-ce que l'on devrai retourné en general?
	 */
	public BaseViewerData editUI(Hashtable<String, String> parameters, boolean isReadOnly) {
		PromptViewerData promptData = new PromptViewerData();

		AbstractOrmEntity retEntity;
		AbstractOrmEntity searchEntity;
		try{
			searchEntity = this.getClass().newInstance();
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}
		if(getPrimaryKeyValue() != 0){
			String primaryKeyName = getPrimaryKeyName();
			Integer primaryKeyValue = getPrimaryKeyValue();
			searchEntity.setData(primaryKeyName, primaryKeyValue);

			// il ne peu y avoir plus d'une entity (search par primaryKey)
			Vector<AbstractOrmEntity> result = get(searchEntity);
			retEntity = result.get(0);
		}else{
			retEntity = this;
		}
		retEntity.setCurrentAction(new BaseAction("Edit", this));

		if(parameters != null && parameters.containsKey("submit") && !isReadOnly){
			if(getPrimaryKeyValue() == 0){
				newE();
			}else{
				edit(getPrimaryKeyName() + "='" + getPrimaryKeyValue() + "'");
			}

			FlagPoolManager.applyFlagPoolChanges(this, getPositiveFlagPoolList().values(), parameters);

			retEntity = (AbstractOrmEntity) ((PromptViewerData) editUI(new Hashtable<String, String>(), isReadOnly))
			        .getData();
			promptData.addNormalMessage("Changements accomplis");
		}

		// On doit ajouter les paramètres spécifiés par défaut lors de la
		// création de l'entité.
		if(parameters != null){
			for(String key : getFields().getKey()){
				if(parameters.get(key) != null){
					setData(key, parameters.get(key));
				}
			}
		}

		promptData.setData(retEntity);
		promptData.setButtonAction(new ActionLink("Enregistrer", new BaseAction("Edit", this)));
		promptData.setBackLink(new ActionLink("Voir Liste", new BaseAction("GetList", this)));
		promptData.setTitle(retEntity.getVisibleName());
		for(Field<?> fld : getFields()){
			promptData.addAlertMessage(fld.getErrorMessage());
		}

		promptData.setReadOnly(isReadOnly);

		return promptData;
	}

	/**
	 * met a jour l'entity en DB, l'ID doit etre présent
	 * 
	 * @param whereClause the where clause for the query
	 */
	@Deprecated
	public final void edit(String whereClause) {
		Vector<String> whereParameter = new Vector<String>();
		whereParameter.add(whereClause);
		Orm.getInstance().update(this, whereParameter);
	}

	/**
	 * met a jour l'entity en DB, l'ID doit etre présent, le whereclause correspond a this
	 * 
	 * @param changeToApply the change to apply to entity matching the where clause
	 */
	public final void edit(AbstractOrmEntity changeToApply) {
		edit(this, changeToApply);
	}

	/**
	 * met a jour l'entity en DB, l'ID doit etre présent
	 * 
	 * @param whereClause the where clause for the query
	 * @param changeToApply the chang to apply to entity matching the where clause
	 */
	public final void edit(AbstractOrmEntity whereClause, AbstractOrmEntity changeToApply) {
		Vector<AbstractOrmEntity> whereParameter = new Vector<AbstractOrmEntity>();
		whereParameter.add(whereClause);
		edit(whereParameter, changeToApply);
	}

	/**
	 * met a jour l'entity en DB, l'ID doit etre présent
	 * 
	 * @param whereClause the where clause for the query
	 * @param changeToApply the chang to apply to entity matching the where clause
	 */
	public final void edit(Vector<AbstractOrmEntity> whereClause, AbstractOrmEntity changeToApply) {
		Orm.getInstance().update(whereClause, changeToApply);
	}

	/**
	 * BaseAction Get
	 * 
	 * @param parameters parametre suplementaire
	 * @return entity qui doit etre read only
	 */
	public AbstractEntity getUI(Hashtable<String, String> parameters)

	{
		return editUI(parameters, true); // TODO: rendre readOnly, néscéssaire
		// pour
		// une gestion de droit suffisante
	}

	/**
	 * @return ne liste d'entité de ce type
	 */
	public final ListViewerData getList() {
		return getList(new Hashtable<String, String>());
	}

	/**
	 * @param parameters parametres de sélection (à venir)
	 * @return une liste d'entité de ce type
	 */
	@SuppressWarnings("null")
	public ListViewerData getList(Hashtable<String, String> parameters)

	{
		Vector<AbstractOrmEntity> resultSet;
		AbstractOrmEntity searchEntity;
		try{
			searchEntity = this.getClass().newInstance();
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}
		ListViewerData entityList = new ListViewerData(searchEntity);
		String searchEntry = parameters.get("searchEntry");
		String orderBy = parameters.get("orderBy");
		Vector<String> searchParameters = null;

		int limit = ListViewerData.buildLimit(parameters, getItemLimitListPerPage());

		int offset = ListViewerData.buildOffset(parameters, limit);

		// On ajoute les critères de recherche de la barre de recherche
		if(searchEntry != null && searchEntry.length() > 0 && !searchEntry.equals("null")){
			searchParameters = new Vector<String>();
			String currentParameter;
			for(Field<?> field : getFields()){
				currentParameter = field.getShortName() + " like '%" + searchEntry + "%'";
				if(searchParameters.size() > 0){
					currentParameter = "or " + currentParameter;
				}
				searchParameters.add(currentParameter);
			}
		}

		for(String fieldName : searchEntity.getFields().getKey()){
			if(searchParameters == null){
				searchParameters = new Vector<String>();
			}
			String currentParameter;
			if(parameters.containsKey(fieldName) && !parameters.get(fieldName).equals("&"))

			{
				currentParameter = fieldName + " = " + parameters.get(fieldName);
				searchParameters.add(currentParameter);
			}
		}
		if(searchParameters.size() == 0){
			searchParameters = null;
		}

		resultSet = Orm.getInstance().select(searchEntity, searchParameters, limit, offset, orderBy);

		int totalRowCount = Orm.getInstance().count(searchEntity, searchParameters);
		if(limit < totalRowCount){
			entityList.setPageSelector(new PageSelector(limit, offset, totalRowCount, "/"
			        + getCurrentModule().getSystemName() + "/GetList/" + getSystemName(), searchEntry, orderBy));
		}else{
			entityList.setPageSelector(null);
		}

		entityList.setSearchBar(new SearchBar("/" + getCurrentModule().getSystemName() + "/GetList/" + getSystemName(),
		        parameters.get("searchEntry"), this, orderBy, limit, offset));

		for(AbstractOrmEntity entity : resultSet){
			entityList.addEntity(entity);
		}

		entityList.setCurrentModule(getCurrentModule());
		entityList.setTitle(getVisibleName());
		entityList.addGlobalActions(new ActionLink("Nouveau " + getVisibleName(), new BaseAction("New", this)));

		// ajout des action specifique
		ActionLink specAction = new ActionLink("Modifier", new BaseAction("Edit", this));
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
	public int getItemLimitListPerPage() {
		return 15;
	}

	/**
	 * @return Module par default
	 */
	@Override
	public Module getCurrentModule() {
		if(currentModule == null){
			String fullClassName = getClass().getName();

			int firstDot = fullClassName.indexOf('.');
			int secondDot = fullClassName.indexOf('.', firstDot + 1);

			String modulePackageName = fullClassName.substring(firstDot + 1, secondDot);

			String moduleName = ("" + modulePackageName.charAt(0)).toUpperCase() + modulePackageName.substring(1);

			return ListModule.getModule(moduleName);
		}

		return currentModule;
	}

	/**
	 * trouve l'entity selon les critere disponible, retourne le premier trouve
	 * 
	 * @param whereClause the where clause for the query
	 * @return this
	 */
	@Deprecated
	public final Vector<AbstractOrmEntity> get(String whereClause)

	{
		Vector<AbstractOrmEntity> retEntityList = null;
		Vector<String> whereParameter = new Vector<String>();
		whereParameter.add(whereClause);
		try{
			retEntityList = Orm.getInstance().select(this.getClass().newInstance(), whereParameter);
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}

		return retEntityList;
	}

	/**
	 * fais un get sur un criter simple exprimé par l'entity this
	 * 
	 * @return the selected entities
	 */
	public final Vector<AbstractOrmEntity> get() {
		return get(this);
	}

	/**
	 * @param entity the search entity
	 * @return the selected entities
	 */
	public final Vector<AbstractOrmEntity> get(AbstractOrmEntity entity)

	{
		Vector<AbstractOrmEntity> entities = new Vector<AbstractOrmEntity>();
		entities.add(entity);
		return get(entities);
	}

	/**
	 * @param entities the entities from which we are going to select our data (where clause)
	 * @return the selected entities
	 */
	public final Vector<AbstractOrmEntity> get(Vector<AbstractOrmEntity> entities) {
		Vector<AbstractOrmEntity> retEntities = null;
		retEntities = Orm.getInstance().select(entities);

		return retEntities;
	}

	/**
	 * @return Nom de la clef primaire
	 */
	public final String getPrimaryKeyName() {
		String firstLetter = (getSystemName().charAt(0) + "").toLowerCase();

		return "PK" + firstLetter + getSystemName().substring(1) + "ID";
	}

	/**
	 * @return nom de la clef étrangère pour cette entité lorsque les autres entités l'utilisent
	 */
	public String getForeignKeyName() {
		String firstLetter = (getSystemName().charAt(0) + "").toLowerCase();
		return firstLetter + getSystemName().substring(1) + "ID";
	}

	/**
	 * @return Valeur de la clef primaire
	 */
	public final Integer getPrimaryKeyValue() {
		String primaryKeyName = getPrimaryKeyName();
		Fields fieldsList = getFields();
		Field<?> field = fieldsList.getField(primaryKeyName);

		if(field == null || field.getData() == null){
			return null;
		}

		int value = (Integer) field.getData();

		return value;
	}

	/**
	 * Implémentation par default
	 * 
	 * @return Caption par default d'un bouton de modification pour cette entité
	 */
	public String getButtonCaption() {
		return "Enregistrer";
	}

	/**
	 * @return Nom visible de l'entités
	 */
	public final String getVisibleName() {
		if(visibleName == null){
			visibleName = getSystemName();
		}

		return visibleName;
	}

	/**
	 * @return the flag pool list
	 */
	public final Hashtable<String, FlagPool> getPositiveFlagPoolList() {
		if(positiveFlagPoolList == null){
			positiveFlagPoolList = new Hashtable<String, FlagPool>();
		}

		return positiveFlagPoolList;
	}

	/**
	 * @return Liste des noms de champs correspondant à la clef naturelle de l'entité
	 */
	public Vector<String> getNaturalKeyNameList()

	{
		Vector<String> naturalKeyNameList = new Vector<String>();

		for(Field<?> field : getFields()){
			if(field.isNaturalKey()){
				naturalKeyNameList.add(field.getShortName());
			}
		}

		if(naturalKeyNameList.size() < 1){
			// clef
			// naturelle vide
			for(Field<?> field : getFields()){
				if(field.getShortName().toLowerCase().contains("name")){
					naturalKeyNameList.add(field.getShortName());
				}
			}
		}

		if(naturalKeyNameList.size() < 1){
			// clef
			// naturelle vide
			for(Field<?> field : getFields()){
				if(field.getShortName().toLowerCase().contains("nom")){
					naturalKeyNameList.add(field.getShortName());
				}
			}
		}

		if(naturalKeyNameList.size() < 1){
			// clef
			// naturelle vide
			for(Field<?> field : getFields()){
				if(!field.getShortName().equals(getPrimaryKeyName())){
					naturalKeyNameList.add(field.getShortName());
				}
			}
		}

		if(naturalKeyNameList.size() < 1){
			// naturelle vide
			naturalKeyNameList.add(getPrimaryKeyName());
		}

		return naturalKeyNameList;
	}

	/**
	 * @return Description d'une entité par la valeur de sa clef naturelle
	 */
	public String getNaturalKeyDescription() {
		String description = "";
		String currentFieldValue;
		for(String naturalKeyName : getNaturalKeyNameList()){

			if(getFields().containsFieldName(naturalKeyName)){
				currentFieldValue = getDataString(naturalKeyName);

				if(getFields().getField(naturalKeyName) instanceof FieldCurrency){
					currentFieldValue = MoneyViewer.getHtmlCode(currentFieldValue);
				}

				ListOfValue listOfValue = tryMatchListOfValue(naturalKeyName);
				if(listOfValue == null){
					description += " " + currentFieldValue;
				}else{
					description += " " + listOfValue.getForeignValue(currentFieldValue);
				}
			}else{
				description += " -";
			}

		}

		return description.trim();
	}

	/**
	 * @return Nom de la clef naturelle
	 */
	public String getNaturalKeyName() {
		String name = getVisibleName();
		for(String naturalKeyName : getNaturalKeyNameList()){
			name += " " + getFields().getField(naturalKeyName).getName();
		}

		return name.trim();
	}

	/**
	 * @param visibleName nom visible de l'entité
	 */
	public final void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}

	/**
	 * @return la liste des accessors plusieurs à plusieurs pour cette entité
	 */
	public final TreeMap<String, PluralAccessor> getPluralAccessorList()

	{
		if(pluralAccessorList == null){
			pluralAccessorList = AccessorManager.getPluralAccessorList(this);
		}

		return pluralAccessorList;
	}

	/**
	 * @return la liste des accessors 1 à 1 ou plusieurs à 1 pour cette entité
	 */
	public final TreeMap<String, AbstractOrmEntity> getSingleAccessorList()

	{
		if(singleAccessorList == null){
			singleAccessorList = AccessorManager.getSingleAccessorList(this);
		}

		return singleAccessorList;
	}

	/**
	 * @param accessorName nom de l'accessor
	 * @return accesseur singulier
	 */
	public final AbstractOrmEntity getSingleAccessor(String accessorName)

	{
		return AccessorManager.getSingleAccessor(this, accessorName);
	}

	/**
	 * @param inputName nom du field
	 * @return valeur contenue dans le field
	 */
	public String getInputValue(String inputName) {
		return fields.getField(inputName).getDataString();
	}

	/**
	 * @param visibleDescription description visible du flagPool
	 * @param flagPool le flag pool
	 */
	public void addPositiveFlagPool(String visibleDescription, FlagPool flagPool) {
		getPositiveFlagPoolList().put(visibleDescription, flagPool);
	}

	/**
	 * @param visibleDescription description du flag pool
	 * @param flagPool flag pool
	 */
	public void addNegativeFlagPool(String visibleDescription, FlagPool flagPool) {
		getNegativeFlagPoolList().put(visibleDescription, flagPool);
	}

	/**
	 * @return liste des référence de flag pool inversées
	 */
	public Hashtable<String, FlagPool> getNegativeFlagPoolList() {
		if(negativeFlagPoolList == null){
			negativeFlagPoolList = new Hashtable<String, FlagPool>();
		}
		return negativeFlagPoolList;
	}

	/**
	 * @param accessorName non de l'accesseur
	 * @return accesseur multiple voulu
	 */
	public PluralAccessor getPluralAccessor(String accessorName) {
		return PluralAccessorManager.getPluralAccessor(this, accessorName);
	}

	/**
	 * @param accessorName nom de l'accesseur
	 * @param searchCriteriaEntity entité de recherche pour accesseur multiple critérié
	 * @return accesseur multiple critérié
	 */
	public PluralAccessor getPluralAccessor(String accessorName, AbstractOrmEntity searchCriteriaEntity) {
		PluralAccessor pluralAccessor = PluralAccessorManager.getPluralAccessor(this, accessorName,
		        searchCriteriaEntity);

		if(pluralAccessor != null){
			return pluralAccessor;
		}

		PluralAccessor uncleanedList = getPluralAccessor(accessorName);
		PluralAccessor cleanList = new PluralAccessor(uncleanedList.getInternalEntityDefinition());

		for(AbstractOrmEntity entityFromList : uncleanedList){
			if(entityFromList.matchesCriteriasFrom(searchCriteriaEntity)){
				cleanList.add(entityFromList);
			}
		}

		return cleanList;
	}

	private boolean matchesCriteriasFrom(AbstractOrmEntity searchCriteriaEntity) {
		String fieldValue, fieldValueToMatch;
		for(Field<?> field : searchCriteriaEntity.getFields()){
			fieldValue = getDataString(field.getShortName());
			fieldValueToMatch = field.getDataString();

			if(fieldValueToMatch != null && !fieldValueToMatch.equals("null") && !fieldValueToMatch.equals(fieldValue)){
				return false;
			}
		}
		return true;
	}

	/**
	 * @param foreignEntity entité étrangère
	 */
	public void assign(AbstractOrmEntity foreignEntity) {
		String foreignEntityForeignKeyName = foreignEntity.getForeignKeyName();
		String localEntityForeignKeyName = getForeignKeyName();

		if(getFields().containsFieldName(foreignEntityForeignKeyName)){
			setData(foreignEntityForeignKeyName, foreignEntity.getPrimaryKeyValue());
		}else if(foreignEntity.getFields().containsFieldName(localEntityForeignKeyName)){
			foreignEntity.setData(localEntityForeignKeyName, getPrimaryKeyValue());
		}else{
			throw new RuntimeException("Impossible d'assigner " + getNaturalKeyName() + " à "
			        + foreignEntity.getNaturalKeyName());
		}
	}

	/**
	 */
	public void save() {
		if(getPrimaryKeyValue() == null){
			throw new RuntimeException("Aucune valeur de clef primaire disponible");
		}

		edit(getPrimaryKeyName() + "='" + getPrimaryKeyValue() + "'");
	}

	/**
	 * @return nom de tous les accesseurs
	 */
	public Vector<String> getAccessorNameList() {
		if(accessorNameList == null){
			accessorNameList = new Vector<String>();
		}
		return accessorNameList;
	}

	/**
	 * @return liste des gateways (accesseurs passant par un autre)
	 */
	public Vector<GateWay> getGateWayList() {
		if(gateWayList == null){
			gateWayList = new Vector<GateWay>();
		}
		return gateWayList;
	}
}
