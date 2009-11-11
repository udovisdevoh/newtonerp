package newtonERP.viewers;

public class SwitchViewer
{
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
