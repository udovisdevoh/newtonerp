
package newtonERP.module;

/**
 * Abstract class representing an abstract module
 * 
 * @author Pascal Lemay
 */
public abstract class Module {
  /**
   *  Sert a conserver les definitions de tables
   *  Genre d'exemple d'une table.
   */
  protected AbstractOrmEntity entityDefinitionList;

  /**
   *  Sert a stocker les actions pouvant etre appelees
   */
  protected AbstractAction actionList;

  private newtonERP.common.NaturalMap globalActionList;

  private String defaultAction;

  private String defaultEntity;

  private String visibleName;

  private newtonERP.common.ActionLink globalActionButtonList =  null;

  private newtonERP.common.NaturalMap defaultBehaviorMenu;

  private boolean visible =  true;

  private static String, Hashtable<String, AbstractOrmEntity> entityCache =  new Hashtable<String, Hashtable<String, AbstractOrmEntity>>();

  private static String, Hashtable<String, AbstractAction> ActionCache =  new Hashtable<String, Hashtable<String, AbstractAction>>();

  /**
   * constructeur par default
   */
  public  Module() {
		entityDefinitionList = new Hashtable<String, AbstractOrmEntity>();

		actionList = new Hashtable<String, AbstractAction>();
  }

  protected final void setDefaultAction(AbstractAction action) {
		if (action instanceof BaseAction)
		{
			defaultAction = ((BaseAction) (action)).getSystemName();
			defaultEntity = ((BaseAction) (action)).getEntity().getSystemName();
		}
		else
		{
			defaultAction = action.getSystemName();
		}
  }

  /**
   * @return the defaultAction
   */
  public String getDefaultAction() {
		if (defaultAction == null)
		{
			if (getEntityDefinitionList().size() < 1)
				throw new ActionNotFoundException(
						"Impossible de définir une action par default car aucune entité n'est utilisable.");

			Vector<AbstractOrmEntity> currentDefinitionList = new Vector<AbstractOrmEntity>(
					getEntityDefinitionList().values());

			defaultEntity = currentDefinitionList.get(0).getSystemName();
			defaultAction = "GetList";
		}

		return defaultAction;
  }

