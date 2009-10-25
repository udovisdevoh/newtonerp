package newtonERP.module;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import newtonERP.module.exception.ActionNotFoundException;
import newtonERP.module.exception.ModuleException;

/**
 * @author Pascal Lemay
 * 
 *         Abstract class representing an abstract module
 * 
 *         HOW TO CREATE A MODULE IN NEWTONERP BY R3HALLEJO
 * 
 *         ***SETTING UP THE ENVIRONMENT PRIOR TO CREATING THE ENTITIES***
 *         Create your package under src/modules/name_of_your_module
 * 
 *         Under this package you have to create a package named actions, and
 *         another called entityDefinitions. --> This is case sensitive so be
 *         careful please!
 * 
 *         Next, at the root of your module package you have to create a class
 *         having like name the name of your module. In that class you will have
 *         to create the default constructor that will be calling super(); -->
 *         Beware to add the "extends Module" in your class header.
 * 
 *         ****CREATING THE ENTITIES OF YOUR MODULE (DATABASE ENTITIES)****
 *         Please add the javadoc of your class with the "@author" tag and a
 *         short description of that class
 * 
 *         Here is a couple of things you have to know. First, carefully think
 *         about your class name because it will be the table name in the
 *         database (But I mean think about it). Next, your newly created class
 *         has to extend "extends AbstractOrmEntity". Add uninplemented methods.
 * 
 *         First thing to do whe you have added the uninplemented method is to
 *         code the "initFields" method that has been implemented. Here you have
 *         to initialize your fields. The field name meaning the viewable name
 *         and the shortName is the name that will be used internally.
 * 
 *         Here is what it should like from the beginning (Only an example):
 * 
 * <pre>
 *         public class GroupRight extends AbstractOrmEntity
 *         {
 *             @Override
 *             public Fields initFields()
 *             {
 *	           Vector<Field> fields = new Vector<Field>();
 *	           fields.add(new FieldInt("numéro de groupe", "groupsID"));
 *	           fields.add(new FieldInt("numéro de droit", "rightID"));
 *	           return new Fields(fields);
 *             }
 *         }
 * </pre>
 * 
 *         Using primary keys. Please not that primary keys are always an ID for
 *         that entity. Meaning this is always and automatically a unique auto
 *         increment integer in the database and for that to be your primary key
 *         has to start with "PK" (Case senstive!)
 * 
 *         *****************************************************************
 * 
 * 
 */
public abstract class Module
{
    // Sert a conserver les definitions de tables
    // Genre d'exemple d'une table.
    protected Hashtable<String, AbstractOrmEntity> entityDefinitionList;

    // Sert a stocker les actions pouvant etre appelees
    protected Hashtable<String, AbstractAction> actionList;

    private Map<String, AbstractAction> globalActionList;

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
     * @throws Exception remonte
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
	    Hashtable<String, String> parameters) throws Exception
    {
	AbstractOrmEntity entity = (AbstractOrmEntity) getEntityDefinition(entityName);
	entity.setEntityFromHashTable(parameters);
	if (actionName.equals("New"))
	    return entity.newUI(parameters);
	if (actionName.equals("Delete"))
	    return entity.deleteUI(parameters);
	if (actionName.equals("Edit"))
	    return entity.editUI(parameters);
	/*
	 * if (actionName.equals("Get")) return entity.getUI(parameters);
	 */

	throw new ActionNotFoundException("l'action " + actionName
		+ "de l'entity" + entityName + "n'existe pas");
    }

    public final Map<String, AbstractAction> getGlobalActionMenu()
    {
	if (globalActionList == null)
	    globalActionList = new TreeMap<String, AbstractAction>();

	return globalActionList;
    }

    public final void addGlobalActionMenuItem(String name, AbstractAction action)
    {
	if (globalActionList == null)
	    globalActionList = new TreeMap<String, AbstractAction>();
	globalActionList.put(name, action);
    }
}
