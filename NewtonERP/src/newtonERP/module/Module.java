/**
 * 
 */
package newtonERP.module;

import java.io.File;
import java.util.Hashtable;

import newtonERP.module.exception.ActionNotFoundException;
import newtonERP.module.exception.ModuleException;

/**
 * @author Pascal Lemay
 * 
 *         Abstract class representing an abstract module
 */
public abstract class Module
{
    // Sert a conserver les definitions de tables
    // Genre d'exemple d'une table.
    protected Hashtable<String, AbstractOrmEntity> entityDefinitionList;

    // Sert a stocker les actions pouvant etre appelees
    protected Hashtable<String, AbstractAction> actionList;

    /**
     * constructeur par default
     * 
     */
    public Module()
    {
	entityDefinitionList = new Hashtable<String, AbstractOrmEntity>();
	actionList = new Hashtable<String, AbstractAction>();
	initAction();
	initEntityDefinition();
    }

    protected final void setDefaultAction(AbstractAction action)
    {
	if (actionList.contains(action))
	    actionList.put("default", action);
    }

    private final void addAction(AbstractAction action)
    {
	actionList.put(action.getClass().getSimpleName(), action);
    }

    /**
     * initialise la liste d'action du module
     * 
     * @throws ActionNotFoundException
     */
    protected void initAction()
    {
	String packageName = getClass().getPackage().getName()
		.replace('.', '/');

	File folder = new File("src/" + packageName + "/actions");
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (listOfFiles[i].getName().endsWith(".java"))
	    {
		try
		{
		    String className = getClass().getPackage().getName()
			    + ".actions."
			    + listOfFiles[i].getName().split("\\.java")[0];
		    AbstractAction act = (AbstractAction) Class.forName(
			    className).newInstance();
		    addAction(act);
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * initialise la liste de definition d'entite du module
     * 
     * @throws ActionNotFoundException
     */
    protected void initEntityDefinition()
    {
	String packageName = getClass().getPackage().getName()
		.replace('.', '/');

	File folder = new File("src/" + packageName + "/entityDefinitions");
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (listOfFiles[i].getName().endsWith(".java"))
	    {
		try
		{
		    String className = getClass().getPackage().getName()
			    + ".entityDefinitions."
			    + listOfFiles[i].getName().split("\\.java")[0];
		    AbstractEntity def = (AbstractEntity) Class.forName(
			    className).newInstance();
		    if (def instanceof AbstractOrmEntity)
			addDefinitionEntity((AbstractOrmEntity) def);
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	}
    }

    private final void addAction(AbstractAction action, boolean isDefault)
    {
	addAction(action);
	if (isDefault)
	    setDefaultAction(action);
    }

    private final void addDefinitionEntity(AbstractOrmEntity definitinEntity)
    {
	entityDefinitionList.put(definitinEntity.getClass().getSimpleName(),
		definitinEntity);
    }

    /**
     * @return the Vector entities
     */
    public final Hashtable<String, AbstractOrmEntity> getEntityDefinitionList()
    {
	return entityDefinitionList;
    }

    /**
     * @return the actions HashTable
     */
    public final Hashtable<String, AbstractAction> getActionList()
    {
	return actionList;
    }

    /**
     * @param entityDefinitionName nom de l action
     * @return action de ce nom contenue dans le HashTable
     * @throws ModuleException si l'action n'Existe pas
     */
    public final AbstractEntity getEntityDefinition(String entityDefinitionName)
	    throws ModuleException
    {
	AbstractEntity entity = null;
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
     * @throws ModuleException si l'action n'Existe pas
     */
    public final AbstractAction getAction(String actionName)
	    throws ModuleException
    {
	AbstractAction action = null;
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
    public void initDB()
    {
	// implementation non obligatoire seulement dans les sous-classe
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
     * @throws Exception
     */
    public final AbstractEntity doAction(String actionName,
	    Hashtable<String, String> parameters) throws Exception
    {
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
     * @throws ModuleException voir le message...
     */
    public final AbstractEntity doAction(String actionName, String entityName,
	    Hashtable<String, String> parameters) throws ModuleException
    {
	AbstractOrmEntity entity = (AbstractOrmEntity) getEntityDefinition(entityName);
	entity.setEntityFromHashTable(parameters);
	if (actionName.equals("New"))
	    return entity.newUI(parameters);
	if (actionName.equals("Delete"))
	    return entity.deleteUI(parameters);
	if (actionName.equals("Edit"))
	    return entity.editUI(parameters);
	if (actionName.equals("Get"))
	    return entity.getUI(parameters);

	throw new ActionNotFoundException("l'action " + actionName
		+ "de l'entity" + entityName + "n'existe pas");
    }
}
