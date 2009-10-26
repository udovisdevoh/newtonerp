package newtonERP.viewers;

import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Guillaume Lacasse, Pascal Lemay
 * 
 *         Represents the prompt viewer
 */
public class PromptViewer
{
    /**
     * Return the html code for the web page FIXME : Why is it throwing 2 kinds
     * of exception?
     * 
     * @param entity
     * @return html
     * @throws ViewerException
     * @throws Exception
     */
    public static String getHtmlCode(PromptViewable entity)
	    throws ViewerException, Exception
    {
	System.out.println("getHtmlCode() (prompt viewer)");
	String html = "";
	try
	{
	    String formActionUrl = Servlet.makeLink(entity.getCurrentModule(),
		    entity.getCurrentAction());

	    html += "<h2>" + entity.getPromptMessage() + "</h2>";

	    html += "<form method=\"POST\" action=\"" + formActionUrl + "?"
		    + entity.getPrimaryKeyName() + "="
		    + entity.getPrimaryKeyValue() + "\">";

	    html += "<table>";

	    String inputValue;
	    for (String inputName : entity.getInputList().keySet())
	    {
		String isReadOnly = "";
		inputValue = entity.getInputList().get(inputName);

		ListOfValue listOfValue = entity.tryMatchListOfValue(inputName);

		if (inputName.equals(entity.getPrimaryKeyName()))
		    isReadOnly = "DISABLED";

		if (listOfValue == null)
		{
		    html += "<tr><td>" + entity.getLabelName(inputName)
			    + ": </td><td><input type=\"text\" name=\""
			    + inputName + "\" value=\"" + inputValue + "\"  "
			    + isReadOnly + "></td></tr>";
		}
		else
		{
		    html += "<tr><td>"
			    + SelectBoxViewer.getHtmlCode(listOfValue,
				    inputName, inputValue) + "</td></tr>";
		}
	    }

	    for (FlagPool flagPool : entity.getFlagPoolList().values())
	    {
		flagPool.query(entity.getPrimaryKeyName(), entity
			.getPrimaryKeyValue());
		html += "<tr><td colspan=\"2\">"
			+ CheckListViewer.getHtmlCode(flagPool) + "</td></tr>";
	    }

	    html += "<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" name=\"submit\" value=\""
		    + entity.getButtonCaption() + "\"></td></tr>";
	    html += "</table>";

	    html += "</form>";
	} catch (Exception e)
	{
	    throw e;
	}

	return html;
    }
}
