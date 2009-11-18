package newtonERP.common;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
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
    private boolean confirm = false;

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
     * @param entity entity servant a compile les parametre
     * @return les parametre compile
     */
    public Hashtable<String, String> getParameters(AbstractEntity entity)
    {
	Hashtable<String, String> newparam = new Hashtable<String, String>();
	for (String key : parameters.keySet())
	{
	    if (parameters.get(key).startsWith("&"))
		newparam.put(key, entity.getDataString(key));
	    else
		newparam.put(key, parameters.get(key));
	}
	return newparam;
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
     * @return l'url relatif
     * @throws Exception remonte
     */
    public String getUrl() throws Exception
    {
	return Servlet.makeLink(action);
    }

    /**
     * @param entity entity servant a definir les parametre dynamique
     * @return les parametre sous la forme key=value&key2=value2... (ne contien
     *         pas le '?')
     * @throws Exception remonte
     */
    public String getParam(AbstractEntity entity) throws Exception
    {
	String param = "";
	for (String key : getParameters(entity).keySet())
	{
	    param += key + "=";
	    param += parameters.get(key);
	    param += "&";
	}
	if (param.length() > 0)
	    param = param.substring(0, param.length() - 1);

	return param;
    }

    /**
     * @param entity entity servant a definir les parametre dynamique
     * @return l'url relatif et suivie des parametre, utilisabe directement dans
     *         un lien web
     * @throws Exception remonte
     */
    public String getUrlParam(AbstractEntity entity) throws Exception
    {
	return getUrl() + "?" + getParam(entity);
    }

    /**
     * @return l'url relatif et suivie des parametre, utilisabe directement dans
     *         un lien web
     * @throws Exception remonte
     */
    public String getUrlParam() throws Exception
    {
	return getUrlParam(null);
    }

    /**
     * @return the confirm
     */
    public boolean isConfirm()
    {
	return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm)
    {
	this.confirm = confirm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof ActionLink))
	    return false;
	ActionLink other = (ActionLink) obj;
	if (action == null)
	{
	    if (other.action != null)
		return false;
	}
	else if (!action.equals(other.action))
	    return false;
	if (confirm != other.confirm)
	    return false;
	if (name == null)
	{
	    if (other.name != null)
		return false;
	}
	else if (!name.equals(other.name))
	    return false;
	if (parameters == null)
	{
	    if (other.parameters != null)
		return false;
	}
	else if (!parameters.equals(other.parameters))
	    return false;
	return true;
    }

    public String toString()
    {
	return name;
    }

}
