package newtonERP.viewers;

import java.util.HashSet;
import java.util.Hashtable;

import newtonERP.viewers.viewables.CheckListViewable;

public class CheckListViewer
{
    public static String getHtmlCode(CheckListViewable entity) throws Exception
    {
	String html = "";
	String isChecked;
	Hashtable<String, String> availableElementList = entity
		.getAvailableElementList();
	HashSet<String> checkedElementList = entity.getCheckedElementList();

	html += "<h3>" + entity.getVisibleDescription() + "</h3>";

	for (String id : availableElementList.keySet())
	{
	    if (checkedElementList.contains(id))
		isChecked = " checked";
	    else
		isChecked = "";

	    html += "<p>";
	    html += "<input type=\"checkbox\" name=\"id\"" + isChecked
		    + "></input> " + availableElementList.get(id);
	    html += "</p>";
	}

	return html;
    }
}
