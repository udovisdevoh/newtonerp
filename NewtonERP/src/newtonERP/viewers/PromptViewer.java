package newtonERP.viewers;

import java.util.Hashtable;

import newtonERP.module.ListOfValue;
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

	    html += "<h1>" + entity.getPromptMessage() + "</h1>";

	    html += "<form method=\"POST\" action=\"" + formActionUrl + "?"
		    + entity.getPrimaryKeyName() + "="
		    + entity.getPrimaryKeyValue() + "\">";

	    html += "<table>";

	    String inputValue;
	    for (String inputName : entity.getInputList().keySet())
	    {
		inputValue = entity.getInputList().get(inputName);

		ListOfValue listOfValue = entity.tryMatchListOfValue(inputName);

		if (listOfValue == null)
		{
		    html += "<tr><td>" + entity.getLabelName(inputName)
			    + ": </td><td><input type=\"text\" name=\""
			    + inputName + "\" value=\"" + inputValue
			    + "\"></td></tr>";
		}
		else
		{
		    html += "<tr><td>" + listOfValue.getLabelName() + ": </td>";
		    html += "<td><select name=\"" + inputName + "\">";

		    Hashtable<String, String> elements = listOfValue
			    .getElements();

		    html += "<option value=\"" + inputValue + "\">"
			    + elements.get(inputValue) + "</option>";

		    for (String elementKey : elements.keySet())
		    {
			if (!elementKey.equals(inputValue))
			{
			    html += "<option value=\"" + elementKey + "\">"
				    + elements.get(elementKey) + "</option>";
			}
		    }

		    html += "</select></td></tr>";
		}
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
