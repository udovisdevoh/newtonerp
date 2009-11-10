package modules.humanResources.entityDefinitions;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * represent une cellule d'un tableau affichable
 * @author CloutierJo
 * 
 */
public class CaseTable extends AbstractEntity
{
    String value;
    AbstractAction actionLink;
    // todo: a ameliore, il faudrait un structure qui contienne
    // action, module, param de maniere simple
    String param;

    /**
     * @param value valeur a affiche dans cette case
     * @param actionLink action a effectuer lorsque clique (null par defaut)
     * @param param parametre d'URL
     * @throws Exception remonte (n'importe quoi...)
     */
    public CaseTable(String value, AbstractAction actionLink, String param)
	    throws Exception
    {
	this.value = value;
	this.actionLink = actionLink;
	this.param = param;
    }

    /**
     * @param value valeur a affiche dans cette case
     * @param actionLink action a effectuer lorsque clique (null par defaut)
     * @throws Exception remonte
     */
    public CaseTable(String value, AbstractAction actionLink) throws Exception
    {
	this(value, actionLink, "");
    }

    /**
     * @param value valeur a affiche dans cette case
     * @throws Exception remonte
     */
    public CaseTable(String value) throws Exception
    {
	this(value, null);
    }

    /**
     * constructeur vide
     * @throws Exception remonte
     */
    public CaseTable() throws Exception
    {
	this("");
    }

    /**
     * @return the value
     */
    public String getValue()
    {
	return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
	this.value = value;
    }

    /**
     * @return the actionLink
     */
    public AbstractAction getActionLink()
    {
	return actionLink;
    }

    /**
     * @param actionLink the actionLink to set
     */
    public void setActionLink(AbstractAction actionLink)
    {
	this.actionLink = actionLink;
    }

    /**
     * @return the param
     */
    public String getParam()
    {
	return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(String param)
    {
	this.param = param;
    }

}
