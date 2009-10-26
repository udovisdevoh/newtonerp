package newtonERP.viewers;

import java.util.Hashtable;

import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.SelectBoxViewable;

public class SelectBoxViewer
{
    public static String getHtmlCode(SelectBoxViewable entity,
	    String inputName, String inputValue) throws OrmException
    {
	String html = "";

	html += entity.getLabelName() + ": </td>";
	html += "<td><select name=\"" + inputName + "\">";

	Hashtable<String, String> elements = entity.getElements();

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
