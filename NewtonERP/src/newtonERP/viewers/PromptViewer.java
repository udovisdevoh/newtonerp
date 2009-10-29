package newtonERP.viewers;

import java.util.ArrayList;
import java.util.Collections;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Represents the prompt viewer
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class PromptViewer
{
    /**
     * Return the html code for the web page FIXME : Why is it throwing 2 kinds
     * of exception?
     * 
     * @param entity the entity to view
     * @return html
     * @throws ViewerException an exception that can occur in viewers
     * @throws Exception general exception
     */
    @SuppressWarnings("null")
    public static String getHtmlCode(PromptViewable entity)
	    throws ViewerException, Exception
    {
	AbstractOrmEntity ormEntity = null;
	System.out.println("getHtmlCode() (prompt viewer)");
	String html = "";

	String formActionUrl = Servlet.makeLink(entity.getCurrentModule(),
		entity.getCurrentAction());

	html += "<h2>" + entity.getPromptMessage() + "</h2>";

	if (entity instanceof AbstractOrmEntity)
	{
	    ormEntity = (AbstractOrmEntity) (entity);
	    // if (ormEntity.getPrimaryKeyValue() != 0)
	    formActionUrl += "?" + ormEntity.getPrimaryKeyName() + "="
		    + ormEntity.getPrimaryKeyValue();
	}

	html += "<form method=\"POST\" action=\"" + formActionUrl + "\">";

	html += "<table>";

	String inputValue;
	String textFieldType;

	// Sort the field name list
	ArrayList<String> fieldNameList = new ArrayList<String>(entity
		.getInputList().keySet());
	Collections.sort(fieldNameList);

	for (String inputName : fieldNameList)
	{
	    String isReadOnly = "";
	    inputValue = entity.getInputList().get(inputName);

	    ListOfValue listOfValue = entity.tryMatchListOfValue(inputName);

	    if (entity instanceof AbstractOrmEntity
		    && inputName.equals(ormEntity.getPrimaryKeyName()))
		isReadOnly = "DISABLED";

	    if (listOfValue == null)
	    {
		if (entity.isFieldHidden(inputName))
		    textFieldType = "password";
		else
		    textFieldType = "text";

		html += "\n<tr><td>" + entity.getLabelName(inputName)
			+ ": </td><td><input type=\"" + textFieldType
			+ "\" name=\"" + inputName + "\" value=\"" + inputValue
			+ "\" class=\"textField\" " + isReadOnly
			+ "></td></tr>";
	    }
	    else
	    {
		html += "<tr><td>"
			+ SelectBoxViewer.getHtmlCode(listOfValue, inputName,
				inputValue) + "</td></tr>";
	    }
	}

	if (entity instanceof AbstractOrmEntity)
	    for (FlagPool flagPool : entity.getFlagPoolList().values())
	    {
		flagPool.query(ormEntity.getPrimaryKeyName(), ormEntity
			.getPrimaryKeyValue());
		html += "<tr><td colspan=\"2\">"
			+ CheckListViewer.getHtmlCode(flagPool) + "</td></tr>";
	    }

	html += "<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" name=\"submit\" value=\""
		+ entity.getButtonCaption() + "\"></td></tr>";
	html += "</table>";

	html += "</form>";

	if (entity.getAlertMessageList() != null)
	    for (String message : entity.getAlertMessageList())
		html += "<p class=\"errorMessage\">" + message + "</p>";

	return html;
    }
}
