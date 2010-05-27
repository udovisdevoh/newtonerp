package newtonERP.viewers.secondStep;

import newtonERP.common.ActionLink;
import newtonERP.common.NaturalMap;
import newtonERP.module.BaseAction;
import newtonERP.module.generalEntity.ListOfValue;
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
     * @param isReadOnly si le select box est readonly
     * @return the html code of this view
     * @throws Exception si obtention de code fail
     */
    public static String getHtmlCode(SelectBoxViewable entity,
	    String inputName, String inputValue, boolean isReadOnly)
	    throws Exception
    {
	String html = "";

	String getListLinkHtml = LinkViewer.getHtmlCode(new ActionLink(entity
		.getLabelName(), new BaseAction("GetList",
		((ListOfValue) entity).getForeignEntityDefinition())));

	if (getListLinkHtml.length() < 2)
	    getListLinkHtml = entity.getLabelName();

	html += getListLinkHtml;

	html += ": </td><td>";

	NaturalMap<String, String> elements = entity.getElements();

	if (isReadOnly)
	{
	    if (inputValue == null || inputValue.equals("null")
		    || inputValue.equals("0"))
		inputValue = "1";

	    if (elements.get(inputValue) != null)
		html += elements.get(inputValue);
	    html += "<input type=\"hidden\" name=\"" + inputName
		    + "\" value=\"" + inputValue + "\" />";
	}
	else
	{
	    html += "<select name=\"" + inputName + "\">";

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
	}

	return html;
    }
}
