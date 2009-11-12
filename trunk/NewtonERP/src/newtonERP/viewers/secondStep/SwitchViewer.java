package newtonERP.viewers.secondStep;

/**
 * Viewer d'élément bool switchable de vrai à faux
 * @author Guillaume Lacasse
 */
public class SwitchViewer
{
    /**
     * @param keyName nom du champ de l'entité
     * @param labelName nom de l'étiquette
     * @param isChecked si la switch est à "on"
     * @return code html de la switch true/false
     */
    public static String getHtmlCode(String keyName, String labelName,
	    boolean isChecked)
    {
	String html = "";
	String yesIsChecked, noIsChecked;

	html += "<tr><td>" + labelName + ": </td><td>";

	if (isChecked)
	{
	    yesIsChecked = " checked";
	    noIsChecked = "";
	}
	else
	{
	    yesIsChecked = "";
	    noIsChecked = " checked";
	}

	html += "oui: <input type=\"radio\" name=\"" + keyName
		+ "\" value=\"true\"" + yesIsChecked + ">";
	html += " | non: <input type=\"radio\" name=\"" + keyName
		+ "\" value=\"false\"" + noIsChecked + ">";

	html += "</td></tr>";

	return html;
    }
}
