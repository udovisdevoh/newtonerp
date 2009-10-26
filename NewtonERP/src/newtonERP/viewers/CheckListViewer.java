package newtonERP.viewers;

import java.util.HashSet;
import java.util.TreeMap;

import newtonERP.viewers.viewables.CheckListViewable;

public class CheckListViewer
{
    public static String getHtmlCode(CheckListViewable entity) throws Exception
    {
	String html = "";
	String isChecked;
	String currentDescription;
	String previousFirstWord = null;
	String currentFirstWord = null;
	TreeMap<String, String> availableElementList = entity
		.getAvailableElementList();

	HashSet<String> checkedElementList = entity.getCheckedElementList();

	html += "<h3>" + entity.getVisibleDescription() + "</h3>";

	html += "<ul>";
	for (String currentDescriptionI : availableElementList.keySet())
	{
	    currentDescription = currentDescriptionI;
	    String id = availableElementList.get(currentDescription);

	    if (wordCount(currentDescription) > 1)
	    {
		currentFirstWord = getFirstWord(currentDescription);
		if (!currentFirstWord.equals(previousFirstWord))
		{
		    if (previousFirstWord != null)
			html += "</ul>";

		    html += "\n<li>" + currentFirstWord + "</li><ul>";
		}
		currentDescription = removeFirstWord(currentDescription);
	    }

	    html += "\n<li>";

	    if (checkedElementList.contains(id))
		isChecked = " checked";
	    else
		isChecked = "";

	    html += "<input type=\"checkbox\" name=\"" + id + "\"" + isChecked
		    + "></input> " + currentDescription;
	    html += "</li>";

	    previousFirstWord = currentFirstWord;
	}
	html += "</ul>";

	return html;
    }

    private static String removeFirstWord(String currentDescription)
    {
	return currentDescription.substring(currentDescription.indexOf(' '));
    }

    private static String getFirstWord(String currentDescription)
    {
	return currentDescription.split(" ")[0];
    }

    private static int wordCount(String currentDescription)
    {
	return currentDescription.split(" ").length;
    }
}