  /**
   * initialise la liste d'action du module
   * 
   * @param path path to the module contening the action
   */
  public void initAction(String path) {
		if (!ActionCache.containsKey(getSystemName()))
		{
			File folder = new File(path + "/actions");
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].getName().endsWith(".class")
						&& !listOfFiles[i].getName().contains("$"))
				{
					String className = getClass().getPackage().getName()
							+ ".actions."
							+ listOfFiles[i].getName().split("\\.class")[0];
					AbstractAction act;
					try
					{
						act = (AbstractAction) ModuleLoader
								.loadClass(className).newInstance();
					} catch (InstantiationException e)
					{
						throw new RuntimeException(e);
					} catch (IllegalAccessException e)
					{
						throw new RuntimeException(e);
					}
					addAction(act);
				}
			}
			ActionCache.put(getSystemName(), actionList);
		}
		else
		{
			actionList = ActionCache.get(getSystemName());
		}
  }

  /**
   * initialise la liste de definition d'entite du module
   * 
   * @param path path to the module contening the action
   */
  public void initEntityDefinition(String path) {
		if (!entityCache.containsKey(getSystemName()))
		{
			File folder = new File(path + "/entityDefinitions");
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].getName().endsWith(".class")
						&& !listOfFiles[i].getName().contains("$"))
				{
					String className = getClass().getPackage().getName()
							+ ".entityDefinitions."
							+ listOfFiles[i].getName().split("\\.class")[0];
					Class<?> entityClass = ModuleLoader.loadClass(className);
					AbstractEntity def;
					try
					{
						def = (AbstractEntity) entityClass.newInstance();
					} catch (InstantiationException e)
					{
						throw new RuntimeException(e);
					} catch (IllegalAccessException e)
					{
						throw new RuntimeException(e);
					}
					if (def instanceof AbstractOrmEntity)
						addDefinitionEntity((AbstractOrmEntity) def);
				}
			}
			entityCache.put(getSystemName(), entityDefinitionList);
		}
		else
		{
			entityDefinitionList = entityCache.get(getSystemName());
			for (AbstractEntity entity : entityDefinitionList.values())
			{
				entity.reset();
			}
		}
  }

  private final void addAction(AbstractAction action) {
		actionList.put(action.getSystemName(), action);
		action.setOwnedByModul(this);
  }

  private final void addDefinitionEntity(AbstractOrmEntity definitinEntity) {
		entityDefinitionList.put(definitinEntity.getSystemName(),
				definitinEntity);
  }

  /**
   * @return the Vector entities
   */
  public final AbstractOrmEntity getEntityDefinitionList() {
		return entityDefinitionList;
  }

  /**
   * @return the actions HashTable
   */
  public final AbstractAction getActionList() {
		return actionList;
  }

  /**
   * @param entityDefinitionName nom de l action
   * @return action de ce nom contenue dans le HashTable
   */
  public final AbstractOrmEntity getEntityDefinition(String entityDefinitionName) {
		AbstractOrmEntity entity = null;
		try
		{
			entity = entityDefinitionList.get(entityDefinitionName);
		} catch (NullPointerException e)
		{
			throw new ModuleException("entity: " + entityDefinitionName
					+ " introuvable");
		}

		if (entity == null)
			throw new ModuleException("entity: " + entityDefinitionName
					+ " introuvable");

		return entity;
  }

  /**
   * @param actionName nom de l action
   * @return action de ce nom contenue dans le HashTable
   */
  public final AbstractAction getAction(String actionName) {
		AbstractAction action = null;

		if (actionName.equals("default"))
			actionName = getDefaultAction();

		try
		{
			action = actionList.get(actionName);
		} catch (NullPointerException e)
		{
			throw new ModuleException("Action: " + actionName + " introuvable");
		}

		if (action == null)
			throw new ModuleException("Action: " + actionName + " introuvable");

		return action;
  }

  /**
   * permet d'initialiser la base de donne lors de l'installation du module,
   * ne doit pas prendre en compte la creation des table
   */
  public void initDB() {
		// on trouve l'ID du groupe admin
		Groups group = new Groups();
		group.setData("groupName", "admin");
		int adminGroupsID = (Integer) ((Groups) Orm.select(group).get(0))
				.getData(group.getPrimaryKeyName());
		int rightID;
		GroupsRight GroupsRight = new GroupsRight();

		// on donne au group admin le droit
		// todo: a changé si l'on gère les dépendance de module
		Right searchRight = new Right();
		searchRight.setData("moduleName", getSystemName());

		for (AbstractOrmEntity right : Orm.select(searchRight))
		{
			rightID = (Integer) ((Right) right).getData(right
					.getPrimaryKeyName());

			// cree le GroupsRight
			GroupsRight.setData("groupsID", adminGroupsID);
			GroupsRight.setData("rightID", rightID);
			Orm.insert(GroupsRight);
		}
  }

  /**
   * permet de faire une action
   * 
   * @param actionName Nom de l'action que l'utilisateur veut faire
   * @param moduleGetterName Nom du module getter permetant d'obtenir l'entité
   *            produite par le module (cette entité servira de paramêtre à
   *            l'action
   * @param parameters Paramètres de l'action devant être accomplie, exemple,
   *            contenu d'un email
   * @return Entité viewable pour l'output du résultat
   */
  public final AbstractEntity doAction(String actionName, Hashtable<String, String> parameters) {
		AbstractAction action = getAction(actionName);
		return action.perform(parameters);
  }

  /**
   * permet de faire un action standart sur une entité
   * 
   * @param actionName Nom de l'action que l'utilisateur veut faire
   * @param entityName Nom de l'entity contenant l'action
   * @param moduleGetterName Nom du module getter permetant d'obtenir l'entité
   *            produite par le module (cette entité servira de paramêtre à
   *            l'action
   * @param parameters Paramètres de l'action devant être accomplie, exemple,
   *            contenu d'un email
   * @return Entité viewable pour l'output du résultat
   */
  public final AbstractEntity doAction(String actionName, String entityName, Hashtable<String, String> parameters) {
		AbstractOrmEntity entity;
		try
		{
			entity = getEntityDefinition(entityName).getClass().newInstance();
		} catch (InstantiationException e)
		{
			throw new RuntimeException(e);
		} catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		entity.getFields().setFromHashTable(parameters);
		if (actionName.equals("New"))
			return entity.newUI(parameters);
		if (actionName.equals("Delete"))
			return entity.deleteUI(parameters);
		if (actionName.equals("Edit"))
			return entity.editUI(parameters, false);
		if (actionName.equals("GetList"))
			return entity.getList(parameters);
		if (actionName.equals("Get"))
			return entity.editUI(parameters, true);

		throw new ActionNotFoundException("l'action " + actionName
				+ "de l'entity" + entityName + "n'existe pas");
  }

  /**
   * @return the global action list
   */
  public final newtonERP.common.NaturalMap getGlobalActionMenu() {
		if (globalActionList == null)
			globalActionList = new NaturalMap<String, AbstractAction>();

		return globalActionList;
  }

  /**
   * @param name the action name
   * @param action the action
   */
  public final void addGlobalActionMenuItem(String name, AbstractAction action) {
		getGlobalActionMenu().put(name, action);
  }

  /**
   * @param actionLink bouton à ajouter
   */
  public void addGlobalActionButton(newtonERP.common.ActionLink actionLink) {
		getGlobalActionButtonList().add(actionLink);
  }

  /**
   * @return liste des boutons du module
   */
  public newtonERP.common.ActionLink getGlobalActionButtonList() {
		if (globalActionButtonList == null)
			globalActionButtonList = new Vector<ActionLink>();
		return globalActionButtonList;
  }

  /**
   * @return Entité par default (dans le cas d'action par default qui est une
   *         baseAction)
   */
  public String getDefaultEntity() {
		return defaultEntity;
  }

  /**
   * @return Nom visible d'un module
   */
  public final String getVisibleName() {
		if (visibleName == null)
			visibleName = getSystemName();

		return visibleName;
  }

  /**
   * @param visibleName nom visible d'un module
   */
  public final void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Module))
			return false;
		Module other = (Module) obj;
		if (actionList == null)
		{
			if (other.actionList != null)
				return false;
		}
		else if (!actionList.equals(other.actionList))
			return false;
		if (defaultAction == null)
		{
			if (other.defaultAction != null)
				return false;
		}
		else if (!defaultAction.equals(other.defaultAction))
			return false;
		if (defaultEntity == null)
		{
			if (other.defaultEntity != null)
				return false;
		}
		else if (!defaultEntity.equals(other.defaultEntity))
			return false;
		if (entityDefinitionList == null)
		{
			if (other.entityDefinitionList != null)
				return false;
		}
		else if (!entityDefinitionList.equals(other.entityDefinitionList))
			return false;
		if (globalActionList == null)
		{
			if (other.globalActionList != null)
				return false;
		}
		else if (!globalActionList.equals(other.globalActionList))
			return false;
		if (visibleName == null)
		{
			if (other.visibleName != null)
				return false;
		}
		else if (!visibleName.equals(other.visibleName))
			return false;
		return true;
  }

  /**
   * @return nom du module dans le système (normalement nom de la classe mais
   *         peut être overridé si c'est un module dynamique)
   */
  public String getSystemName() {
		return getClass().getSimpleName();
  }

  /**
   * @return retourne les élément du menu du module peu importe qu'ils aient
   *         été spécifiés ou pas (cas pour lequel les éléments par défault
   *         seront des GetList sur chaque entité du module)
   */
  public newtonERP.common.NaturalMap getGlobalActionMenuOrReturnDefaultBehavior() {
		if (getGlobalActionMenu().size() < 1)
			return getDefaultBehaviorMenu();
		return getGlobalActionMenu();
  }

  private newtonERP.common.NaturalMap getDefaultBehaviorMenu() {
		if (defaultBehaviorMenu == null)
		{
			defaultBehaviorMenu = new NaturalMap<String, AbstractAction>();
			Collection<AbstractOrmEntity> currentEntityDefinitionList = getEntityDefinitionList()
					.values();
			for (AbstractOrmEntity entity : currentEntityDefinitionList)
			{
				if (entity == null)
					continue;
				entity.initFields();
				String visibleActionName = entity.getVisibleName();

				if (visibleActionName == null)
					continue;
				if (!defaultBehaviorMenu.containsKey(visibleActionName))
					try
					{
						defaultBehaviorMenu.put(visibleActionName,
								new BaseAction("GetList", entity.getClass()
										.newInstance()));
					} catch (InstantiationException e)
					{
						throw new RuntimeException(e);
					} catch (IllegalAccessException e)
					{
						throw new RuntimeException(e);
					}
			}
		}
		return defaultBehaviorMenu;
  }

  /**
   * @return the visible
   */
  public boolean isVisible() {
		return visible;
  }

  /**
   * @param visible the visible to set
   */
  public void setVisible(boolean visible) {
		this.visible = visible;
  }

  /**
   * reset les cache de module
   */
  public static void resetCache()
  {
		ActionCache.clear();
		entityCache.clear();
  }

}
