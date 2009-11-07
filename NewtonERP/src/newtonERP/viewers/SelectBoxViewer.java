package newtonERP.viewers;

import newtonERP.common.NaturalMap;
import newtonERP.viewers.viewables.SelectBoxViewable;

/**
 * The select box viewer
 * @author Guillaume Lacasse
 */
public class SelectBoxViewer
{
    /**
     * @param entity the entity to view
     * @param inputName the input name
     * @param inputValue the input value
     * @return the html code of this view
     * @throws Exception si obtention de code fail
     */
    public static String getHtmlCode(SelectBoxViewable entity,
	    String inputName, String inputValue) throws Exception
    {
	String html = "";

	html += entity.getLabelName() + ": </td>";
	html += "<td><select name=\"" + inputName + "\">";

	NaturalMap<String, String> elements = entity.getElements();

	if (elements.get(inputValue) != null)
	    html += "<option value=\"" + inputValue + "\">"
		    + elements.get(inputValue) + "</option>";

	for (String elementKey : elements.getKeyList())
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
