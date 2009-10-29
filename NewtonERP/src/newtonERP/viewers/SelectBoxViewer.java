package newtonERP.viewers;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.SelectBoxViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         The select box viewer
 */
public class SelectBoxViewer
{
    /**
     * @param entity the entity to view
     * @param inputName the input name
     * @param inputValue the input value
     * @return the html code of this view
     * @throws OrmException an exception that can occur in the orm
     */
    public static String getHtmlCode(SelectBoxViewable entity,
	    String inputName, String inputValue) throws OrmException
    {
	String html = "";

	html += entity.getLabelName() + ": </td>";
	html += "<td><select name=\"" + inputName + "\">";

	Hashtable<String, String> elements = entity.getElements();

	if (elements.get(inputValue) != null)
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
	html += "</select>";

	return html;
    }
}
