package newtonERP.common;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.serveur.Servlet;

/**
 * class englobant une action et tout les composants néscéssaire pour l'executer
 * @author CloutierJo
 */
public class ActionLink
{
    private String name;
    private AbstractAction action;
    private Hashtable<String, String> parameters;

    /**
     * constructeur vide
     */
    public ActionLink()
    {
	this(null);
    }

    /**
     * @param action action a effectuer
     */
    public ActionLink(AbstractAction action)
    {
	this("", action);
    }

    /**
     * @param name nom a afficher
     * @param action action a effectuer
     */
    public ActionLink(String name, AbstractAction action)
    {
	this(name, action, null);
    }

    /**
     * @param name nom a afficher
     * @param action action a effectuer
     * @param parameters parametre de lien
     */
    @SuppressWarnings("unchecked")
    public ActionLink(String name, AbstractAction action,
	    Hashtable<String, String> parameters)
    {
	this.name = name;
	this.action = action;
	if (parameters != null)
	    this.parameters = (Hashtable<String, String>) parameters.clone();
	else
	    this.parameters = new Hashtable<String, String>();
    }

    /**
     * @return the name
     */
    public String getName()
    {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
	this.name = name;
    }

    /**
     * @return the action
     */
    public AbstractAction getAction()
    {
	return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(AbstractAction action)
    {
	this.action = action;
    }

    /**
     * @return the parameters
     */
    public Hashtable<String, String> getParameters()
    {
	return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Hashtable<String, String> parameters)
    {
	this.parameters = parameters;
    }

    /**
     * @param field nom du champ a ajouter
     * @param value valeur du champ a ajouter
     * @param parameters ajoute le parametre a la liste de parametre
     */
    public void addParameters(String field, String value)
    {
	parameters.put(field, value);
    }

    /**
     * @return l'url relatif et suivie des parametre
     * @throws Exception remonte
     */
    public String getUrl() throws Exception
    {
	String param = "";
	for (String key : parameters.keySet())
	{
	    param += key + "=" + parameters.get(key) + "&";
	}
	if (param.length() > 0)
	    param = param.substring(0, param.length() - 1);
	return Servlet.makeLink(action) + "?" + param;
    }

}
