package newtonERP.viewers.secondStep;

import java.util.HashSet;
import java.util.TreeMap;

import newtonERP.viewers.viewables.CheckListViewable;

/**
 * A checkList viewer
 * @author Guillaume Lacasse
 */
public class CheckListViewer
{
    /**
     * @param entity the checklist viewable entity
     * @return the html code
     * @throws Exception general exception
     */
    @SuppressWarnings("null")
    public static String getHtmlCode(CheckListViewable entity) throws Exception
    {
	String html = "";
	String isChecked;
	String currentDescription;
	String previousFirstWord = null;
	String currentFirstWord = null;
	String currentSecondWord = null;
	String previousSecondWord = null;
	TreeMap<String, String> availableElementList = entity
		.getAvailableElementList();

	HashSet<String> checkedElementList = entity.getCheckedElementList();

	html += "<div class=\"checkList\">";
	html += "<h3>" + entity.getVisibleDescription() + "</h3>";

	html += "<ul>";
	for (String currentDescriptionI : availableElementList.keySet())
	{
	    currentDescription = currentDescriptionI;

	    String id = availableElementList.get(currentDescription);

	    while (wordCount(currentDescription) < 3)
		currentDescription += " -";

	    if (wordCount(currentDescription) > 1)
	    {
		currentFirstWord = getFirstWord(currentDescription);
		currentDescription = removeFirstWord(currentDescription);
		currentSecondWord = getFirstWord(currentDescription);

		if (!currentFirstWord.equals(previousFirstWord))
		{
		    previousSecondWord = null;
		    if (previousFirstWord != null)
			html += "</ul>";

		    html += "\n<li>" + currentFirstWord + "</li><ul>";
		}

		if (wordCount(currentDescription) > 1)
		    currentDescription = removeFirstWord(currentDescription);
	    }

	    if (!currentSecondWord.equals(previousSecondWord)
		    && currentSecondWord != null)
	    {
		if (previousSecondWord != null)
		    html += "\n</li>";

		html += "\n<li>" + currentSecondWord + ": <br />";
	    }

	    // Unpeu bâtard comme manière de faire mais le layout de DIV
	    // table-cell
	    // ne semble pas bien marcher sur IE 6
	    html += "<table class=\"checkBoxAtomicElement\" style=\"display:inline\"><tr><td>";

	    if (checkedElementList.contains(id))
		isChecked = " checked";
	    else
		isChecked = "";

	    html += "<input type=\"checkbox\" name=\"" + id + "\"" + isChecked
		    + "></input> " + currentDescription;

	    /*
	     * if (currentSecondWord != previousSecondWord && currentSecondWord
	     * != null) html += "</li>";
	     */

	    html += "</td></tr></table>";

	    previousFirstWord = currentFirstWord;
	    previousSecondWord = currentSecondWord;
	}
	html += "</ul>";

	html += "</div>";

	return html;
    }

    private static String removeFirstWord(String currentDescription)
    {
	return currentDescription.substring(currentDescription.indexOf(' '))
		.trim();
    }

    private static String getFirstWord(String currentDescription)
    {
	return currentDescription.split(" ")[0].trim();
    }

    private static int wordCount(String currentDescription)
    {
	return currentDescription.split(" ").length;
    }
}
